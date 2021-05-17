package hr.alphacloud.server.model.command.supply_analytics;

import hr.alphacloud.server.model.command.base.AbstractReportFilterCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SupplyAnalyticsFilterCommand extends AbstractReportFilterCommand {
    private String articleName;
    private String warehouseTypeId;
}
