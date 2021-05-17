package hr.alphacloud.server.model.entity.reporting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hr.alphacloud.server.model.entity.reporting.base.BaseEntity;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "bruttoBalanceList"})
public class Report extends BaseEntity {

    public static final Field COMPANY_ID;
    public static final Field NAME_FIELD;
    public static final Field FOR_DATE;
    public static final Field ADDED_ON;
    public static final Field REPORT_TYPE_CODE;
    public static final Field REPORT_DATE_FROM;
    public static final Field REPORT_DATE_TO;

    static {
        try {
            COMPANY_ID = Report.class.getDeclaredField("companyId");
            NAME_FIELD = Report.class.getDeclaredField("name");
            FOR_DATE = Report.class.getDeclaredField("lastUpdate");
            ADDED_ON = Report.class.getDeclaredField("addedOn");
            REPORT_TYPE_CODE = Report.class.getDeclaredField("reportTypeCode");
            REPORT_DATE_FROM = Report.class.getDeclaredField("reportDateFrom");
            REPORT_DATE_TO = Report.class.getDeclaredField("reportDateTo");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in Report.");
        }
    }

    @Column
    private Long companyId;

    @Column(name = "name")
    private String name;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @Column(name = "report_date_from")
    private LocalDate reportDateFrom;

    @Column(name = "report_date_to")
    private LocalDate reportDateTo;

    @CreationTimestamp
    @Column(name = "added_on", nullable = false, updatable = false)
    private LocalDateTime addedOn;

    @Column(name = "report_type_code")
    private String reportTypeCode;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BruttoBalance> bruttoBalanceList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesRecap> salesRecapList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseRecap> purchaseRecapList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplyAnalytics> supplyAnalyticList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Customer> customerList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Supplier> supplierList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerAdvance> customerAdvanceList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierAdvance> supplierAdvanceList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanGiven> loanGivenList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoanReceived> loanReceivedList;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceivableMaturity> receivableMaturityList;

	
}
