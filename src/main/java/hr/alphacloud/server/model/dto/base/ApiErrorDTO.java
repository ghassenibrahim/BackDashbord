package hr.alphacloud.server.model.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorDTO {
    private String messageKey;
    private String message;

    public ApiErrorDTO(String errorKey, String errorMessage) {
        this.messageKey = errorKey;
        this.message = errorMessage;
    }

    public ApiErrorDTO(ErrorInfo errorInfo) {
        this.messageKey = errorInfo.getMessageKey();
        this.message = errorInfo.getMessage();
    }
}
