package hr.alphacloud.server.service.settings.additional_settings;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.settings.additional_settings.AdditionalSettingsFilterCommand;
import hr.alphacloud.server.model.command.settings.additional_settings.AdditionalSettingsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.settings.AdditionalSettingsDTO;
import hr.alphacloud.server.repository.settings.AdditionalSettingsRepository;
import org.springframework.stereotype.Service;

@Service
public class AdditionalSettingsServiceImpl implements AdditionalSettingsService {

    private final AdditionalSettingsRepository additionalSettingsRepository;

    public AdditionalSettingsServiceImpl(AdditionalSettingsRepository additionalSettingsRepository) {
        this.additionalSettingsRepository = additionalSettingsRepository;
    }

    @Override
    public ApiBaseDTO<AdditionalSettingsDTO> save(ApiBaseCommand<AdditionalSettingsSaveCommand> command) {
        return ApiBaseDTO.generateSuccessResponse(AdditionalSettingsDTO.of(
                this.additionalSettingsRepository.save(command.getCommand().convertToEntity())));
    }

    @Override
    public ApiBaseDTO<AdditionalSettingsDTO> fetch(ApiBaseCommand<AdditionalSettingsFilterCommand> command) {
        return ApiBaseDTO.generateSuccessResponse(AdditionalSettingsDTO.of(
                this.additionalSettingsRepository.findByCompanyId(command.getCommand().getCompanyId())));
    }
}
