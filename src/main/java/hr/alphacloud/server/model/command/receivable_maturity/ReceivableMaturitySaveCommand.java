package hr.alphacloud.server.model.command.receivable_maturity;

import hr.alphacloud.server.model.entity.reporting.ReceivableMaturity;
import hr.alphacloud.server.model.entity.reporting.ReceivableMaturityProperties;
import hr.alphacloud.server.model.entity.reporting.Report;
import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReceivableMaturitySaveCommand {

    private Long id;
    private List<ReceivableMaturityProperties> receivableMaturityProperties;
    private String importType;
    private Date dateFrom;
    private Date dateTo;
    private Date dateCompleted;
    private Report report;
    private BusinessType businessType;
    private Premises premises;
    private SectorType sectorType;
    private SpendingLocation spendingLocation;

    public ReceivableMaturity convertToEntity() {
        ReceivableMaturity receivableMaturity = new ReceivableMaturity();
        receivableMaturity.setId(this.id);
        receivableMaturity.setReceivableMaturityProperties(this.receivableMaturityProperties);
        receivableMaturity.setImportType(this.importType);
        receivableMaturity.setDateFrom(this.dateFrom);
        receivableMaturity.setDateTo(this.dateTo);
        receivableMaturity.setDateCompleted(this.dateCompleted);
        receivableMaturity.setReport(this.report);
        receivableMaturity.setBusinessType(this.businessType);
        receivableMaturity.setPremises(this.premises);
        receivableMaturity.setSectorType(this.sectorType);
        receivableMaturity.setSpendingLocation(this.spendingLocation);
        return receivableMaturity;
    }
}
