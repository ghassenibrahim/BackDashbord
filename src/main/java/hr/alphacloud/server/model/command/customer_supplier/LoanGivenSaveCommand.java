package hr.alphacloud.server.model.command.customer_supplier;

import hr.alphacloud.server.model.command.customer_supplier.base.AbstractCustomerSupplierSaveCommand;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.LoanGiven;
import lombok.Getter;

@Getter
public class LoanGivenSaveCommand extends AbstractCustomerSupplierSaveCommand<LoanGiven> {

    @Override
    public LoanGiven convertToEntity() {
        LoanGiven loanGiven = new LoanGiven();
        assignEntity(loanGiven);
        return loanGiven;
    }
}
