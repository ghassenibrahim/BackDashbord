package hr.alphacloud.server.service.customer_supplier;


import hr.alphacloud.server.model.entity.reporting.customer_supplier.SupplierAdvance;
import hr.alphacloud.server.repository.ReportRepository;
import hr.alphacloud.server.repository.customer_supplier.SupplierAdvanceRepository;
import hr.alphacloud.server.util.AbstractReportUtil;
import org.springframework.stereotype.Service;

@Service
public class SupplierAdvanceServiceImpl extends AbstractCustomerSupplierServiceImpl<SupplierAdvance, SupplierAdvanceRepository> {

    public SupplierAdvanceServiceImpl(ReportRepository reportRepository,
                                      AbstractReportUtil abstractReportUtil,
                                      SupplierAdvanceRepository repository) {
        super(reportRepository, abstractReportUtil, repository);
    }

}
