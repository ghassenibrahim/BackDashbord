package hr.alphacloud.server.model.dto.report_validate.sales_recap_supply;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SalesRepSupplyValidateDTO {

    private Long reportId;
    private Long salesId;
    private Long supplyId;
    private Long salesArticleNumber;
    private BigDecimal salesSoldAmount;
    private BigDecimal salesItemSellingPrice;
    private Long supplyArticleNumber;
    private BigDecimal supplyAmountOutgoing;
    private BigDecimal supplyValueOutgoing;
    private String supplyArticleName;

    public static SalesRepSupplyValidateDTO of(SalesRepSupplyValidate salesRepSupplyValidate) {
        SalesRepSupplyValidateDTO cmd = new SalesRepSupplyValidateDTO();
        cmd.reportId = salesRepSupplyValidate.getReportId();
        cmd.salesId = salesRepSupplyValidate.getSalesId();
        cmd.supplyId = salesRepSupplyValidate.getSupplyId();
        cmd.salesArticleNumber = salesRepSupplyValidate.getSalesArticleNumber();
        cmd.salesSoldAmount = salesRepSupplyValidate.getSalesSoldAmount();
        cmd.salesItemSellingPrice = salesRepSupplyValidate.getSalesItemSellingPrice();
        cmd.supplyArticleNumber = salesRepSupplyValidate.getSupplyArticleNumber();
        cmd.supplyAmountOutgoing = salesRepSupplyValidate.getSupplyAmountOutgoing();
        cmd.supplyValueOutgoing = salesRepSupplyValidate.getSupplyValueOutgoing();
        cmd.supplyArticleName = salesRepSupplyValidate.getSupplyArticleName();

        return cmd;
    }

    public static List<SalesRepSupplyValidateDTO> of(List<SalesRepSupplyValidate> salesRepSupplyValidates) {
        return salesRepSupplyValidates.stream().map(SalesRepSupplyValidateDTO::of).collect(Collectors.toList());
    }
}
