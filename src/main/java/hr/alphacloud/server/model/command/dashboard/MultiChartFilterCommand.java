package hr.alphacloud.server.model.command.dashboard;

import hr.alphacloud.server.model.command.validate.BaseReportingFilterCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MultiChartFilterCommand extends BaseReportingFilterCommand {
    private String excelType;
    private String chartColumn;
    private String chartRow;
    private String chartValue;
    private String colGroupBy;
    private Date dateType;
}
