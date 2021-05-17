package hr.alphacloud.server.service.settings.spending_location;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.spending_location.SpendingLocationFilterCommand;
import hr.alphacloud.server.model.command.settings.spending_location.SpendingLocationSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SpendingLocationService {

    List<SpendingLocationDTO> filter(Long companyId, Pageable pageable);

    ApiBasePageDTO<SpendingLocationDTO> filter(ApiBasePageCommand<SpendingLocationFilterCommand> command);

    ApiBaseDTO<SpendingLocationDTO> save(ApiBaseCommand<SpendingLocationSaveCommand> command);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command);
}
