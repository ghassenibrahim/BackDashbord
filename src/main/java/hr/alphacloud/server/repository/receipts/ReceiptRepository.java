package hr.alphacloud.server.repository.receipts;

import hr.alphacloud.server.model.entity.reporting.receipts.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>, JpaSpecificationExecutor<Receipt> {

}
