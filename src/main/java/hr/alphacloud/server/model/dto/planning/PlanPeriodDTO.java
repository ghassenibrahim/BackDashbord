package hr.alphacloud.server.model.dto.planning;

import hr.alphacloud.server.model.entity.reporting.planning.PlanPeriod;
import hr.alphacloud.server.model.enums.planning.TimePeriodType;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@AllArgsConstructor
public class PlanPeriodDTO implements Serializable {
    private Long id;
    private TimePeriodType planPeriodType;
    private Long planId;
    private BigDecimal operatingIncomes;
    private BigDecimal costOfGoodsSold;
    private BigDecimal goodsValueChanges;
    private BigDecimal rawMaterialSuppliesCost;
    private BigDecimal otherExternalCosts;
    private BigDecimal netSalariesWages;
    private BigDecimal taxesContributionsFromSalaries;
    private BigDecimal wageContributions;
    private BigDecimal otherCosts;
    private BigDecimal fixedAssetsExceptFinancial;
    private BigDecimal currentAssetsExceptFinancial;
    private BigDecimal provisionsForPensions;
    private BigDecimal provisionsForTaxes;
    private BigDecimal provisionsForInitiatedLitigation;
    private BigDecimal provisionsForNaturalResources;
    private BigDecimal provisionsForWarrantyPeriod;
    private BigDecimal provisionsOther;
    private BigDecimal amortization;
    private BigDecimal financialRevenues;
    private BigDecimal financialExpenses;
    private BigDecimal employeeNumber;
    private BigDecimal revenueGrowth;
    private BigDecimal revenueShare;

    private PlanPeriodDTO assignFields(PlanPeriod source) {
        PlanPeriodDTO planPeriodDTO = new PlanPeriodDTO();
        planPeriodDTO.setId(source.getId());
        planPeriodDTO.setPlanId(source.getPlan().getId());
        planPeriodDTO.setOperatingIncomes(source.getOperatingIncomes());
        planPeriodDTO.setCostOfGoodsSold(source.getCostOfGoodsSold());
        planPeriodDTO.setGoodsValueChanges(source.getGoodsValueChanges());
        planPeriodDTO.setRawMaterialSuppliesCost(source.getRawMaterialSuppliesCost());
        planPeriodDTO.setOtherExternalCosts(source.getOtherExternalCosts());
        planPeriodDTO.setNetSalariesWages(source.getNetSalariesWages());
        planPeriodDTO.setTaxesContributionsFromSalaries(source.getTaxesContributionsFromSalaries());
        planPeriodDTO.setWageContributions(source.getWageContributions());
        planPeriodDTO.setOtherCosts(source.getOtherCosts());
        planPeriodDTO.setFixedAssetsExceptFinancial(source.getFixedAssetsExceptFinancial());
        planPeriodDTO.setCurrentAssetsExceptFinancial(source.getCurrentAssetsExceptFinancial());
        planPeriodDTO.setProvisionsForPensions(source.getProvisionsForPensions());
        planPeriodDTO.setProvisionsForTaxes(source.getProvisionsForTaxes());
        planPeriodDTO.setProvisionsForInitiatedLitigation(source.getProvisionsForInitiatedLitigation());
        planPeriodDTO.setProvisionsForNaturalResources(source.getProvisionsForNaturalResources());
        planPeriodDTO.setProvisionsForWarrantyPeriod(source.getProvisionsForWarrantyPeriod());
        planPeriodDTO.setProvisionsOther(source.getProvisionsOther());
        planPeriodDTO.setAmortization(source.getAmortization());
        planPeriodDTO.setFinancialRevenues(source.getFinancialRevenues());
        planPeriodDTO.setFinancialExpenses(source.getFinancialExpenses());
        planPeriodDTO.setEmployeeNumber(source.getEmployeeNumber());
        planPeriodDTO.setRevenueGrowth(source.getRevenueGrowth());
        planPeriodDTO.setRevenueShare(source.getRevenueShare());
        planPeriodDTO.setPlanPeriodType(source.getPlanPeriodType());
        return planPeriodDTO;
    }

    public static PlanPeriodDTO fromEntity(PlanPeriod source) {
        return new PlanPeriodDTO().assignFields(source);
    }


    public static List<PlanPeriodDTO> fromEntityList(List<PlanPeriod> sourceList) {
        if (sourceList != null){
        return sourceList.stream().sorted(Comparator.comparing(PlanPeriod::getPlanPeriodType)).map(PlanPeriodDTO::fromEntity).collect(Collectors.toList());
        } else return Collections.emptyList();
    }
}
