package hr.alphacloud.server.model.specifications;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

public class SharedSpecification {

    private SharedSpecification() {
    }

    public static <X> Specification<X> getStringValueEquals(Class<X> entity, String value, Field... properties) {
        return (root, query, criteriaBuilder) -> {
            if (value != null && !value.trim().isEmpty()) {
                return criteriaBuilder.equal(criteriaBuilder.lower(getGenericValue(root, properties)), value.toLowerCase());
            }
            return null;
        };
    }

    public static <X, N extends Number> Specification<X> getNumericValueEqual(Class<X> entity, N value, Field... properties) {
        return (Root<X> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (criteriaValueExists(value)) {
                return criteriaBuilder.equal(getGenericValue(root, properties), value);
            }
            return null;
        };
    }

    public static <X> Specification<X> getDateFrom(Class<X> entity, LocalDateTime value, Field... properties) {
        return (Root<X> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (criteriaValueExists(value)) {
                return criteriaBuilder.greaterThanOrEqualTo(getGenericValue(root, properties), value);
            }
            return null;
        };
    }

    public static <X> Specification<X> getDateTo(Class<X> entity, LocalDateTime value, Field... properties) {
        return (Root<X> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (criteriaValueExists(value)) {
                return criteriaBuilder.lessThanOrEqualTo(getGenericValue(root, properties), value);
            }
            return null;
        };
    }

    private static <T, X> Path<X> getGenericValue(Root<T> root, Field... fields) {
        Path path = root;
        for (Field property : fields) {
            path = path.get(property.getName());
        }
        return (Path<X>) path;
    }

    private static <T> boolean criteriaValueExists(T property) {
        return property != null;
    }
}
