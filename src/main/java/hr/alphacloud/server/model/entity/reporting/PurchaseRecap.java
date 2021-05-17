package hr.alphacloud.server.model.entity.reporting;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import hr.alphacloud.server.model.entity.reporting.base.AbstractBusinessReport;
import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "purchase_recap")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class PurchaseRecap extends AbstractBusinessReport {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<PurchaseRecapProperties> purchaseRecapProperties;

    @ManyToOne
    @JoinColumn(name = "account_book_id")
    private AccountBook accountBook;

    @Column
    private String importType;

}
