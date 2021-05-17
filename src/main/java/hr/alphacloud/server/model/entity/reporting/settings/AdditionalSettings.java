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
public class AdditionalSettings extends BaseEntity {
    public static final Field COMPANY_ID;
    public static final Field PRINCIPAL;
    public static final Field INTEREST;
    public static final Field RATE_PD;

    static {
        try {
            COMPANY_ID = AdditionalSettings.class.getDeclaredField("companyId");
            PRINCIPAL = AdditionalSettings.class.getDeclaredField("principal");
            INTEREST = AdditionalSettings.class.getDeclaredField("interest");
            RATE_PD = AdditionalSettings.class.getDeclaredField("ratePd");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in WarehouseType.");
        }
    }

    @Column(updatable = false, nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private Float principal;

    @Column(nullable = false)
    private Float interest;

    @Column(nullable = false)
    private Float ratePd;
}
