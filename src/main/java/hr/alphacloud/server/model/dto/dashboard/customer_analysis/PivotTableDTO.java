package hr.alphacloud.server.model.dto.dashboard.customer_analysis;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PivotTableDTO {

    private List<PivotTableDataDTO> previousYearTable = new ArrayList<>();
    private List<PivotTableDataDTO> currentYearTable = new ArrayList<>();
    private List<PivotTableDataDTO> differenceInYearsTable = new ArrayList<>();

}
