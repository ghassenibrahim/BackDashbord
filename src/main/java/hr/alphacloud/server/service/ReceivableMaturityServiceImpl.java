package hr.alphacloud.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.receivable_maturity.ReceivableMaturityFilterCommand;
import hr.alphacloud.server.model.command.receivable_maturity.ReceivableMaturitySaveCommand;
import hr.alphacloud.server.model.dto.ReceivableMaturityDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.entity.reporting.ReceivableMaturity;
import hr.alphacloud.server.model.entity.reporting.ReceivableMaturityProperties;
import hr.alphacloud.server.repository.ReceivableMaturityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceivableMaturityServiceImpl implements ReceivableMaturityService {

    private final ReceivableMaturityRepository receivableMaturityRepository;

    public ReceivableMaturityServiceImpl(ReceivableMaturityRepository receivableMaturityRepository) {
        this.receivableMaturityRepository = receivableMaturityRepository;
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<ReceivableMaturityDTO>> filteReceivableMaturity(ApiBasePageCommand<ReceivableMaturityFilterCommand> command) {
        return null;

    }

    @Override
    @Transactional
    public ApiBaseDTO<Boolean> saveReceivableMaturity(ApiBaseCommand<ReceivableMaturitySaveCommand> command) {
        return null;

    }

    @Override
    public ApiBaseDTO<Boolean> deleteReceivableMaturity(ApiBaseCommand<DeleteCommand> command) {
        //TODO remove if delete happens only from report service
        return null;
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<ReceivableMaturityFilterCommand> command) {
        List<ReceivableMaturity> receivableMaturityList = this.receivableMaturityRepository.findAllByReportId(
                command.getCommand().getReportId());
        List<ReportTableDataDTO> reportTableDataListDTO = new ArrayList<>();
        receivableMaturityList.forEach(receivableMaturity -> {
            List<ReceivableMaturityProperties> receivableMaturityProperties = receivableMaturity.getReceivableMaturityProperties();
            if (receivableMaturityProperties != null) {
                try {
                    String json = new ObjectMapper().writeValueAsString(receivableMaturityProperties);
                    receivableMaturityProperties = new ObjectMapper().readValue(json, new TypeReference<>() {
                    });
                } catch (Exception ignored) {
                }
                List<List<String>> finalList = new ArrayList<>();
                receivableMaturityProperties.forEach(item -> finalList.add(item.toList()));
                reportTableDataListDTO.add(ReportTableDataDTO.of(receivableMaturity, finalList));
            }
        });
        return ApiBaseDTO.generateSuccessResponse(reportTableDataListDTO);
    }


}
