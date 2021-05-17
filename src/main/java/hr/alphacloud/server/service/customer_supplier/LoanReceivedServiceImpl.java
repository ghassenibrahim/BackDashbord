package hr.alphacloud.server.service.customer_supplier;

import hr.alphacloud.server.model.entity.reporting.customer_supplier.LoanReceived;
import hr.alphacloud.server.repository.ReportRepository;
import hr.alphacloud.server.repository.customer_supplier.LoanReceivedRepository;
import hr.alphacloud.server.util.AbstractReportUtil;
import org.springframework.stereotype.Service;

@Service
public class LoanReceivedServiceImpl extends AbstractCustomerSupplierServiceImpl<LoanReceived, LoanReceivedRepository> {

    public LoanReceivedServiceImpl(ReportRepository reportRepository,
                                   AbstractReportUtil abstractReportUtil,
                                   LoanReceivedRepository repository) {
        super(reportRepository, abstractReportUtil, repository);
    }

}
