package hr.alphacloud.server.service.customer_supplier;


import hr.alphacloud.server.model.entity.reporting.customer_supplier.LoanGiven;
import hr.alphacloud.server.repository.ReportRepository;
import hr.alphacloud.server.repository.customer_supplier.LoanGivenRepository;
import hr.alphacloud.server.util.AbstractReportUtil;
import org.springframework.stereotype.Service;

@Service
public class LoanGivenServiceImpl extends AbstractCustomerSupplierServiceImpl<LoanGiven, LoanGivenRepository> {

    public LoanGivenServiceImpl(ReportRepository reportRepository,
                                AbstractReportUtil abstractReportUtil,
                                LoanGivenRepository repository) {
        super(reportRepository, abstractReportUtil, repository);
    }

}
