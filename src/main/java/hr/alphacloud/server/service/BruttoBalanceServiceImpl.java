package hr.alphacloud.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.brutto_balance.BruttoBalanceFilterCommand;
import hr.alphacloud.server.model.command.brutto_balance.BruttoBalanceSaveCommand;
import hr.alphacloud.server.model.dto.BruttoBalanceDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.entity.reporting.BruttoBalance;
import hr.alphacloud.server.model.entity.reporting.BruttoBalanceProperties;
import hr.alphacloud.server.repository.BruttoBalanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class BruttoBalanceServiceImpl implements BruttoBalanceService {

    private final BruttoBalanceRepository bruttoBalanceRepository;

    @Override
    @Transactional
    public ApiBaseDTO<List<BruttoBalanceDTO>> filterBruttoBalance(ApiBasePageCommand<BruttoBalanceFilterCommand> command) {
        List<BruttoBalance> bruttoBalanceList = this.bruttoBalanceRepository.findAllByReportId(command.getCommand().getReportId());
        List<BruttoBalanceDTO> bruttoBalanceDTO = new ArrayList<>();
        bruttoBalanceList.forEach(item -> {
            bruttoBalanceDTO.add(BruttoBalanceDTO.of(item));
        });

        return ApiBaseDTO.generateSuccessResponse(bruttoBalanceDTO);
    }

    @Override
    @Transactional
    public ApiBaseDTO<Boolean> saveBruttoBalance(ApiBaseCommand<BruttoBalanceSaveCommand> command) {
        Optional<BruttoBalance> optionalBruttoBalance = bruttoBalanceRepository.findById(command.getCommand().getId());
        if (optionalBruttoBalance.isPresent()) {
            BruttoBalance bruttoBalance = command.getCommand().convertToEntity();
            bruttoBalance.setImportSettings(optionalBruttoBalance.get().getImportSettings());
            this.bruttoBalanceRepository.save(bruttoBalance);
        } else {
            final BruttoBalance bruttoBalance = command.getCommand().convertToEntity();
            this.bruttoBalanceRepository.save(bruttoBalance);
        }

        return ApiBaseDTO.generateSuccessResponse(true);
    }

    @Override
    public ApiBaseDTO<Boolean> deleteBruttoBalance(ApiBaseCommand<DeleteCommand> command) {
        //TODO remove if delete happens only from report service
        return null;
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<BruttoBalanceFilterCommand> command) {
        List<BruttoBalance> bruttoBalanceList = this.bruttoBalanceRepository.findAllByReportId(
                command.getCommand().getReportId());
        List<ReportTableDataDTO> reportTableDataListDTO = new ArrayList<>();
        bruttoBalanceList.forEach(bruttoBalance -> {
            List<BruttoBalanceProperties> bruttoBalanceProperties = bruttoBalance.getBruttoBalanceProperties();
            if (bruttoBalanceProperties != null) {
                try {
                    String json = new ObjectMapper().writeValueAsString(bruttoBalanceProperties);
                    bruttoBalanceProperties = new ObjectMapper().readValue(json, new TypeReference<>() {
                    });
                } catch (Exception ignored) {
                }
                List<List<String>> finalList = new ArrayList<>();
                bruttoBalanceProperties.forEach(item -> {
                    finalList.add(item.toList());
                });
                reportTableDataListDTO.add(ReportTableDataDTO.of(bruttoBalance, finalList));
            }
        });
        return ApiBaseDTO.generateSuccessResponse(reportTableDataListDTO);
    }
}
