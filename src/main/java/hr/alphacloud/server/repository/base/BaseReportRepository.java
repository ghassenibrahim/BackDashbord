package hr.alphacloud.server.repository.base;

import hr.alphacloud.server.model.entity.reporting.base.AbstractReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Interface to extended by customer_supplier Repositories which pass their Entities inside this interface
 */

@NoRepositoryBean
public interface BaseReportRepository<T extends AbstractReport> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

    List<T> findAllByReportId(Long reportId);

}
