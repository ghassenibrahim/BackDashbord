package hr.alphacloud.server.repository;

import hr.alphacloud.server.model.entity.reporting.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long>, JpaSpecificationExecutor<Report> {

    Optional<Report> findFirstByCompanyIdOrderByAddedOnAsc(Long companyId);

    Optional<Report> findByIdAndCompanyId(Long id, Long companyId);

}
