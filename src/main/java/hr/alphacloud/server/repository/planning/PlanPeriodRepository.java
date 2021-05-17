package hr.alphacloud.server.repository.planning;

import hr.alphacloud.server.model.entity.reporting.planning.PlanPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlanPeriodRepository extends JpaRepository<PlanPeriod, Long> {
}
