package hr.alphacloud.server.model.entity.reporting;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import hr.alphacloud.server.model.entity.reporting.base.AbstractBusinessReport;
import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import hr.alphacloud.server.model.entity.reporting.settings.WarehouseType;
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
@Entity(name = "supply_analytics")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class SupplyAnalytics extends AbstractBusinessReport {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<SupplyAnalyticsProperties> supplyAnalyticsProperties;

    @ManyToOne
    @JoinColumn(name = "warehouse_type_id")
    private WarehouseType warehouseType;

    @Column
    private String importType;

    @ManyToOne
    @JoinColumn(name = "account_book_id")
    private AccountBook accountBook;


}
