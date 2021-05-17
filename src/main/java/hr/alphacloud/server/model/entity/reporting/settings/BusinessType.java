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
public class BusinessType extends BaseEntity {
    public static final Field COMPANY_ID;
    public static final Field VALUE_FIELD;
    public static final Field LOCALE_FIELD;

    static {
        try {
            COMPANY_ID = BusinessType.class.getDeclaredField("companyId");
            VALUE_FIELD = BusinessType.class.getDeclaredField("value");
            LOCALE_FIELD = BusinessType.class.getDeclaredField("locale");
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

//    @OneToMany(mappedBy = "businessType")
//    private Set<BruttoBalance> bruttoBalanceList;

//    @OneToMany(mappedBy = "businessType")
//    private Set<SalesRecap> salesRecapsList;
//
//    @OneToMany(mappedBy = "businessType")
//    private List<SupplyAnalytics> supplyAnalyticsList;
}
