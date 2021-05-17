package hr.alphacloud.server.model.command.brutto_balance;

import hr.alphacloud.server.model.entity.reporting.BruttoBalance;
import hr.alphacloud.server.model.entity.reporting.BruttoBalanceProperties;
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
public class BruttoBalanceSaveCommand {

    private Long id;
    private Report report;
    private List<BruttoBalanceProperties> bruttoBalanceProperties;
    private BusinessType businessType;
    private Premises premises;
    private SectorType sectorType;
    private SpendingLocation spendingLocation;

    public BruttoBalance convertToEntity() {
        BruttoBalance bruttoBalance = new BruttoBalance();
        bruttoBalance.setId(this.id);
        bruttoBalance.setReport(this.report);
        bruttoBalance.setBruttoBalanceProperties(this.bruttoBalanceProperties);
        bruttoBalance.setBusinessType(this.businessType);
        bruttoBalance.setPremises(this.premises);
        bruttoBalance.setSpendingLocation(this.spendingLocation);
        bruttoBalance.setSectorType(this.sectorType);
        return bruttoBalance;
    }

}