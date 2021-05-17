package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.AccountBookDTO;
import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.model.entity.reporting.base.AbstractReport;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class AbstractReportDTO implements Serializable {
    protected Long id;
    protected Long companyId;
    protected ReportDTO report;
    protected SpendingLocationDTO spendingLocation;
    protected PremisesDTO premises;
    protected SectorTypeDTO sectorType;
    protected AccountBookDTO accountBook;
    protected String importType;

    protected void assignAbstractReportFields(AbstractReport abstractReport) {
        this.id = abstractReport.getId();
        this.companyId = abstractReport.getReport().getCompanyId();
        this.report = ReportDTO.of(abstractReport.getReport());
        this.spendingLocation = SpendingLocationDTO.of(abstractReport.getSpendingLocation());
        this.premises = PremisesDTO.fromEntity(abstractReport.getPremises());
        this.sectorType = SectorTypeDTO.fromEntity(abstractReport.getSectorType());

    }
}
