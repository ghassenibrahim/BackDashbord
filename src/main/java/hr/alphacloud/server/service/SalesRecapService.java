package hr.alphacloud.server.service;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.sales_recap.SalesRecapFilterCommand;
import hr.alphacloud.server.model.command.sales_recap.SalesRecapSaveCommand;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.SalesRecapDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.dashboard.customer_analysis.PivotTableDataDTO;

import java.util.List;

public interface SalesRecapService {

    ApiBaseDTO<List<SalesRecapDTO>> filterSalesRecaps(ApiBasePageCommand<SalesRecapFilterCommand> command);

    ApiBaseDTO<List<PivotTableDataDTO>> filterPivotTable(ApiBaseCommand<SalesRecapFilterCommand> command);

    ApiBaseDTO<Boolean> saveSalesRecap(ApiBaseCommand<SalesRecapSaveCommand> command);

    ApiBaseDTO<Boolean> deleteSalesRecap(ApiBaseCommand<DeleteCommand> command);

    ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<SalesRecapFilterCommand> command);

}
