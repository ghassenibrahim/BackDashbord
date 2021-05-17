package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.entity.reporting.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the basic Report data within the Reporting List
 */

@Getter
@Setter
@Builder
public class ReportDTO implements Serializable {
    private Long id;
    private Long companyId;
    private String name;
    private LocalDate lastUpdate;
    private LocalDateTime addedOn;
    private String reportTypeCode;
    private LocalDate reportDateFrom;
    private LocalDate reportDateTo;
    private Boolean hasBruttoBalance;
    private Boolean hasSalesRecapList;
    private Boolean hasSupplyAnalyticList;
    private Boolean hasCustomerList;
    private Boolean hasSupplierList;
    private Boolean hasCustomerAdvanceList;
    private Boolean hasSupplierAdvanceList;
    private Boolean hasLoanGivenList;
    private Boolean hasLoanReceivedList;
    private Boolean hasReceivableMaturityList;

    public static ReportDTO of(Report report) {
        return builder()
                .id(report.getId())
                .companyId(report.getCompanyId())
                .name(report.getName())
                .lastUpdate(report.getLastUpdate())
                .addedOn(report.getAddedOn())
                .reportTypeCode(report.getReportTypeCode())
                .reportDateFrom(report.getReportDateFrom())
                .reportDateTo(report.getReportDateTo())
                .hasBruttoBalance(!report.getBruttoBalanceList().isEmpty())
                .hasSalesRecapList(!report.getSalesRecapList().isEmpty())
                .hasSupplyAnalyticList(!report.getSupplyAnalyticList().isEmpty())
                .hasCustomerList(!report.getCustomerList().isEmpty())
                .hasSupplierList(!report.getSupplierList().isEmpty())
                .hasCustomerAdvanceList(!report.getCustomerAdvanceList().isEmpty())
                .hasSupplierAdvanceList(!report.getSupplierAdvanceList().isEmpty())
                .hasLoanGivenList(!report.getLoanGivenList().isEmpty())
                .hasLoanReceivedList(!report.getLoanReceivedList().isEmpty())
                .hasReceivableMaturityList(!report.getReceivableMaturityList().isEmpty())
                .build();
    }

    public static List<ReportDTO> of(List<Report> reportList) {
        return reportList.stream()
                .map(ReportDTO::of)
                .collect(Collectors.toList());
    }
}
