package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.dtos.requests.OrderDto;
import iuh.fit.salesappbackend.dtos.requests.ProductOrderDto;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.mappers.AddressMapper;
import iuh.fit.salesappbackend.models.*;
import iuh.fit.salesappbackend.models.enums.DeliveryMethod;
import iuh.fit.salesappbackend.models.enums.OrderStatus;
import iuh.fit.salesappbackend.models.enums.Scope;
import iuh.fit.salesappbackend.models.enums.VoucherType;
import iuh.fit.salesappbackend.models.id_classes.UserVoucherId;
import iuh.fit.salesappbackend.repositories.*;
import iuh.fit.salesappbackend.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl extends BaseServiceImpl<Order, String> implements OrderService {

    private UserRepository userRepository;
    @Value("${delivery.EXPRESS}")
    private Double expressPrice;
    @Value("${delivery.ECONOMY}")
    private Double economyPrice;
    private AddressMapper addressMapper;
    private ProductRepository productRepository;
    private ProductDetailRepository productDetailRepository;
    private ProductPriceRepository productPriceRepository;
    private OrderDetailRepository orderDetailRepository;
    private VoucherRepository voucherRepository;
    private UserVoucherRepository userVoucherRepository;
    private VoucherUsagesRepository voucherUsagesRepository;

    public OrderServiceImpl(JpaRepository<Order, String> repository) {
        super(repository);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductDetailRepository(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @Autowired
    public void setProductPriceRepository(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    @Autowired
    public void setOrderDetailRepository(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Autowired
    public void setVoucherRepository(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Autowired
    public void setUserVoucherRepository(UserVoucherRepository userVoucherRepository) {
        this.userVoucherRepository = userVoucherRepository;
    }

    @Autowired
    public void setVoucherUsagesRepository(VoucherUsagesRepository voucherUsagesRepository) {
        this.voucherUsagesRepository = voucherUsagesRepository;
    }

    @Override
    @Transactional(rollbackFor = {DataNotFoundException.class})
    public Order save(OrderDto orderDto) throws DataNotFoundException {
        List<ProductOrderDto> productOrderDtos = orderDto.getProductOrderDtos();
        User user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        double originalAmount = 0;

        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .orderDate(LocalDateTime.now())
                .status(OrderStatus.PENDING)
                .originalAmount(originalAmount)
                .byerName(orderDto.getBuyerName())
                .phoneNumber(orderDto.getPhoneNumber())
                .paymentMethod(orderDto.getPaymentMethod())
                .note(orderDto.getNote())
                .deliveryMethod(orderDto.getDeliveryMethod())
                .deliveryAmount(orderDto.getDeliveryMethod().equals(DeliveryMethod.EXPRESS) ? expressPrice : economyPrice)
                .address(addressMapper.addressDto2Address(orderDto.getAddress()))
                .build();
        order = super.save(order);

        List<Long> vouchers = orderDto.getVouchers();
        for (Long voucherId: vouchers) {
            Voucher voucher = voucherRepository.findById(voucherId)
                    .orElseThrow(() -> new DataNotFoundException("Voucher not found"));
            UserVoucherId userVoucherId = new UserVoucherId(user, voucher);
            if (voucher.getScope().equals(Scope.FOR_USER)) {
                UserVoucher userVoucher = userVoucherRepository.findById(userVoucherId)
                        .orElseThrow(() -> new DataNotFoundException("UserVoucher not found"));
                if (!userVoucher.getIsUsed()) {
                    double discountPrice = addVoucherDeliveryToOrder(originalAmount, voucher);
                    if(discountPrice > 0) {
                        userVoucher.setIsUsed(true);
                    }
                    if(voucher.getVoucherType().equals(VoucherType.FOR_DELIVERY)) {
                        order.setDeliveryAmount(order.getDeliveryAmount() - discountPrice);
                    } else {
                        order.setDiscountedPrice(
                                (order.getDiscountedPrice() == null ? 0 : order.getDiscountedPrice())
                                        + discountPrice);
                    }
                    userVoucherRepository.save(userVoucher);
                }
            } else  {
                Optional<VoucherUsages> voucherUsages = voucherUsagesRepository.findById(userVoucherId);
                double discountPrice = addVoucherDeliveryToOrder(originalAmount, voucher);
                if(voucherUsages.isEmpty()) {
                    if(voucher.getVoucherType().equals(VoucherType.FOR_DELIVERY)) {
                        order.setDeliveryAmount(order.getDeliveryAmount() - discountPrice);
                    } else {
                        order.setDiscountedPrice(
                                (order.getDiscountedPrice() == null ? 0 : order.getDiscountedPrice())
                                        + discountPrice);
                    }
                    if(discountPrice > 0) {
                        VoucherUsages voucherUsages1 = new VoucherUsages();
                        voucherUsages1.setVoucher(voucher);
                        voucherUsages1.setUser(user);
                        voucherUsages1.setUsagesDate(LocalDateTime.now());
                        voucherUsagesRepository.save(voucherUsages1);
                    }
                }
            }
        }

        originalAmount = handleAmount(productOrderDtos, order, originalAmount);
        order.setDiscountedAmount((originalAmount + order.getDeliveryAmount())
                - (order.getDiscountedPrice() == null ? 0 : order.getDiscountedPrice()));
        order.setOriginalAmount(originalAmount);
        return super.save(order);
    }

    private double addVoucherDeliveryToOrder(double originalAmount, Voucher voucher) {
        if(voucher.getExpriedDate().isAfter(LocalDateTime.now()) &&
                originalAmount >= voucher.getMinAmount()) {
            double discountPrice = originalAmount * voucher.getDiscount();
            if(discountPrice >= voucher.getMaxPrice()) {
                discountPrice = voucher.getMaxPrice();
            }
            return discountPrice;

        }
        return 0;
    }

    private double handleAmount(List<ProductOrderDto> productOrderDtos, Order order, double originalAmount) throws DataNotFoundException {
        for (ProductOrderDto productOrderDto : productOrderDtos) {
            ProductDetail productDetail = productDetailRepository.findById(
                            productOrderDto.getProductDetailId())
                    .orElseThrow(() -> new DataNotFoundException("Product detail not found")
                    );
            Product product = productDetail.getProduct();
            int quantity = productDetail.getQuantity() - productOrderDto.getQuantity();
            if (quantity < 0) {
                throw new DataNotFoundException("Product out of stock");
            }
            productDetail.setQuantity(quantity);
            product.setTotalQuantity(product.getTotalQuantity() - productOrderDto.getQuantity());
            productDetailRepository.save(productDetail);
            productRepository.save(product);

            List<ProductPrice> productPrices = productPriceRepository
                    .findAllByProductId(product.getId());
            double discountedPrice = 0;
            double price = product.getPrice();
            if (!productPrices.isEmpty()) {
                for (ProductPrice productPrice: productPrices ) {
                    if(productPrice.getExpriedDate().isAfter(LocalDateTime.now())) {
                        if(productPrice.getDiscountedPrice() > discountedPrice) {
                            discountedPrice = productPrice.getDiscountedPrice();
                        }
                    }
                }
            }
            price = price - discountedPrice;
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(productOrderDto.getQuantity());
            orderDetail.setAmount(price * orderDetail.getQuantity());
            orderDetail.setOrder(order);
            orderDetail.setProductDetail(productDetail);
            orderDetailRepository.save(orderDetail);
            originalAmount += orderDetail.getAmount();
        }
        return originalAmount;
    }
}
