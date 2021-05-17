package hr.alphacloud.server.model.dto.report_validate.base_control;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BaseControlDTO {

    private String brutoName;
    private Long brutoKonto;
    private BigDecimal brutoValue;
    private BigDecimal importValue;

    public static BaseControlDTO of(BaseControl baseControl) {
        BaseControlDTO cmd = new BaseControlDTO();
        cmd.brutoKonto = baseControl.getBrutoKonto();
        cmd.brutoName = baseControl.getBrutoName();
        cmd.brutoValue = baseControl.getBrutoValue();
        cmd.importValue = baseControl.getImportValue();

        return cmd;
    }

    public static List<BaseControlDTO> of(List<BaseControl> baseControlList) {
        return baseControlList.stream().map(BaseControlDTO::of).collect(Collectors.toList());
    }
}
