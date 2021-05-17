package hr.alphacloud.server.model.command.customer_supplier.base;

import hr.alphacloud.server.model.entity.reporting.Report;
import hr.alphacloud.server.model.entity.reporting.base.AbstractCustomerSupplier;
import hr.alphacloud.server.model.entity.reporting.base.CustomerSupplierProperties;
import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class AbstractCustomerSupplierSaveCommand<T extends AbstractCustomerSupplier> {

    private Long id;
    private Report report;
    private List<CustomerSupplierProperties> customerSupplierProperties;
    private Premises premises;
    private SectorType sectorType;
    private SpendingLocation spendingLocation;
    private AccountBook accountBook;

    protected void assignEntity(T entity) {
        entity.setId(this.id);
        entity.setReport(this.report);
        entity.setCustomerSupplierProperties(this.customerSupplierProperties);
        entity.setPremises(this.premises);
        entity.setSectorType(this.sectorType);
        entity.setSpendingLocation(this.spendingLocation);
        entity.setAccountBook(this.accountBook);

    }

    public abstract T convertToEntity();
}
