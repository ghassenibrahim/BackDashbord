package hr.alphacloud.server.model.command.planning;

import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class PlanFilterCommand {
    private Long id;
    private Long companyId;
    private String reportType;
    private Date dateFrom;
    private Date dateTo;
    private SpendingLocation spendingLocation;
    private String name;
}
