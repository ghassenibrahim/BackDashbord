package hr.alphacloud.server.service.settings.business_type;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.business_type.BusinessTypeFilterCommand;
import hr.alphacloud.server.model.command.settings.business_type.BusinessTypeSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.BusinessTypeDTO;

import java.util.List;

public interface BusinessTypeSettingsService {


    ApiBaseDTO<BusinessTypeDTO> save(ApiBaseCommand<BusinessTypeSaveCommand> command);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command);

    ApiBasePageDTO<BusinessTypeDTO> filter(ApiBasePageCommand<BusinessTypeFilterCommand> command);

    List<BusinessTypeDTO> filter(Long companyId, String locale);
}
