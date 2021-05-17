package hr.alphacloud.server.model.entity.reporting.settings;

import hr.alphacloud.server.model.entity.reporting.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Field;

@Getter
@Setter
@Entity
public class AccountBook extends BaseEntity {
    public static final Field COMPANY_ID;
    public static final Field CODE;
    public static final Field NAME;

    static {
        try {
            COMPANY_ID = AccountBook.class.getDeclaredField("companyId");
            CODE = AccountBook.class.getDeclaredField("code");
            NAME = AccountBook.class.getDeclaredField("name");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in WarehouseType.");
        }
    }

    @Column(updatable = false, nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Integer code;

    @Column(nullable = false)
    private String name;

}