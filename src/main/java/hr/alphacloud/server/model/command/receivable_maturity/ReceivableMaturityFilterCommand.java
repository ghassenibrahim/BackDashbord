package hr.alphacloud.server.model.command.receivable_maturity;

import hr.alphacloud.server.model.command.base.AbstractReportFilterCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReceivableMaturityFilterCommand extends AbstractReportFilterCommand {
    private String name;
}
