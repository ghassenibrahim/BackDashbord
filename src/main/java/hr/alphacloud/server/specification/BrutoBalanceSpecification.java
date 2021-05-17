package hr.alphacloud.server.specification;

import hr.alphacloud.server.model.command.brutto_balance.BruttoBalanceFilterCommand;
import hr.alphacloud.server.model.entity.reporting.BruttoBalance;
import org.springframework.data.jpa.domain.Specification;

public class BrutoBalanceSpecification {
    public static Specification<BruttoBalance> getBrutoBalanceFilterSpecification(BruttoBalanceFilterCommand bruttoBalanceFilterCommand) {
        SharedSpecification<BruttoBalance> sharedSpecification = new SharedSpecification<>();
        return Specification.where(
                sharedSpecification.getEqual(bruttoBalanceFilterCommand.getId(), "id")
                        .and(sharedSpecification.getEqual(bruttoBalanceFilterCommand.getReportId(), "report"))
                        .and(sharedSpecification.getEqual(bruttoBalanceFilterCommand.getBusinessType(), "businessType"))
                        .and(sharedSpecification.getEqual(bruttoBalanceFilterCommand.getBusinessType(), "spendingLocation"))
                        .and(sharedSpecification.getEqual(bruttoBalanceFilterCommand.getBusinessType(), "premises"))
                        .and(sharedSpecification.getEqual(bruttoBalanceFilterCommand.getBusinessType(), "sectorType"))
        );
    }
}