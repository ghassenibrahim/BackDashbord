package hr.alphacloud.server.model.command.customer_supplier;

import hr.alphacloud.server.model.command.customer_supplier.base.AbstractCustomerSupplierSaveCommand;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.Customer;
import lombok.Getter;

@Getter
public class CustomerSaveCommand extends AbstractCustomerSupplierSaveCommand<Customer> {

    @Override
    public Customer convertToEntity() {
        Customer customer = new Customer();
        assignEntity(customer);
        return customer;
    }
}
