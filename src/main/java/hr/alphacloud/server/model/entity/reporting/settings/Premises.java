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
public class Premises extends BaseEntity {
    public static final Field COMPANY_ID;
    public static final Field NAME_FIELD;

    static {
        try {
            COMPANY_ID = ImportSettings.class.getDeclaredField("companyId");
            NAME_FIELD = ImportSettings.class.getDeclaredField("name");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in ImportSettings.");
        }
    }

    @Column
    private Long companyId;

    @Column
    private String name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "premises")
//    private Set<BruttoBalance> bruttoBalanceList = new HashSet<>();

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "premises")
//    private Set<SalesRecap> salesRecapList = new HashSet<>();
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "premises")
//    private Set<SupplyAnalytics> supplyAnalyticsList = new HashSet<>();

}
