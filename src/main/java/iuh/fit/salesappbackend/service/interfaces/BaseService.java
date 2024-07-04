package iuh.fit.salesappbackend.service.interfaces;

import iuh.fit.salesappbackend.dtos.responses.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BaseService<T, ID> {
    T save(T t);
    List<T> findAll();
    Optional<T> findById(ID id);
    T update(ID id, T t);
    T updatePatch(ID id, Map<String, ?> data);
    void deleteById(ID id);
    Page<T> findAll(Pageable pageable);
    PageResponse getPageData(int pageNo, int pageSize, String[] search, String[] sort);
}
