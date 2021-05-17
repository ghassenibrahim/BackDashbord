package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.WarehouseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseTypeRepository extends JpaRepository<WarehouseType, Long>, JpaSpecificationExecutor<WarehouseType> {

    Optional<WarehouseType> findByIdAndCompanyId(Long id, Long companyId);

}
