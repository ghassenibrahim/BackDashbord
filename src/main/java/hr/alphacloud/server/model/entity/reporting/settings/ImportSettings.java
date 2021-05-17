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
public class ImportSettings extends BaseEntity {
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

    @Column
    private Integer skipFirstRowAmount;

    @Column
    private Integer skipLastRowAmount;

    @Column
    private Integer minimumAcceptedColumnLength;

    @Column
    private String acceptedDateFormat;

    @Column
    private Integer skipFirstColumnAmount;
}
