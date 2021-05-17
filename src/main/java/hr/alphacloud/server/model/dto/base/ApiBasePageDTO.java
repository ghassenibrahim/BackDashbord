package hr.alphacloud.server.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiBasePageDTO<T> {
    private PaginationAndSortingCommand pageSortObject;
    private boolean success;
    private ErrorInfo error;
    private List<T> response;

    /**
     * Generates success response with pagination object
     *
     * @param response       List of entities for response param
     * @param pageSortObject Object which contains pagination parameters
     * @return ApiBasePageDTO object which contains all data for pagination on frontend
     */
    public static <T> ApiBasePageDTO<T> generateSuccessResponse(List<T> response, PaginationAndSortingCommand pageSortObject) {
        ApiBasePageDTO<T> apiBaseDTO = new ApiBasePageDTO<>();
        apiBaseDTO.setError(null);
        apiBaseDTO.setSuccess(true);
        apiBaseDTO.setResponse(response);
        apiBaseDTO.setPageSortObject(pageSortObject);
        return apiBaseDTO;
    }

    /**
     * Generates error response with error code and error message
     *
     * @param errorInfo info of error
     * @return ApiBasePageDTO object which contains error data
     */
    public static <T> ApiBasePageDTO<T> generateErrorResponse(ErrorInfo errorInfo) {
        ApiBasePageDTO<T> apiBaseDTO = new ApiBasePageDTO<>();
        apiBaseDTO.setError(errorInfo);
        apiBaseDTO.setSuccess(false);
        return apiBaseDTO;
    }

}
