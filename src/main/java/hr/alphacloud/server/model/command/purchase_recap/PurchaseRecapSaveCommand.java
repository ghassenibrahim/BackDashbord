package hr.alphacloud.server.model.command.purchase_recap;

import hr.alphacloud.server.model.entity.reporting.PurchaseRecap;
import hr.alphacloud.server.model.entity.reporting.PurchaseRecapProperties;
import hr.alphacloud.server.model.entity.reporting.Report;
import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PurchaseRecapSaveCommand {

    private Long id;
    private Report report;
    private List<PurchaseRecapProperties> purchaseRecapProperties;
    private BusinessType businessType;
    private Premises premises;
    private SectorType sectorType;
    private SpendingLocation spendingLocation;

    public PurchaseRecap convertToEntity() {
        PurchaseRecap purchaseRecap = new PurchaseRecap();
        purchaseRecap.setId(this.id);
        purchaseRecap.setReport(this.report);
        purchaseRecap.setPurchaseRecapProperties(this.purchaseRecapProperties);
        purchaseRecap.setBusinessType(this.businessType);
        purchaseRecap.setPremises(this.premises);
        purchaseRecap.setSectorType(this.sectorType);
        purchaseRecap.setSpendingLocation(this.spendingLocation);
        return purchaseRecap;
    }
}