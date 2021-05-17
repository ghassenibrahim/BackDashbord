package hr.alphacloud.server.model.command.customer_supplier;

import hr.alphacloud.server.model.command.customer_supplier.base.AbstractCustomerSupplierSaveCommand;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.Supplier;
import lombok.Getter;

@Getter
public class SupplierSaveCommand extends AbstractCustomerSupplierSaveCommand<Supplier> {

    @Override
    public Supplier convertToEntity() {
        Supplier supplier = new Supplier();
        assignEntity(supplier);
        return supplier;
    }
}
