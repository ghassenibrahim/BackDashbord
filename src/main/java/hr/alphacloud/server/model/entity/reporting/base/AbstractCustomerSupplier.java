package hr.alphacloud.server.model.entity.reporting.base;

import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.lang.reflect.Field;
import java.util.List;

/**
 * customerSupplierProperties are equally shared between Customer, Supplier, Customer advance, Supplier advance,
 * Given loans and Received loans with their respective settings inside AbstractReport class.
 */

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractCustomerSupplier extends AbstractReport {
    public static final Field ACCOUNT_BOOK;
    public static final Field IMPORT_TYPE;

    static {
        try {
            IMPORT_TYPE = AbstractCustomerSupplier.class.getDeclaredField("importType");
            ACCOUNT_BOOK = AbstractCustomerSupplier.class.getDeclaredField("accountBook");
        } catch (NoSuchFieldException nsfe) {
            throw new ExceptionInInitializerError("Failed to init static fields in AbstractReport.");
        }
    }

    @Column(name = "import_type")
    private String importType;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<CustomerSupplierProperties> customerSupplierProperties;


    @ManyToOne
    @JoinColumn(name = "account_book_id")
    private AccountBook accountBook;

}
