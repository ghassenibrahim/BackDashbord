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
public class SpendingLocation extends BaseEntity {

    public static final Field COMPANY_ID;
    public static final Field NAME_FIELD;

    static {
        try {
            COMPANY_ID = SpendingLocation.class.getDeclaredField("companyId");
            NAME_FIELD = SpendingLocation.class.getDeclaredField("name");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in SpendingLocation.");
        }
    }

    @Column(updatable = false, nullable = false)
    private Long companyId;

    @Column(nullable = false)
    private String name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "spendingLocation")
//    private Set<BruttoBalance> bruttoBalanceList = new HashSet<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "spendingLocation")
//    private Set<SalesRecap> salesRecapList = new HashSet<>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "spendingLocation")
//    private Set<SupplyAnalytics> supplyAnalyticsList = new HashSet<>();
}
