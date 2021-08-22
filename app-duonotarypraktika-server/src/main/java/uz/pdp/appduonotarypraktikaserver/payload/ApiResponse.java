package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private Object object;
    private Long totalElements;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


    public ApiResponse(String message, boolean success, Object object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }
}
