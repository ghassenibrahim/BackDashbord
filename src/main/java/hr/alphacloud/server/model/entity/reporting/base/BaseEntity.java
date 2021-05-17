package hr.alphacloud.server.model.entity.reporting.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Field;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    public static final Field ID_FIELD;

    static {
        try {
            ID_FIELD = BaseEntity.class.getDeclaredField("id");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in BaseEntity.");
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, precision = 19)
    private Long id;
}
