package iuh.fit.salesappbackend.dtos.responses;

import lombok.*;

@Getter
@Setter
@Builder
public class PageResponse {
    private int pageNo;
    private long totalPage;
    private int totalElements;
    private Object data;
}
