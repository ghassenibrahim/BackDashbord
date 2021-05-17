package hr.alphacloud.server.specification;

import hr.alphacloud.server.model.command.account.CompanyFilter;
import hr.alphacloud.server.model.entity.Company;
import org.springframework.data.jpa.domain.Specification;

public class CompanySpecification {
    public static Specification<Company> getCompanyClientFilterSpecification(CompanyFilter dealFilterCommand) {
        SharedSpecification<Company> sharedSpecification = new SharedSpecification<>();
        return Specification.where(
                sharedSpecification.getEqual(dealFilterCommand.getId(), "id")
                        .and(sharedSpecification.getLike(dealFilterCommand.getFullName(), "fullName"))
                        .and(sharedSpecification.getEqual(dealFilterCommand.getOib(), "oib"))
                        .and(sharedSpecification.getEqual(dealFilterCommand.getMbs(), "mbs"))
                        .and(sharedSpecification.getLike(dealFilterCommand.getEmail(), "email"))
                        .and(sharedSpecification.getLike(dealFilterCommand.getAddress(), "address"))
        );
    }
}
