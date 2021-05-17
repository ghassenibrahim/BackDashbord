package hr.alphacloud.server.service.customer_supplier;

import hr.alphacloud.server.model.entity.reporting.customer_supplier.Customer;
import hr.alphacloud.server.repository.ReportRepository;
import hr.alphacloud.server.repository.customer_supplier.CustomerRepository;
import hr.alphacloud.server.util.AbstractReportUtil;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends AbstractCustomerSupplierServiceImpl<Customer, CustomerRepository> {

    public CustomerServiceImpl(ReportRepository reportRepository,
                               AbstractReportUtil abstractReportUtil,
                               CustomerRepository repository) {
        super(reportRepository, abstractReportUtil, repository);
    }

}