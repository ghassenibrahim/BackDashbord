package hr.alphacloud.server.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiBaseDTO<T> {
    private boolean success;
    private ApiErrorDTO error;
    private T response;

    /**
     * Generates success response
     *
     * @param response data
     * @return ApiBaseDTO with specific response data
     */
    public static <T> ApiBaseDTO<T> generateSuccessResponse(T response) {
        ApiBaseDTO<T> apiBaseDTO = new ApiBaseDTO<>();
        apiBaseDTO.setError(null);
        apiBaseDTO.setSuccess(true);
        apiBaseDTO.setResponse(response);
        return apiBaseDTO;
    }

    /**
     * Generates error response
     *
     * @param errorInfo info of the error
     * @return ApiBaseDTO with error data
     */
    public static <T> ApiBaseDTO<T> generateErrorResponse(ErrorInfo errorInfo) {
        ApiBaseDTO<T> apiBaseDTO = new ApiBaseDTO<>();
        apiBaseDTO.setError(new ApiErrorDTO(errorInfo.getMessageKey(), errorInfo.getMessage()));
        apiBaseDTO.setSuccess(false);
        return apiBaseDTO;
    }
}
