package hr.alphacloud.server.model.command.validate;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseReportingFilterCommand {
    private Long companyId;
    private Long reportId;
    private String reportType;
    private Date dateFrom;
    private Date dateTo;
    private Long spendingLocationId;
    private Long premisesId;
    private Long sectorTypeId;
}
