package hr.alphacloud.server.model.dto.report_validate.bruto_supply;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplyInitBalanceDTO {

    private Long brutoInitCode;
    private String brutoInitArticleName;
    private BigDecimal brutoInitStateBalance;
    private BigDecimal supplyFinalOutgoing;

    public static SupplyInitBalanceDTO of(BrutoSupplyValidate brutoSupplyValidate) {
        SupplyInitBalanceDTO cmd = new SupplyInitBalanceDTO();
        cmd.brutoInitCode = brutoSupplyValidate.getbrutoInitCode();
        cmd.brutoInitArticleName = brutoSupplyValidate.getBrutoInitArticleName();
        cmd.brutoInitStateBalance = brutoSupplyValidate.getBrutoInitStateBalance();
        cmd.supplyFinalOutgoing = brutoSupplyValidate.getSupplyFinalOutgoing();

        return cmd;
    }

}
