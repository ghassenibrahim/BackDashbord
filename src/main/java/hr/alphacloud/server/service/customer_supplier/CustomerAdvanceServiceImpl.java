package hr.alphacloud.server.service.customer_supplier;


import hr.alphacloud.server.model.entity.reporting.customer_supplier.CustomerAdvance;
import hr.alphacloud.server.repository.ReportRepository;
import hr.alphacloud.server.repository.customer_supplier.CustomerAdvancesRepository;
import hr.alphacloud.server.util.AbstractReportUtil;
import org.springframework.stereotype.Service;

@Service
public class CustomerAdvanceServiceImpl extends AbstractCustomerSupplierServiceImpl<CustomerAdvance, CustomerAdvancesRepository> {

    public CustomerAdvanceServiceImpl(ReportRepository reportRepository,
                                      AbstractReportUtil abstractReportUtil,
                                      CustomerAdvancesRepository repository) {
        super(reportRepository, abstractReportUtil, repository);
    }

}
