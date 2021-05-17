package hr.alphacloud.server.model.dto.report_validate.bruto_balance;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BrutoBalanceValidateDTO {

    private Long id;
    private Long reportId;
    private BigDecimal initStateBalance;
    private BigDecimal finalStateBalance;

    public static BrutoBalanceValidateDTO of(BrutoBalanceValidate brutoBalanceValidate) {
        BrutoBalanceValidateDTO cmd = new BrutoBalanceValidateDTO();
        cmd.id = brutoBalanceValidate.getId();
        cmd.reportId = brutoBalanceValidate.getReportId();
        cmd.initStateBalance = brutoBalanceValidate.getInitStateBalance();
        cmd.finalStateBalance = brutoBalanceValidate.getFinalStateBalance();

        return cmd;
    }

    public static List<BrutoBalanceValidateDTO> of(List<BrutoBalanceValidate> brutoBalanceValidates) {
        return brutoBalanceValidates.stream().map(BrutoBalanceValidateDTO::of).collect(Collectors.toList());
    }
}
