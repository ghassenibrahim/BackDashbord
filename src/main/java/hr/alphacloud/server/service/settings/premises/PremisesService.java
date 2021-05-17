package hr.alphacloud.server.service.settings.premises;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.premises.PremisesFilterCommand;
import hr.alphacloud.server.model.command.settings.premises.PremisesSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PremisesService {

    ApiBaseDTO<PremisesDTO> save(ApiBaseCommand<PremisesSaveCommand> command);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command);

    ApiBasePageDTO<PremisesDTO> filter(ApiBasePageCommand<PremisesFilterCommand> command);

    List<PremisesDTO> filter(Long companyId, Pageable pageable);

}
