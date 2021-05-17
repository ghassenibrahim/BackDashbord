package hr.alphacloud.server.model.command.base;

import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractReportFilterCommand {
    @NotNull
    private Long companyId;
    @NotNull
    private Long reportId;
    private Long id;
    private SpendingLocation spendingLocation;
    private BusinessType businessType;
    private Premises premises;
    private SectorType sectorType;
}
