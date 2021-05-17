package hr.alphacloud.server.repository.customer_supplier;


import hr.alphacloud.server.model.entity.reporting.customer_supplier.LoanReceived;
import hr.alphacloud.server.repository.base.BaseReportRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanReceivedRepository extends BaseReportRepository<LoanReceived> {
}
