package hr.alphacloud.server.exception;

import hr.alphacloud.server.exception.exceptions.EntityNotExistException;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiErrorDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(
                new ApiErrorDTO(ErrorInfo.GENERAL_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleConstraintViolationException(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(
                new ApiErrorDTO(ErrorInfo.CONSTRAINT_VIOLATION_EXCEPTION),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler({EntityNotExistException.class})
    public ApiBaseDTO handleConstraintViolation(EntityNotExistException ex) {
        log.error(ex.getMessage(), ex);
        return ApiBaseDTO.generateErrorResponse(ex.getErrorInfo());
    }
}
