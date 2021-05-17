package hr.alphacloud.server.service.settings.import_settings;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.import_settings.ImportSettingsFilterCommand;
import hr.alphacloud.server.model.command.settings.import_settings.ImportSettingsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.ImportSettingsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ImportSettingsService {

    Set<String> getReportFields();

    List<ImportSettingsDTO> filter(Long companyId, Pageable pageable);

    ApiBasePageDTO<ImportSettingsDTO> filter(ApiBasePageCommand<ImportSettingsFilterCommand> filterCommand);

    ApiBaseDTO<ImportSettingsDTO> save(ApiBaseCommand<ImportSettingsSaveCommand> saveCommand);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> deleteCommand);
}
