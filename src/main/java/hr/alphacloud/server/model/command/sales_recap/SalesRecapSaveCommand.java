package hr.alphacloud.server.model.command.sales_recap;

import hr.alphacloud.server.model.entity.reporting.Report;
import hr.alphacloud.server.model.entity.reporting.SalesRecap;
import hr.alphacloud.server.model.entity.reporting.SalesRecapProperties;
import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SalesRecapSaveCommand {

    private Long id;
    private Report report;
    private List<SalesRecapProperties> salesRecapProperties;
    private BusinessType businessType;
    private Premises premises;
    private SectorType sectorType;
    private SpendingLocation spendingLocation;

    public SalesRecap convertToEntity() {
        SalesRecap salesRecap = new SalesRecap();
        salesRecap.setId(this.id);
        salesRecap.setReport(this.report);
        salesRecap.setSalesRecapProperties(this.salesRecapProperties);
        salesRecap.setBusinessType(this.businessType);
        salesRecap.setPremises(this.premises);
        salesRecap.setSectorType(this.sectorType);
        salesRecap.setSpendingLocation(this.spendingLocation);
        return salesRecap;
    }
}