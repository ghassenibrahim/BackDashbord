package hr.alphacloud.server.model.command.base;

import hr.alphacloud.server.model.entity.reporting.Report;
import hr.alphacloud.server.model.entity.reporting.base.AbstractReport;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class AbstractReportSaveCommand<T extends AbstractReport> {
    protected Long id;
    @NotNull
    protected Long companyId;
    @NotNull
    protected Long reportId;
    protected String locale;
    protected Long spendingLocationId;
    protected Long businessTypeId;
    protected Long warehouseTypeId;
    protected Long premisesNameId;
    protected Long sectorTypeId;

    public abstract T convertToEntity(Report report);
}
