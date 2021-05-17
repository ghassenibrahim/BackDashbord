package hr.alphacloud.server.model.dto.report_validate.bruto_supply;

import java.math.BigDecimal;

public interface BrutoSupplyValidate {


    String getBrutoInitArticleName();

    String getBrutoFinalArticleName();

    Long getbrutoInitCode();

    Long getbrutoFinalCode();

    Long getBrutoId();

    Long getSupplyId();

    BigDecimal getBrutoInitStateBalance();

    BigDecimal getSupplyFinalOutgoing();

    BigDecimal getBrutoFinalStateBalance();

    BigDecimal getSupplySaldoFinal();

}
