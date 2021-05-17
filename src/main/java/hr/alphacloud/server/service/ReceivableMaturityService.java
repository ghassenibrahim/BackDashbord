package hr.alphacloud.server.service;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.receivable_maturity.ReceivableMaturityFilterCommand;
import hr.alphacloud.server.model.command.receivable_maturity.ReceivableMaturitySaveCommand;
import hr.alphacloud.server.model.dto.ReceivableMaturityDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;

import java.util.List;

public interface ReceivableMaturityService {

    ApiBaseDTO<List<ReceivableMaturityDTO>> filteReceivableMaturity(ApiBasePageCommand<ReceivableMaturityFilterCommand> command);

    ApiBaseDTO<Boolean> saveReceivableMaturity(ApiBaseCommand<ReceivableMaturitySaveCommand> command);

    ApiBaseDTO<Boolean> deleteReceivableMaturity(ApiBaseCommand<DeleteCommand> command);

    ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<ReceivableMaturityFilterCommand> command);

}
