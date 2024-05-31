package uz.freelance.kt_task.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private int status;
    private Object data;

    public ApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.data = "";
    }
}
