package hr.alphacloud.server.model.entity.reporting.base;

import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractBusinessReport extends AbstractReport {
    public static final Field BUSINESS_TYPE;

    static {
        try {
            BUSINESS_TYPE = AbstractBusinessReport.class.getDeclaredField("businessType");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in AbstractBusinessReport.");
        }
    }

    @ManyToOne
    @JoinColumn(name = "business_type_id")
    private BusinessType businessType;

}
