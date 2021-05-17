package hr.alphacloud.server.specification;

import hr.alphacloud.server.model.command.ReportFilterCommand;
import hr.alphacloud.server.model.entity.reporting.Report;
import org.springframework.data.jpa.domain.Specification;

public class ReportSpecification {
    public static Specification<Report> getReportFilterSpecification(ReportFilterCommand reportFilterCommand) {
        SharedSpecification<Report> sharedSpecification = new SharedSpecification<>();
        return Specification.where(
                sharedSpecification.getEqual(reportFilterCommand.getId(), "id")
                        .and(sharedSpecification.getEqual(reportFilterCommand.getCompanyId(), "companyId"))
                        .and(sharedSpecification.getLike(reportFilterCommand.getName(), "name"))
                        .and(sharedSpecification.getLike(reportFilterCommand.getReportTypeCode(), "reportTypeCode"))
        );
    }
}
