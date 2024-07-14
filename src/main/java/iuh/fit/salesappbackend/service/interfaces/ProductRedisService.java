package iuh.fit.salesappbackend.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import iuh.fit.salesappbackend.dtos.responses.PageResponse;

public interface ProductRedisService {
    PageResponse<?> getProductsInCache(int pageNo, int pageSize, String[] search, String[] sort) throws JsonProcessingException;
    void saveProductsToCache(PageResponse<?> products, int pageNo, int pageSize, String[] search, String[] sort) throws JsonProcessingException;
    void clearCache();
}
