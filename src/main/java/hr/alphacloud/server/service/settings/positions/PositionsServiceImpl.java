package hr.alphacloud.server.service.settings.positions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsDeleteCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsFilerCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.settings.position_template.PositionDTO;
import hr.alphacloud.server.model.entity.reporting.settings.position_template.PositionProperties;
import hr.alphacloud.server.model.entity.reporting.settings.position_template.Positions;
import hr.alphacloud.server.repository.settings.PositionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionsServiceImpl implements PositionsService {

    private final PositionsRepository positionsRepository;

    public PositionsServiceImpl(PositionsRepository positionsRepository) {
        this.positionsRepository = positionsRepository;
    }

    @Override
    public ApiBaseDTO<List<PositionDTO>> filter(ApiBasePageCommand<PositionsFilerCommand> command) {
        List<Positions> positions = this.positionsRepository.findAllByCompanyId(
                command.getCommand().getCompanyId());

        ObjectMapper objectMapper = new ObjectMapper();
        List<PositionDTO> positionDTO = positions.stream().map(item ->
                objectMapper.convertValue(item,
                        new TypeReference<PositionDTO>() {
                        })).collect(Collectors.toList());

        return ApiBaseDTO.generateSuccessResponse(positionDTO);
    }

    @Override
    public ApiBaseDTO<Boolean> deletePosition(ApiBaseCommand<PositionsDeleteCommand> deleteCommand) {

        ObjectMapper mapper = new ObjectMapper();

        Positions position = this.positionsRepository.findByCompanyIdAndSheetType
                (deleteCommand.getCommand().getCompanyId(), deleteCommand.getCommand().getSheetType());

        List<PositionProperties> tempPositionProperties = mapper.convertValue(position.getPositionProperties(), new TypeReference<>() {
        });
        boolean removed = tempPositionProperties.removeIf(item -> item.getName().equals(deleteCommand.getCommand().getPosition()));
        position.setPositionProperties(tempPositionProperties);
        if (removed) {
            this.positionsRepository.save(position);
            return ApiBaseDTO.generateSuccessResponse(true);
        } else {
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
        }
    }

    @Override
    public ApiBaseDTO<Boolean> savePositionsData(ApiBaseCommand<PositionsSaveCommand> command) {
        Positions positions = new Positions();
        positions.setId(command.getCommand().getPositions().getId());
        positions.setCompanyId(command.getCommand().getPositions().getCompanyId());
        positions.setSheetType(command.getCommand().getPositions().getSheetType());
        positions.setPositionProperties(command.getCommand().getPositions().getPositionProperties());

        this.positionsRepository.save(positions);
        return ApiBaseDTO.generateSuccessResponse(true);
    }

    public ApiBaseDTO<PositionDTO> fetchAllDistinctKontos(Long companyId) {
        List<Positions> positions = this.positionsRepository.findAllByCompanyId(companyId);

        ObjectMapper objectMapper = new ObjectMapper();
        List<PositionDTO> positionDTO = positions.stream().map(item ->
                objectMapper.convertValue(item,
                        new TypeReference<PositionDTO>() {
                        })).collect(Collectors.toList());

        return null;
    }
}
