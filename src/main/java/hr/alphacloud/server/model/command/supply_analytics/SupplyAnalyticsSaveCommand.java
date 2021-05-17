package hr.alphacloud.server.model.command.supply_analytics;

import hr.alphacloud.server.model.entity.reporting.Report;
import hr.alphacloud.server.model.entity.reporting.SupplyAnalytics;
import hr.alphacloud.server.model.entity.reporting.SupplyAnalyticsProperties;
import hr.alphacloud.server.model.entity.reporting.settings.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SupplyAnalyticsSaveCommand {

    private Long id;
    private Report report;
    private Premises premises;
    private SectorType sectorType;
    private SpendingLocation spendingLocation;
    private BusinessType businessType;
    private WarehouseType warehouseType;
    private AccountBook accountBook;
    private List<SupplyAnalyticsProperties> supplyAnalyticsProperties;

    public SupplyAnalytics convertToEntity() {
        SupplyAnalytics supplyAnalytics = new SupplyAnalytics();
        supplyAnalytics.setId(this.id);
        supplyAnalytics.setReport(this.report);
        supplyAnalytics.setSupplyAnalyticsProperties(this.supplyAnalyticsProperties);
        supplyAnalytics.setPremises(this.premises);
        supplyAnalytics.setSectorType(this.sectorType);
        supplyAnalytics.setWarehouseType(this.warehouseType);
        supplyAnalytics.setBusinessType(this.businessType);
        supplyAnalytics.setAccountBook(this.accountBook);
        supplyAnalytics.setSpendingLocation(this.spendingLocation);
        return supplyAnalytics;
    }
}