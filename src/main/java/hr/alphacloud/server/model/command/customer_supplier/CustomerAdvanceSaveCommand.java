package hr.alphacloud.server.model.command.customer_supplier;

import hr.alphacloud.server.model.command.customer_supplier.base.AbstractCustomerSupplierSaveCommand;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.CustomerAdvance;
import lombok.Getter;

@Getter
public class CustomerAdvanceSaveCommand extends AbstractCustomerSupplierSaveCommand<CustomerAdvance> {

    @Override
    public CustomerAdvance convertToEntity() {
        CustomerAdvance customerAdvance = new CustomerAdvance();
        assignEntity(customerAdvance);
        return customerAdvance;
    }
}
