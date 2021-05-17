package hr.alphacloud.server.model.dto.report_validate.bruto_balance;

import java.math.BigDecimal;

public interface BrutoBalanceValidate {

    Long getReportId();

    Long getId();

    BigDecimal getInitStateBalance();

    BigDecimal getFinalStateBalance();
}
