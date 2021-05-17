package hr.alphacloud.server.exception.exceptions;

import hr.alphacloud.server.model.dto.base.ErrorInfo;
import lombok.Getter;

@Getter
public class EntityNotExistException extends RuntimeException {
    private final ErrorInfo errorInfo;

    public EntityNotExistException(ErrorInfo errorInfo) {
        super(errorInfo.getMessage());
        this.errorInfo = errorInfo;
    }
}
