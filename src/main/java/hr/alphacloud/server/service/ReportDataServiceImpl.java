package hr.alphacloud.server.service;

import hr.alphacloud.server.model.command.ReportDataFilterCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.ReportSettingsDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.service.settings.account_book.AccountBookSettingsService;
import hr.alphacloud.server.service.settings.business_type.BusinessTypeSettingsService;
import hr.alphacloud.server.service.settings.import_settings.ImportSettingsService;
import hr.alphacloud.server.service.settings.premises.PremisesService;
import hr.alphacloud.server.service.settings.sector_type.SectorTypeService;
import hr.alphacloud.server.service.settings.spending_location.SpendingLocationService;
import hr.alphacloud.server.service.settings.warehouse_type.WarehouseTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportDataServiceImpl implements ReportDataService {

    private final ReportService reportService;
    private final BusinessTypeSettingsService businessTypeSettingsService;
    private final ImportSettingsService importSettingsService;
    private final SpendingLocationService spendingLocationService;
    private final WarehouseTypeService warehouseTypeService;
    private final PremisesService premisesService;
    private final SectorTypeService sectorTypeService;
    private final AccountBookSettingsService accountBookSettingsService;

    // TODO: check if calls are well written here
    @Override
    public ApiBaseDTO<ReportSettingsDataDTO> getReportSettingsData(ApiBasePageCommand<ReportDataFilterCommand> command) {
        return ApiBaseDTO.generateSuccessResponse(ReportSettingsDataDTO.builder()
                .importDate(reportService.getLastImportDate(command.getCommand().getCompanyId()))
                .businessTypeList(businessTypeSettingsService.filter(command.getCommand().getCompanyId(), command.getCommand().getLocale()))
                .importSettingsList(importSettingsService.filter(command.getCommand().getCompanyId(), command.getPaginationAndSorting().generatePagingAndSortingRequest()))
                .spendingLocationList(spendingLocationService.filter(command.getCommand().getCompanyId(), command.getPaginationAndSorting().generatePagingAndSortingRequest()))
                .warehouseTypeList(warehouseTypeService.filter(command.getCommand().getCompanyId(), command.getCommand().getLocale(),
                        command.getPaginationAndSorting().generatePagingAndSortingRequest()))
                .premisesList(premisesService.filter(command.getCommand().getCompanyId(), command.getPaginationAndSorting().generatePagingAndSortingRequest()))
                .sectorTypeList(sectorTypeService.filter(command.getCommand().getCompanyId(), command.getPaginationAndSorting().generatePagingAndSortingRequest()))
                .accountBookList(accountBookSettingsService.filterList(command.getCommand().getCompanyId(), command.getPaginationAndSorting().generatePagingAndSortingRequest()))
                .build());
    }
}
