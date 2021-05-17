package hr.alphacloud.server.specification;

import hr.alphacloud.server.model.command.account.UserFilterCommand;
import hr.alphacloud.server.model.entity.User;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
    public static Specification<User> getUserSpecification(UserFilterCommand dealFilterCommand) {
        SharedSpecification<User> sharedSpecification = new SharedSpecification<>();
        return Specification.where(
                sharedSpecification.getEqual(dealFilterCommand.getId(), "id")
                        .and(sharedSpecification.getEqual(dealFilterCommand.getCompanyId(), "company", "id"))
                        .and(sharedSpecification.getLike(dealFilterCommand.getEmail(), "email"))
                        .and(sharedSpecification.getLike(dealFilterCommand.getFirstName(), "firstName"))
                        .and(sharedSpecification.getLike(dealFilterCommand.getSurname(), "lastName"))
                        .and(sharedSpecification.getLike(dealFilterCommand.getPosition(), "position"))
        );
    }
}
