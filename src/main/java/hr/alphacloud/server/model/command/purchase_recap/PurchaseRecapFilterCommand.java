package hr.alphacloud.server.model.command.purchase_recap;

import hr.alphacloud.server.model.command.base.AbstractReportFilterCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PurchaseRecapFilterCommand extends AbstractReportFilterCommand {
    private String articleName;
    private Integer previousYear;
    private Integer currentYear;
}
