package hr.alphacloud.server.model.entity.reporting.dashboard;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import hr.alphacloud.server.model.entity.reporting.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class FinancialIndicatorTemplate extends BaseEntity {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private FinancialIndicator liquidityIndicators;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private FinancialIndicator indebtednessIndicators;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private FinancialIndicator activityIndicators;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private FinancialIndicator costEffectivenessIndicators;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private FinancialIndicator profitabilityIndicators;
}
