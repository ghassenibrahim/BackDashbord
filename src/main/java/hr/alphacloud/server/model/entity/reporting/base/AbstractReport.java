package hr.alphacloud.server.model.entity.reporting.base;

import hr.alphacloud.server.model.entity.reporting.Report;
import hr.alphacloud.server.model.entity.reporting.settings.ImportSettings;
import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractReport extends BaseEntity {
    public static final Field REPORT_FIELD;
    public static final Field SPENDING_LOCATION_FIELD;
    public static final Field PREMISES_FIELD;
    public static final Field SECTOR_FIELD;
    public static final Field IMPORT_SETTINGS_FIELD;

    static {
        try {
            REPORT_FIELD = AbstractReport.class.getDeclaredField("report");
            SPENDING_LOCATION_FIELD = AbstractReport.class.getDeclaredField("spendingLocation");
            PREMISES_FIELD = AbstractReport.class.getDeclaredField("premises");
            SECTOR_FIELD = AbstractReport.class.getDeclaredField("sectorType");
            IMPORT_SETTINGS_FIELD = AbstractReport.class.getDeclaredField("importSettings");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in AbstractReport.");
        }
    }

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @ManyToOne
    @JoinColumn(name = "spending_location_id")
    private SpendingLocation spendingLocation;

    @ManyToOne
    @JoinColumn(name = "premises_id")
    private Premises premises;

    @ManyToOne
    @JoinColumn(name = "import_settings_id")
    private ImportSettings importSettings;

    @ManyToOne
    @JoinColumn(name = "sector_type_id")
    private SectorType sectorType;

}
