package hr.alphacloud.server.model.entity.reporting.planning;
import hr.alphacloud.server.model.entity.reporting.base.BaseEntity;
import hr.alphacloud.server.model.enums.planning.TimePeriodType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "plan_period", schema = "public")
public class PlanPeriod extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Column
    private BigDecimal operatingIncomes;

    @Column
    private BigDecimal costOfGoodsSold;

    @Column
    private BigDecimal goodsValueChanges;

    @Column
    private BigDecimal rawMaterialSuppliesCost;

    @Column
    private BigDecimal otherExternalCosts;

    @Column
    private BigDecimal netSalariesWages;

    @Column
    private BigDecimal taxesContributionsFromSalaries;

    @Column
    private BigDecimal wageContributions;

    @Column
    private BigDecimal otherCosts;

    @Column
    private BigDecimal fixedAssetsExceptFinancial;

    @Column
    private BigDecimal currentAssetsExceptFinancial;

    @Column
    private BigDecimal provisionsForPensions;

    @Column
    private BigDecimal provisionsForTaxes;

    @Column
    private BigDecimal provisionsForInitiatedLitigation;

    @Column
    private BigDecimal provisionsForNaturalResources;

    @Column
    private BigDecimal provisionsForWarrantyPeriod;

    @Column
    private BigDecimal provisionsOther;

    @Column
    private BigDecimal amortization;

    @Column
    private BigDecimal financialRevenues;

    @Column
    private BigDecimal  financialExpenses;

    @Column
    private BigDecimal  employeeNumber;

    @Column
    private BigDecimal revenueGrowth;

    @Column
    private BigDecimal revenueShare;

    @Enumerated(EnumType.STRING)
    @Column
    private TimePeriodType planPeriodType;
}