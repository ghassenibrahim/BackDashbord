package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountBookRepository extends JpaRepository<AccountBook, Long>, JpaSpecificationExecutor<AccountBook> {

    Optional<AccountBook> findByIdAndCompanyId(Long id, Long companyId);

    List<AccountBook> findAllByCompanyId(Long companyId);

}
