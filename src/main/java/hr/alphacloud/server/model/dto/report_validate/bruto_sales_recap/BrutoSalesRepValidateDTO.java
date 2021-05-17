package hr.alphacloud.server.model.dto.report_validate.bruto_sales_recap;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class BrutoSalesRepValidateDTO {

    private Long id;
    private Long reportId;
    private Long brutoId;
    private Long brutoCode;
    private String brutoArticleName;
    private BigDecimal salesRepTotalSellingPrice;
    private BigDecimal brutoFinalStateBalance;

    public static BrutoSalesRepValidateDTO of(BrutoSalesRepValidate brutoSalesRepValidate) {
        BrutoSalesRepValidateDTO cmd = new BrutoSalesRepValidateDTO();
        cmd.id = brutoSalesRepValidate.getId();
        cmd.reportId = brutoSalesRepValidate.getReportId();
        cmd.brutoId = brutoSalesRepValidate.getBrutoId();
        cmd.brutoCode = brutoSalesRepValidate.getBrutoCode();
        cmd.brutoArticleName = brutoSalesRepValidate.getBrutoArticleName();
        cmd.salesRepTotalSellingPrice = brutoSalesRepValidate.getSalesRepTotalSellingPrice();
        cmd.brutoFinalStateBalance = brutoSalesRepValidate.getBrutoFinalStateBalance();

        return cmd;
    }

    public static List<BrutoSalesRepValidateDTO> of(List<BrutoSalesRepValidate> brutoSalesRepValidates) {
        return brutoSalesRepValidates.stream().map(BrutoSalesRepValidateDTO::of).collect(Collectors.toList());
    }
}
