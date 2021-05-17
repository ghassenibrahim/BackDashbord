package hr.alphacloud.server.repository.planning;

import hr.alphacloud.server.model.entity.reporting.planning.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningRepository extends JpaRepository<Plan, Long>, JpaSpecificationExecutor<Plan> {

}
