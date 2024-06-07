package iuh.fit.salesappbackend.service.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BaseService<T, ID> {
    T save (T t);
    List<T> findAll();
    Optional<T> findById(ID id);
    T update(ID id,T t);
    T updatePatch(ID id, Map<String,?> data);
    void deleteById(ID id);
}
