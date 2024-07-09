package iuh.fit.salesappbackend.dtos.responses;

import lombok.*;

@Getter
@Setter
@Builder
public class PageResponse<T> {
    private int pageNo;
    private long totalPage;
    private int totalElements;
    private T data;
}
