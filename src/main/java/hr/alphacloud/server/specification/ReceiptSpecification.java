package hr.alphacloud.server.specification;

import hr.alphacloud.server.model.command.receipts.ReceiptFilterCommand;
import hr.alphacloud.server.model.entity.reporting.receipts.Receipt;
import org.springframework.data.jpa.domain.Specification;

public class ReceiptSpecification {
    public static Specification<Receipt> getReceiptFilterSpecification(ReceiptFilterCommand receiptFilterCommand) {
        SharedSpecification<Receipt> sharedSpecification = new SharedSpecification<>();
        return Specification.where(
                sharedSpecification.getEqual(receiptFilterCommand.getId(), "id")
                        .and(sharedSpecification.getEqual(receiptFilterCommand.getCompanyId(), "companyId"))
                        .and(sharedSpecification.getLike(receiptFilterCommand.getName(), "name"))
        );
    }
}
