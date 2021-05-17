package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.*;
import hr.alphacloud.server.model.entity.reporting.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReportTableDataDTO {
    private Long id;
    private List<List<String>> data;
    private List<String> properties;
    private List<Long> ids;
    private SpendingLocationDTO spendingLocation;
    private BusinessTypeDTO businessType;
    private PremisesDTO premises;
    private SectorTypeDTO sectorType;
    private ReportDTO report;
    private ImportSettingsDTO importSettings;
    private WarehouseTypeDTO warehouseType;
    private AccountBookDTO accountBook;
    private String importType;
    private Date dateFrom;
    private Date dateTo;
    private Date dateCompleted;

    public static ReportTableDataDTO of(BruttoBalance bruttoBalance, List<List<String>> dataList) {
        return builder()
                .id(bruttoBalance.getId())
                .properties(BruttoBalanceProperties.getPropertyList())
                .spendingLocation(SpendingLocationDTO.of(bruttoBalance.getSpendingLocation()))
                .businessType(BusinessTypeDTO.of(bruttoBalance.getBusinessType()))
                .premises(PremisesDTO.fromEntity(bruttoBalance.getPremises()))
                .sectorType(SectorTypeDTO.fromEntity(bruttoBalance.getSectorType()))
                .report(ReportDTO.of(bruttoBalance.getReport()))
                .importSettings(ImportSettingsDTO.of(bruttoBalance.getImportSettings()))
                .data(dataList)
                .build();
    }

    public static ReportTableDataDTO of(SalesRecap salesRecap, List<List<String>> dataList) {
        return builder()
                .id(salesRecap.getId())
                .properties(SalesRecapProperties.getPropertyList())
                .spendingLocation(SpendingLocationDTO.of(salesRecap.getSpendingLocation()))
                .businessType(BusinessTypeDTO.of(salesRecap.getBusinessType()))
                .premises(PremisesDTO.fromEntity(salesRecap.getPremises()))
                .sectorType(SectorTypeDTO.fromEntity(salesRecap.getSectorType()))
                .report(ReportDTO.of(salesRecap.getReport()))
                .importSettings(ImportSettingsDTO.of(salesRecap.getImportSettings()))
                .data(dataList)
                .importType(salesRecap.getImportType())
                .accountBook(AccountBookDTO.of(salesRecap.getAccountBook()))
                .build();
    }

    public static ReportTableDataDTO of(PurchaseRecap purchaseRecap, List<List<String>> dataList) {
        return builder()
                .id(purchaseRecap.getId())
                .properties(PurchaseRecapProperties.getPropertyList())
                .spendingLocation(SpendingLocationDTO.of(purchaseRecap.getSpendingLocation()))
                .businessType(BusinessTypeDTO.of(purchaseRecap.getBusinessType()))
                .premises(PremisesDTO.fromEntity(purchaseRecap.getPremises()))
                .sectorType(SectorTypeDTO.fromEntity(purchaseRecap.getSectorType()))
                .report(ReportDTO.of(purchaseRecap.getReport()))
                .importSettings(ImportSettingsDTO.of(purchaseRecap.getImportSettings()))
                .data(dataList)
                .importType(purchaseRecap.getImportType())
                .accountBook(AccountBookDTO.of(purchaseRecap.getAccountBook()))
                .build();
    }

    public static ReportTableDataDTO of(SupplyAnalytics supplyAnalytics, List<List<String>> dataList) {
        return builder()
                .id(supplyAnalytics.getId())
                .properties(SupplyAnalyticsProperties.getPropertyList())
                .spendingLocation(SpendingLocationDTO.of(supplyAnalytics.getSpendingLocation()))
                .businessType(BusinessTypeDTO.of(supplyAnalytics.getBusinessType()))
                .premises(PremisesDTO.fromEntity(supplyAnalytics.getPremises()))
                .sectorType(SectorTypeDTO.fromEntity(supplyAnalytics.getSectorType()))
                .report(ReportDTO.of(supplyAnalytics.getReport()))
                .importSettings(ImportSettingsDTO.of(supplyAnalytics.getImportSettings()))
                .warehouseType(WarehouseTypeDTO.of(supplyAnalytics.getWarehouseType()))
                .data(dataList)
                .accountBook(AccountBookDTO.of(supplyAnalytics.getAccountBook()))
                .importType(supplyAnalytics.getImportType())
                .build();
    }

    public static ReportTableDataDTO of(ReceivableMaturity receivableMaturity, List<List<String>> dataList) {
        return builder()
                .id(receivableMaturity.getId())
                .properties(ReceivableMaturityProperties.getPropertyList())
                .spendingLocation(SpendingLocationDTO.of(receivableMaturity.getSpendingLocation()))
                .businessType(BusinessTypeDTO.of(receivableMaturity.getBusinessType()))
                .premises(PremisesDTO.fromEntity(receivableMaturity.getPremises()))
                .sectorType(SectorTypeDTO.fromEntity(receivableMaturity.getSectorType()))
                .report(ReportDTO.of(receivableMaturity.getReport()))
                .importSettings(ImportSettingsDTO.of(receivableMaturity.getImportSettings()))
                .accountBook(AccountBookDTO.of(receivableMaturity.getAccountBook()))
                .data(dataList)
                .importType(receivableMaturity.getImportType())
                .dateCompleted(receivableMaturity.getDateCompleted())
                .dateTo(receivableMaturity.getDateTo())
                .dateFrom(receivableMaturity.getDateFrom())
                .build();
    }

}
