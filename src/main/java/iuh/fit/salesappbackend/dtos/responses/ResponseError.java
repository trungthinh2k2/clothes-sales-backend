package iuh.fit.salesappbackend.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseError {
    private int status;
    private List<String> errors;

    public ResponseError(int status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }
}
