package hr.alphacloud.server.model.dto.report_validate.bruto_supply;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SupplyFinalBalanceDTO {

    private Long brutoFinalCode;
    private String brutoFinalArticleName;
    private BigDecimal brutoFinalStateBalance;
    private BigDecimal supplyFinalSaldo;

    public static SupplyFinalBalanceDTO of(BrutoSupplyValidate brutoSupplyValidate) {
        SupplyFinalBalanceDTO cmd = new SupplyFinalBalanceDTO();
        cmd.brutoFinalCode = brutoSupplyValidate.getbrutoFinalCode();
        cmd.brutoFinalArticleName = brutoSupplyValidate.getBrutoFinalArticleName();
        cmd.brutoFinalStateBalance = brutoSupplyValidate.getBrutoFinalStateBalance();
        cmd.supplyFinalSaldo = brutoSupplyValidate.getSupplySaldoFinal();

        return cmd;
    }
}
