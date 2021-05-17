package hr.alphacloud.server.service;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.brutto_balance.BruttoBalanceFilterCommand;
import hr.alphacloud.server.model.command.brutto_balance.BruttoBalanceSaveCommand;
import hr.alphacloud.server.model.dto.BruttoBalanceDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;

import java.util.List;

public interface BruttoBalanceService {

    ApiBaseDTO<List<BruttoBalanceDTO>> filterBruttoBalance(ApiBasePageCommand<BruttoBalanceFilterCommand> command);

    ApiBaseDTO<Boolean> saveBruttoBalance(ApiBaseCommand<BruttoBalanceSaveCommand> command);

    ApiBaseDTO<Boolean> deleteBruttoBalance(ApiBaseCommand<DeleteCommand> command);

    ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<BruttoBalanceFilterCommand> command);

}
