package hr.alphacloud.server.repository;

import hr.alphacloud.server.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    // Optional<User> findByEmailAndJwtToken(String username, String token);

    Optional<User> findByEmail(String username);

    void deleteByJwtToken(String fJwtToken);

    Optional<User> findByRefreshTokenAndJwtToken(String refreshToken, String jwtToken);

    void deleteByRefreshTokenAndJwtToken(String refreshToken, String jwtToken);

    Page<User> findAll(Specification<User> userSpecification, Pageable generatePagingAndSortingRequest);
}
