package hr.alphacloud.server.model.entity.reporting.planning;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import hr.alphacloud.server.model.entity.reporting.base.BaseEntity;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="plan")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Plan extends BaseEntity implements Serializable {

    public static final Field NAME_FIELD;
    public static final Field COMPANY_ID;
    public static final Field ADDED_ON;
    public static final Field FOR_DATE;
    public static final Field SPENDING_LOCATION;

    static {
        try {
            COMPANY_ID = Plan.class.getDeclaredField("companyId");
            NAME_FIELD = Plan.class.getDeclaredField("name");
            SPENDING_LOCATION = Plan.class.getDeclaredField("spendingLocation");
            FOR_DATE = Plan.class.getDeclaredField("lastUpdate");
            ADDED_ON = Plan.class.getDeclaredField("addedOn");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in Plan.");
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "spending_location_id")
    private SpendingLocation spendingLocation;

    @Column
    private Long companyId;

    @Column(name = "name")
    private String name;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @CreationTimestamp
    @Column(name = "added_on", nullable = false, updatable = false)
    private LocalDateTime addedOn;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PlanPeriod> planPeriodList = new ArrayList<>();

}
