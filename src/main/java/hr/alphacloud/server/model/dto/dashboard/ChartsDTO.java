package hr.alphacloud.server.model.dto.dashboard;

import lombok.Data;

@Data
public class ChartsDTO {
    private String chartColumn;
    private String chartRow;
    private String chartValue;

}
