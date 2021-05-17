package hr.alphacloud.server.model.command.customer_supplier;

import hr.alphacloud.server.model.command.customer_supplier.base.AbstractCustomerSupplierSaveCommand;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.SupplierAdvance;
import lombok.Getter;

@Getter
public class SupplierAdvanceSaveCommand extends AbstractCustomerSupplierSaveCommand<SupplierAdvance> {

    @Override
    public SupplierAdvance convertToEntity() {
        SupplierAdvance supplierAdvance = new SupplierAdvance();
        assignEntity(supplierAdvance);
        return supplierAdvance;
    }
}
