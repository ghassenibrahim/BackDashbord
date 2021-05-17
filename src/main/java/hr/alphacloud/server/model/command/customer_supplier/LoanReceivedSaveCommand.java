package hr.alphacloud.server.model.command.customer_supplier;

import hr.alphacloud.server.model.command.customer_supplier.base.AbstractCustomerSupplierSaveCommand;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.LoanReceived;
import lombok.Getter;

@Getter
public class LoanReceivedSaveCommand extends AbstractCustomerSupplierSaveCommand<LoanReceived> {

    @Override
    public LoanReceived convertToEntity() {
        LoanReceived loanReceived = new LoanReceived();
        assignEntity(loanReceived);
        return loanReceived;
    }
}
