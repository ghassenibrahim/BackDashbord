package hr.alphacloud.server.model.dto.dashboard;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class FinancialIndicatorsPropertiesDTO {

    private String name;
    private List<BigDecimal> totalValue = new ArrayList<>();
    private Boolean percentage;
}
