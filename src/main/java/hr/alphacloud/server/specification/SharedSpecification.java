package hr.alphacloud.server.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

class SharedSpecification<T> {

    Specification<T> getLike(String value, String... properties) {
        return (root, query, criteriaBuilder) -> {
            if (checkIfPropertyHasValue(value)) {
                return criteriaBuilder.like(criteriaBuilder.lower(getGenericValue(root, String.class, properties)),
                        "%" + value.toLowerCase() + "%");
            }
            return null;
        };
    }

    <K> Specification<T> getIn(List<K> values, Class<K> clazz, String... properties) {
        return (root, query, builder) -> {
            if (checkIfPropertyHasValue(values) && !values.isEmpty()) {
                CriteriaBuilder.In<K> in = builder.in(getGenericValue(root, clazz, properties));
                for (K value : values) {
                    in = in.value(value);
                }
                return in;
            }
            return null;
        };
    }

    Specification<T> isValueNull(Boolean value, String... properties) {
        return (root, query, criteriaBuilder) -> {
            if (value) {
                return criteriaBuilder.isNull(getGenericValue(root, String.class, properties));
            }
            return null;
        };
    }

    <K> Specification<T> getEqual(K value, String... properties) {
        return (root, query, criteriaBuilder) -> {
            if (checkIfPropertyHasValue(value)) {
                return criteriaBuilder.equal(getGenericValue(root, String.class, properties), value);
            }
            return null;
        };
    }

    Specification<T> getDateEqual(LocalDate value, String... properties) {
        return (root, query, criteriaBuilder) -> {
            if (checkIfPropertyHasValue(value)) {
                return criteriaBuilder.equal(getGenericValue(root, LocalDate.class, properties), value);
            }
            return null;
        };
    }

    Specification<T> getDateFrom(Date value, String... properties) {
        return (root, query, criteriaBuilder) -> {
            if (checkIfPropertyHasValue(value)) {
                return criteriaBuilder.greaterThanOrEqualTo(getGenericValue(root, Date.class, properties), value);
            }
            return null;
        };
    }

    Specification<T> getDateTo(Date value, String... properties) {
        return (root, query, criteriaBuilder) -> {
            if (checkIfPropertyHasValue(value)) {
                return criteriaBuilder.lessThanOrEqualTo(getGenericValue(root, Date.class, properties), value);
            }
            return null;
        };
    }

    Specification<T> getDateBetween(Date dateFrom, Date dateTo, String... properties) {
        return (root, query, criteriaBuilder) -> {
            if (checkIfPropertyHasValue(dateFrom) && checkIfPropertyHasValue(dateTo)) {
                return criteriaBuilder.between(getGenericValue(root, Date.class, properties), dateFrom, dateTo);
            }
            return null;
        };
    }

    private <K> Expression<K> getGenericValue(Root<T> root, Class<K> type, String... values) {
        Path path = root;
        for (String value : values) {
            path = path.get(value);
        }
        return path;
    }


    private boolean checkIfPropertyHasValue(String property) {
        return (property != null && !property.isEmpty());
    }

    private <A> boolean checkIfPropertyHasValue(A property) {
        return property != null;
    }
}
