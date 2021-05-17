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
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "receivable_maturity")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ReceivableMaturity extends AbstractBusinessReport {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<ReceivableMaturityProperties> receivableMaturityProperties;

    @Column
    private String importType;

    @Column
    private Date dateFrom;

    @Column
    private Date dateTo;

    @Column
    private Date dateCompleted;

    @ManyToOne
    @JoinColumn(name = "account_book_id")
    private AccountBook accountBook;

}
