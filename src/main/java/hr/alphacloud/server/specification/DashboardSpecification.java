package hr.alphacloud.server.specification;

import hr.alphacloud.server.model.command.dashboard.SpinAnalyticsFilterCommand;
import hr.alphacloud.server.model.entity.reporting.ReceivableMaturity;
import org.springframework.data.jpa.domain.Specification;

public class DashboardSpecification {

    public static Specification<ReceivableMaturity> getSpinAnalyticFilterSpecification(SpinAnalyticsFilterCommand spinAnalyticsFilterCommand) {
        SharedSpecification<ReceivableMaturity> sharedSpecification = new SharedSpecification<>();
        return Specification.where(
                sharedSpecification.getEqual(spinAnalyticsFilterCommand.getImportType(), "importType")
                        .or(sharedSpecification.getDateBetween(spinAnalyticsFilterCommand.getDateFrom(), spinAnalyticsFilterCommand.getDateTo(),
                                "dateFrom"))
                        .or(sharedSpecification.getDateBetween(spinAnalyticsFilterCommand.getDateFrom(), spinAnalyticsFilterCommand.getDateTo(),
                                "dateTo"))
                        .or(sharedSpecification.getEqual(spinAnalyticsFilterCommand.getPremisesId(), "premises", "id"))
                        .or(sharedSpecification.getEqual(spinAnalyticsFilterCommand.getSectorTypeId(), "sectorType", "id"))
                        .or(sharedSpecification.getEqual(spinAnalyticsFilterCommand.getSpendingLocationId(), "spendingLocation", "id"))
        );
    }

}
