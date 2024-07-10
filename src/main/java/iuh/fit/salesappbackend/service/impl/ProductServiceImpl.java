package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.dtos.requests.ProductDto;
import iuh.fit.salesappbackend.dtos.responses.PageResponse;
import iuh.fit.salesappbackend.dtos.responses.ProductResponse;
import iuh.fit.salesappbackend.exceptions.DataExistsException;
import iuh.fit.salesappbackend.exceptions.DataNotFoundException;
import iuh.fit.salesappbackend.mappers.ProductMapper;
import iuh.fit.salesappbackend.models.Product;
import iuh.fit.salesappbackend.models.ProductDetail;
import iuh.fit.salesappbackend.models.ProductImage;
import iuh.fit.salesappbackend.repositories.ProductDetailRepository;
import iuh.fit.salesappbackend.repositories.ProductImageRepository;
import iuh.fit.salesappbackend.repositories.ProductRepository;
import iuh.fit.salesappbackend.repositories.customizations.ProductQuery;
import iuh.fit.salesappbackend.service.interfaces.ProductService;
import iuh.fit.salesappbackend.utils.CloudinaryUpload;
import iuh.fit.salesappbackend.utils.S3Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, Long> implements ProductService {

    private ProductMapper productMapper;
    private ProductRepository productRepository;
    private ProductImageRepository productImageRepository;
    private ProductDetailRepository productDetailRepository;
    private CloudinaryUpload cloudinaryUpload;
    private S3Upload s3Upload;
    private ProductQuery productQuery;

    public ProductServiceImpl(JpaRepository<Product, Long> repository, ProductMapper productMapper) {
        super(repository, Product.class);
    }

    @Autowired
    public void setProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setProductImageRepository(ProductImageRepository productImageRepository) {
        this.productImageRepository = productImageRepository;
    }

    @Autowired
    public void setCloudinaryUpload(CloudinaryUpload cloudinaryUpload) {
        this.cloudinaryUpload = cloudinaryUpload;
    }

    @Autowired
    public void setS3Upload(S3Upload s3Upload) {
        this.s3Upload = s3Upload;
    }

    @Autowired
    public void setProductDetailRepository(ProductDetailRepository productDetailRepository) {
        this.productDetailRepository = productDetailRepository;
    }

    @Autowired
    public void setProductQuery(ProductQuery productQuery) {
        this.productQuery = productQuery;
    }

    //Upload Cloudinary
//    @Override
//    @Transactional(rollbackFor = {DataExistsException.class, DataNotFoundException.class})
//    public Product save(ProductDto productDto) throws DataExistsException, DataNotFoundException {
//        if(productRepository.existsByProductName(productDto.getProductName()))
//           throw new DataExistsException("Product name already exists");
//        Product product = productMapper.ProductDto2Product(productDto);
//        product = super.save(product);
//        if(!productDto.getImages().isEmpty()) {
//            List<MultipartFile> multipartFiles = productDto.getImages();
//            for (MultipartFile file : multipartFiles) {
//                if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")){
//                    throw new DataExistsException("File is not an image");
//                }
//                try {
//                    String path = cloudinaryUpload.upload(file);
//                    ProductImage productImage = new ProductImage();
//                    productImage.setProduct(product);
//                    productImage.setPath(path);
//                    productImageRepository.save(productImage);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        return super.save(product);
//    }


    //Upload S3
    @Override
    @Transactional(rollbackFor = {DataExistsException.class, DataNotFoundException.class})
    public Product save(ProductDto productDto) throws DataExistsException, DataNotFoundException {
        if (productRepository.existsByProductName(productDto.getProductName()))
            throw new DataExistsException("Product name already exists");
        Product product = productMapper.ProductDto2Product(productDto);
        product = super.save(product);
        if (!productDto.getImages().isEmpty()) {
            List<MultipartFile> multipartFiles = productDto.getImages();
            for (MultipartFile file : multipartFiles) {
                if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {
                    throw new DataExistsException("File is not an image");
                }
                try {
                    String path = s3Upload.uploadFile(file);
                    ProductImage productImage = new ProductImage();
                    productImage.setProduct(product);
                    productImage.setPath(path);
                    productImageRepository.save(productImage);
                    if (productDto.getThumbnail() != null &&
                            productDto.getThumbnail() == multipartFiles.indexOf(file)
                    ) {
                        product.setThumbnail(path);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.save(product);
    }

    @Override
    public ProductResponse findProductById(Long id) throws DataNotFoundException {
        Product product = super.findById(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
        List<ProductImage> productImages = productImageRepository.findByProductId(id);
        List<ProductDetail> productDetails = productDetailRepository.findByProductId(id);
        return ProductResponse.builder()
                .product(product)
                .productImages(productImages)
                .productDetails(productDetails)
                .build();
    }

    @Override
    public PageResponse<?> getProductsForUserRole(int pageNo, int pageSize, String[] search, String[] sort) {
        return productQuery.getPageData(pageNo, pageSize, search, sort);
    }

}
