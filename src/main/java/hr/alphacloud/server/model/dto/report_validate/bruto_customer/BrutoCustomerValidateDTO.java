package hr.alphacloud.server.model.dto.report_validate.bruto_customer;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BrutoCustomerValidateDTO {

    private Long reportId;
    private Long brutoId;
    private Long customerId;
    private Long brutoCode;
    private String brutoName;
    private BigDecimal customerSum;
    private BigDecimal brutoCost;

    public static BrutoCustomerValidateDTO of(BrutoCustomerValidate brutoCustomerValidate) {
        BrutoCustomerValidateDTO cmd = new BrutoCustomerValidateDTO();
        cmd.reportId = brutoCustomerValidate.getReportId();
        cmd.brutoId = brutoCustomerValidate.getBrutoId();
        cmd.customerId = brutoCustomerValidate.getCustomerId();
        cmd.brutoCode = brutoCustomerValidate.getBrutoCode();
        cmd.brutoName = brutoCustomerValidate.getBrutoName();
        cmd.customerSum = brutoCustomerValidate.getCustomerSum();
        cmd.brutoCost = brutoCustomerValidate.getBrutoCost();

        return cmd;
    }

    public static List<BrutoCustomerValidateDTO> of(List<BrutoCustomerValidate> brutoCustomerValidateList) {
        return brutoCustomerValidateList.stream().map(BrutoCustomerValidateDTO::of).collect(Collectors.toList());
    }
}
