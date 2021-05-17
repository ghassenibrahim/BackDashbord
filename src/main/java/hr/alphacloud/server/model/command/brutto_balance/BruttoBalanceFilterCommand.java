package hr.alphacloud.server.model.command.brutto_balance;

import hr.alphacloud.server.model.command.base.AbstractReportFilterCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BruttoBalanceFilterCommand extends AbstractReportFilterCommand {
    private String kontoName;
}
