package hr.alphacloud.server.model.entity.reporting.receipts;

import hr.alphacloud.server.model.entity.reporting.base.BaseEntity;
import hr.alphacloud.server.model.entity.reporting.planning.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="receipt")
public class Receipt extends BaseEntity implements Serializable {

    public static final Field NAME_FIELD;
    public static final Field COMPANY_ID;
    public static final Field ADDED_ON;

    static {
        try {
            COMPANY_ID = Plan.class.getDeclaredField("companyId");
            NAME_FIELD = Plan.class.getDeclaredField("name");
            ADDED_ON = Plan.class.getDeclaredField("addedOn");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in Plan.");
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "name")
    private String name;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @CreationTimestamp
    @Column(name = "added_on", nullable = false, updatable = false)
    private LocalDateTime addedOn;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_oib")
    private String customerOib;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    @Column(name = "payment_Due")
    private LocalDate paymentDue;
}
