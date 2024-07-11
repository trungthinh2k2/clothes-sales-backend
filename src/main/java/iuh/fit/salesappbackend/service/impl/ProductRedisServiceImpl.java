package iuh.fit.salesappbackend.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.salesappbackend.dtos.responses.PageResponse;
import iuh.fit.salesappbackend.dtos.responses.products.ProductUserResponse;
import iuh.fit.salesappbackend.service.interfaces.ProductRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRedisServiceImpl implements ProductRedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public PageResponse<?> getProductsInCache(int pageNo, int pageSize, String[] search, String[] sort)
            throws JsonProcessingException {
        String key = getKey(pageNo, pageSize, search, sort);
        String json = (String) redisTemplate.opsForValue().get(key);
        if (json != null) {
            JavaType type = objectMapper.getTypeFactory().constructParametricType(PageResponse.class,
                    objectMapper.getTypeFactory().constructParametricType(List.class, ProductUserResponse.class));
            return objectMapper.readValue(json, type);
        }
        return null;
    }

    @Override
    public void saveProductsToCache(PageResponse<?> products, int pageNo, int pageSize, String[] search, String[] sort) throws JsonProcessingException {
        String key = getKey(pageNo, pageSize, search, sort);
        String json = objectMapper.writeValueAsString(products);
        redisTemplate.opsForValue().set(key, json);
    }

    @Override
    public void clearCache() {
        redisTemplate.execute((RedisCallback<Object>) connect->
        {
            connect.flushDb();
            return null;
        });
    }

    private String getKey(int pageNo, int pageSize, String[] search, String[] sort) {
        StringBuilder key = new StringBuilder("products");
        key.append(":").append(pageNo);
        key.append(":").append(pageSize);
        if (search != null) {
            for (String s : search) {
                key.append(":").append(s);
            }
        }
        if (sort != null) {
            for (String s : sort) {
                key.append(":").append(s);
            }
        }
        return key.toString();
    }
}
