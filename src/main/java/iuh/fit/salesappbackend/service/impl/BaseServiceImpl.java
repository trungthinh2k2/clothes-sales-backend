package iuh.fit.salesappbackend.service.impl;

import iuh.fit.salesappbackend.dtos.responses.PageResponse;
import iuh.fit.salesappbackend.repositories.customizations.BaseCustomizationRepository;
import iuh.fit.salesappbackend.service.interfaces.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BaseServiceImpl<T, ID extends Serializable>
        extends BaseCustomizationRepository<T>
        implements BaseService<T, ID>{

    private final JpaRepository<T, ID> repository;

    public BaseServiceImpl(JpaRepository<T, ID> repository, Class<T> entityClass) {
        super(entityClass);
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public T update(ID id, T t) {
        repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return repository.save(t);
    }

    /*
    Giải thích phương thức Update Patch:
        1. Tìm đối tượng cần cập nhật
        2. Lấy lớp của đối tượng
        3. Lấy danh sách các khóa từ map
        4. Lấy tất cả các phương thức của lớp đối tượng
        5. Duyệt qua từng phương thức và từng khóa
        6. Lưu đối tượng đã cập nhật
     */
    @Override
    public T updatePatch(ID id, Map<String, ?> data) {
        T t = repository.findById(id).orElseThrow();            // 1
        Class<?> clazz = t.getClass();                          // 2
        Set<String> keys = data.keySet();                       // 3
        Method[] methods = clazz.getDeclaredMethods();          // 4
        for (Method method : methods) {
            for (String key : keys) {
                if (method.getName().equals("set" + toUpperCaseFirstChar(key)) ||
                    method.getName().equals("is" + toUpperCaseFirstChar(key))) {
                    try {
                        Object value = data.get(key);
                        if (value instanceof String && method.getParameterTypes()[0].isEnum()) {
                            value = Enum.valueOf((Class<Enum>) method.getParameterTypes()[0], (String) value);
                        }
                        method.invoke(t, value);
                    } catch (Exception e) {
                       throw new RuntimeException(e);
                    }
                }
            }
        }
        return repository.save(t);
    }

    private String toUpperCaseFirstChar(String str) {
        char[] chars = str.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public PageResponse<?> getPageData(int pageNo, int pageSize, String[] search, String[] sort) {
        return super.getPageData(pageNo, pageSize, search, sort);
    }


}
