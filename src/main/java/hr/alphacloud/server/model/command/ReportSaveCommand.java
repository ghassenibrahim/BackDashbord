package hr.alphacloud.server.model.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import hr.alphacloud.server.model.entity.reporting.*;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReportSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    private String name;
    private String locale;
    @NotEmpty
    private String reportTypeCode;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate lastUpdate;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate reportDateFrom;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate reportDateTo;
    private List<BruttoBalance> bruttoBalanceList;
    private List<PurchaseRecap> purchaseRecapList;
    private List<SalesRecap> salesRecapList;
    private List<SupplyAnalytics> supplyAnalyticsList;
    private List<Customer> customerList;
    private List<Supplier> supplierList;
    private List<CustomerAdvance> customerAdvanceList;
    private List<SupplierAdvance> supplierAdvanceList;
    private List<LoanGiven> loanGivenList;
    private List<LoanReceived> loanReceivedList;
    private List<ReceivableMaturity> receivableMaturityList;

    public Report convertToEntity() {
        Report report = new Report();
        report.setId(this.id);
        report.setCompanyId(this.companyId);
        report.setName(this.name);
        report.setReportTypeCode(this.reportTypeCode);
        report.setLastUpdate(this.lastUpdate);
        report.setReportDateTo(this.reportDateTo);
        report.setReportDateFrom(this.reportDateFrom);
        report.setBruttoBalanceList(this.bruttoBalanceList);
        if (this.bruttoBalanceList != null) {
            report.getBruttoBalanceList().forEach(item -> item.setReport(report));
        }
        report.setSalesRecapList(this.salesRecapList);
        if (this.salesRecapList != null) {
            report.getSalesRecapList().forEach(item -> item.setReport(report));
        }
        report.setPurchaseRecapList(this.purchaseRecapList);
        if (this.purchaseRecapList != null) {
            report.getPurchaseRecapList().forEach(item -> item.setReport(report));
        }
        report.setSupplyAnalyticList(this.supplyAnalyticsList);
        if (this.supplyAnalyticsList != null) {
            report.getSupplyAnalyticList().forEach(item -> item.setReport(report));
        }
        report.setCustomerList(this.customerList);
        if (this.customerList != null) {
            report.getCustomerList().forEach(item -> item.setReport(report));
        }
        report.setSupplierList(this.supplierList);
        if (this.supplierList != null) {
            report.getSupplierList().forEach(item -> item.setReport(report));
        }
        report.setCustomerAdvanceList(this.customerAdvanceList);
        if (this.customerAdvanceList != null) {
            report.getCustomerAdvanceList().forEach(item -> item.setReport(report));
        }
        report.setSupplierAdvanceList(this.supplierAdvanceList);
        if (this.supplierAdvanceList != null) {
            report.getSupplierAdvanceList().forEach(item -> item.setReport(report));
        }
        report.setLoanGivenList(this.loanGivenList);
        if (this.loanGivenList != null) {
            report.getLoanGivenList().forEach(item -> item.setReport(report));
        }
        report.setLoanReceivedList(this.loanReceivedList);
        if (this.loanReceivedList != null) {
            report.getLoanReceivedList().forEach(item -> item.setReport(report));
        }
        report.setReceivableMaturityList(this.receivableMaturityList);
        if (this.receivableMaturityList != null) {
            report.getReceivableMaturityList().forEach(item -> item.setReport(report));
        }

        return report;
    }

//        this.wageList.forEach(travelOrderWage -> travelOrderWage.setTravelOrder(this));
//    // FIXME: 05/11/2019 - Refactor
//    private <T extends AbstractCustomerSupplier, S extends AbstractReportSaveCommand<T>> Set<T> mapReports(Report report,
//                                                                                                           List<S> saveCommandList,
//                                                                                                           Map<Long, SpendingLocation> locationMap) {
//        return Optional.ofNullable(saveCommandList)
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(sc -> {
//                    T entity = sc.convertToEntity(report);
//                    entity.setSpendingLocation(findSpendingLocation(locationMap, sc.getSpendingLocationId()));
//                    return entity;
//                })
//                .collect(Collectors.toSet());
//    }
//
//    private <T extends AbstractBusinessReport, S extends AbstractReportSaveCommand<T>> Set<T> mapReports(Report report,
//                                                                                                         List<S> saveCommandList,
//                                                                                                         Map<Long, SpendingLocation> locationMap,
//                                                                                                         Map<Long, BusinessType> businessTypeMap) {
//        return Optional.ofNullable(saveCommandList)
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(sc -> {
//                    T entity = sc.convertToEntity(report);
//                    entity.setSpendingLocation(findSpendingLocation(locationMap, sc.getSpendingLocationId()));
//                    entity.setBusinessType(findBusinessType(businessTypeMap, sc.getBusinessTypeId()));
//                    return entity;
//                })
//                .collect(Collectors.toSet());
//    }
//
//    private Set<SupplyAnalytics> mapReports(Report report,
//                                            List<SupplyAnalyticsSaveCommand> saveCommandList,
//                                            Map<Long, SpendingLocation> locationMap,
//                                            Map<Long, BusinessType> businessTypeMap,
//                                            Map<Long, WarehouseType> warehouseTypeMap) {
//        return Optional.ofNullable(saveCommandList)
//                .orElse(Collections.emptyList())
//                .stream()
//                .map(sc -> {
//                    SupplyAnalytics entity = sc.convertToEntity(report);
//                    entity.setSpendingLocation(findSpendingLocation(locationMap, sc.getSpendingLocationId()));
//                    entity.setBusinessType(findBusinessType(businessTypeMap, sc.getBusinessTypeId()));
//                    entity.setWarehouseType(findWarehouseType(warehouseTypeMap, sc.getWarehouseTypeId()));
//                    return entity;
//                })
//                .collect(Collectors.toSet());
//    }
//
//    private WarehouseType findWarehouseType(Map<Long, WarehouseType> warehouseTypeMap, Long warehouseTypeId) {
//        if (warehouseTypeId != null) {
//            return Optional.ofNullable(warehouseTypeMap.get(warehouseTypeId))
//                    .orElseThrow(() -> new EntityNotExistException(ErrorInfo.WAREHOUSE_TYPE_NOT_EXIST));
//        }
//        return null;
//    }
//
//    private BusinessType findBusinessType(Map<Long, BusinessType> businessTypeMap, Long businessTypeId) {
//        if (businessTypeId != null) {
//            return Optional.ofNullable(businessTypeMap.get(businessTypeId))
//                    .orElseThrow(() -> new EntityNotExistException(ErrorInfo.BUSINESS_TYPE_NOT_EXIST));
//        }
//        return null;
//    }
//
//    private SpendingLocation findSpendingLocation(Map<Long, SpendingLocation> locationMap, Long spendingLocationId) {
//        if (spendingLocationId != null) {
//            return Optional.ofNullable(locationMap.get(spendingLocationId))
//                    .orElseThrow(() -> new EntityNotExistException(ErrorInfo.SPENDING_LOCATION_NOT_EXIST));
//        }
//        return null;
//    }
}
