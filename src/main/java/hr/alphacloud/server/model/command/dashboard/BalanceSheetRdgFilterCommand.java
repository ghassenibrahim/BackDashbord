package hr.alphacloud.server.model.command.dashboard;

import hr.alphacloud.server.model.command.validate.BaseReportingFilterCommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceSheetRdgFilterCommand extends BaseReportingFilterCommand {
    private String sheetType;
}
