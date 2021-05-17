package hr.alphacloud.server.model.entity.reporting.dashboard;

import lombok.Data;

import java.util.List;

@Data
public class FinancialIndicatorProperties {

    private String name;
    private String action;
    private List<Integer> firstIndex;
    private List<Integer> secondIndex;
    private List<Integer> thirdIndex;
    private List<Integer> fourthIndex;
    private Boolean percentage;
}
