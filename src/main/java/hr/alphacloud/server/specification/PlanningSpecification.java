package hr.alphacloud.server.specification;

import hr.alphacloud.server.model.command.planning.PlanFilterCommand;
import hr.alphacloud.server.model.entity.reporting.planning.Plan;
import org.springframework.data.jpa.domain.Specification;

public class PlanningSpecification {
    public static Specification<Plan> getPlanFilterSpecification(PlanFilterCommand planFilterCommand) {
        SharedSpecification<Plan> sharedSpecification = new SharedSpecification<>();
        return Specification.where(
                sharedSpecification.getEqual(planFilterCommand.getId(), "id")
                        .and(sharedSpecification.getEqual(planFilterCommand.getCompanyId(), "companyId"))
                        .or(sharedSpecification.getLike(planFilterCommand.getName(), "name"))
        );
    }
}
