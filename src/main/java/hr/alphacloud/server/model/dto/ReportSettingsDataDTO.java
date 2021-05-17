package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Used to extract all settings for Reports within one request
 */

@Getter
@Setter
@Builder
public class ReportSettingsDataDTO {

    private String importDate;
    private List<WarehouseTypeDTO> warehouseTypeList;
    private List<BusinessTypeDTO> businessTypeList;
    private List<SpendingLocationDTO> spendingLocationList;
    private List<ImportSettingsDTO> importSettingsList;
    private List<PremisesDTO> premisesList;
    private List<SectorTypeDTO> sectorTypeList;
    private List<AccountBookDTO> accountBookList;

}
