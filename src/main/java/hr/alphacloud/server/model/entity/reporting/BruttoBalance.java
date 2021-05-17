package hr.alphacloud.server.model.entity.reporting;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import hr.alphacloud.server.model.entity.reporting.base.AbstractBusinessReport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "brutto_balance")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class BruttoBalance extends AbstractBusinessReport {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<BruttoBalanceProperties> bruttoBalanceProperties;

}
