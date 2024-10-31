package iuh.fit.salesappbackend.configurations;

import iuh.fit.salesappbackend.dtos.requests.ProductPriceDto;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.mappers.ProductPriceMapper;
import iuh.fit.salesappbackend.models.*;
import iuh.fit.salesappbackend.models.enums.Gender;
import iuh.fit.salesappbackend.models.enums.Role;
import iuh.fit.salesappbackend.models.enums.SizeType;
import iuh.fit.salesappbackend.models.enums.Status;
import iuh.fit.salesappbackend.repositories.*;
import iuh.fit.salesappbackend.service.impl.ProductDetailServiceImpl;
import iuh.fit.salesappbackend.service.impl.ProductPriceServiceImpl;
import iuh.fit.salesappbackend.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.datafaker.Faker;

@Configuration
@RequiredArgsConstructor
public class FakerData {
    private final ProductServiceImpl productServiceImpl;
    private final SizeRepository sizeRepository;
    private final ColorRepository colorRepository;
    private final CategoryRepository categoryRepository;
    private final ProviderRepository providerRepository;
    private final String[] categories = {"Áo", "Quần", "Giày dép", "Túi xách", "Nước hoa", "Linh tinh"};
    private final String[] providers = {"Nike", "Louis Vuitton", "Gucci", "Chanel", "Dior", "Prada"};
    private final ProductImageRepository productImageRepository;
    private final ProductDetailServiceImpl productDetailServiceImpl;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ProductPriceServiceImpl productPriceServiceImpl;
    private final ProductPriceMapper productPriceMapper;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Faker faker = new Faker();
            Random random = new Random();
            List<User> users = fakeUser(faker);
            fakeCategory();
            fakeProvider(faker);
            fakeColor();
            fakeSize();
            fakeProduct(faker, random);
            fakeProductPrice(random);
        };
    }

    private List<User> fakeUser(Faker faker) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            User user = new User();
            user.setAddress(new Address(faker.address().streetAddress(),
                    faker.address().state(),
                    faker.address().city()));
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(passwordEncoder.encode("123456"));
            user.setVerify(true);
            user.setPhoneNumber(faker.phoneNumber().subscriberNumber(10));
            user.setGender(faker.options().option(Gender.class));
            user.setName(faker.name().fullName());
            user.setRole(Role.ROLE_USER);
            users.add(userRepository.save(user));
        }
        return users;
    }

    private void fakeCategory() {
        for (String s : categories) {
            Category category = new Category();
            category.setCategoryName(s);
//            category.setStatus(Status.ACTIVE);
            categoryRepository.save(category);
        }
    }

    private void fakeProvider(Faker faker) {
        for (String s : providers) {
            Provider provider = new Provider();
            provider.setProviderName(s);
            provider.setEmail(faker.internet().emailAddress());
            provider.setPhoneNumber(faker.phoneNumber().subscriberNumber(10));
            provider.setAddress(new Address(faker.address().streetAddress(),
                    faker.address().state(),
                    faker.address().city()));
            providerRepository.save(provider);
        }
    }

    private void fakeSize() {
        String[] textSize = {"S", "M", "L", "XL", "XXL"};
        for (int i = 30; i <= 45; i++) {
            Size size = new Size();
            size.setNumberSize((int) Short.parseShort(i + ""));
            size.setSizeType(SizeType.NUMBER);
            sizeRepository.save(size);
        }
        for (int i = 0; i <= 4; i++) {
            Size size = new Size();
            size.setTextSize(textSize[i]);
            size.setSizeType(SizeType.TEXT);
            sizeRepository.save(size);
        }
    }

    private void fakeColor() {
        String[] colors = {"red", "green", "blue", "yellow",
                "black", "white", "brown", "orange", "violet", "crimson",
                "grey", "pink", "aqua", "purple", "silver"
        };
        String[] colorsHex = {
                "#FF0000", "#008000", "#0000FF", "#FFFF00",
                "#000000", "#FFFFFF", "#A52A2A", "#FFA500",
                "#8F00FF", "#DC143C", "#808080", "#FFC0CB",
                "#00FFFF", "#800080", "#C0C0C0"
        };
        for (String s : colors) {
            Color color = new Color();
            color.setColorName(s);
//            color.setColorHex(colorsHex[i]);
            colorRepository.save(color);
        }
    }

    private void fakeProduct(Faker faker, Random random) {
        for (int i = 0; i < 1000; i++) {
            Product product = new Product();
            product.setProductName(faker.commerce().productName() + " " + faker.commerce().brand() + " " + faker.name().title());
            product.setDescription(faker.restaurant().description());
            product.setProductStatus(Status.ACTIVE);
            product.setCategory(new Category(random.nextLong(categories.length) + 1));
            product.setProvider(new Provider(random.nextLong(providers.length) + 1));
            product.setPrice(Double.parseDouble(roundPrice(random.nextInt(50000, 2000001)) + ""));
            productServiceImpl.save(product);
            for (int j = 0; j < 5; j++) {
                ProductImage productImage = new ProductImage();
                productImage.setProduct(product);
                String formattedNumber = String.format("%03d", random.nextInt(133) + 1);
                String path = String
                        .format("https://sales-app-bucket.s3.ap-southeast-1.amazonaws.com/fakeimages/%s.jpg",
                                formattedNumber);
                productImage.setPath(path);
                productImageRepository.save(productImage);
                if (j == 0) {
                    product.setThumbnail(path);
                    productServiceImpl.save(product);
                }
            }
            SizeType sizeType = faker.options().option(SizeType.class);
            for (int j = 0; j <= 5; j++) {
                ProductDetail productDetail = new ProductDetail();
                productDetail.setProduct(product);
                productDetail.setQuantity(random.nextInt(100, 1000));
                if (sizeType == SizeType.NUMBER) {
                    productDetail.setSize(new Size(random.nextLong(15) + 1));
                } else {
                    productDetail.setSize(new Size(random.nextLong(16, 20) + 1));
                }
                for (int k = 1; k <= 6; k++) {
                    productDetail.setId(null);
                    productDetail.setColor(new Color(Long.parseLong(k + "")));
                    productDetailServiceImpl.save(productDetail);
                }

            }
        }
    }

    private int roundPrice(int price) {
        return Math.round(price / 1000f) * 1000;
    }


    private void fakeProductPrice(Random random) throws DataNotFoundException {
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            long productId = random.nextLong(1000) + 1;
            if (ids.contains(productId)) {
                continue;
            }
            ProductPriceDto productPriceDto = ProductPriceDto.builder()
                    .productId(productId)
                    .discount((float) ((random.nextInt(9) + 1) / 10f))
                    .note("sale")
                    .expiredDate(LocalDateTime.of(2026, 12, 31, 23, 59, 59))
                    .build();

            productPriceServiceImpl
                    .save(productPriceMapper.productPriceDto2ProductPrice(productPriceDto));
            ids.add(productId);

        }
    }


}