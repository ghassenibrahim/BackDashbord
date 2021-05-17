package hr.alphacloud.server.model.entity.reporting.settings;

import hr.alphacloud.server.model.entity.reporting.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.lang.reflect.Field;

@Getter
@Setter
@Entity
public class WarehouseType extends BaseEntity {
    public static final Field COMPANY_ID;
    public static final Field VALUE_FIELD;
    public static final Field LOCALE_FIELD;

    static {
        try {
            COMPANY_ID = WarehouseType.class.getDeclaredField("companyId");
            VALUE_FIELD = WarehouseType.class.getDeclaredField("value");
            LOCALE_FIELD = WarehouseType.class.getDeclaredField("locale");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in WarehouseType.");
        }
    }

    @Column(updatable = false, nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private String locale;

    @ManyToOne
    @JoinColumn(name = "initStateBalanceCode")
    private AccountBook initStateBalanceCode;

    @ManyToOne
    @JoinColumn(name = "finalStateBalanceCode")
    private AccountBook finalStateBalanceCode;

}
