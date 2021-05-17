package hr.alphacloud.server.service.customer_supplier;

import hr.alphacloud.server.model.entity.reporting.customer_supplier.Supplier;
import hr.alphacloud.server.repository.ReportRepository;
import hr.alphacloud.server.repository.customer_supplier.SupplierRepository;
import hr.alphacloud.server.util.AbstractReportUtil;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImpl extends AbstractCustomerSupplierServiceImpl<Supplier, SupplierRepository> {

    public SupplierServiceImpl(ReportRepository reportRepository,
                               AbstractReportUtil abstractReportUtil,
                               SupplierRepository repository) {
        super(reportRepository, abstractReportUtil, repository);
    }

}
