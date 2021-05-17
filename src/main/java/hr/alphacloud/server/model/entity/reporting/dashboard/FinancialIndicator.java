package hr.alphacloud.server.model.entity.reporting.dashboard;

import lombok.Data;

import java.util.List;

@Data
public class FinancialIndicator {
    private String name;
    private List<FinancialIndicatorProperties> propertiesList;
}
