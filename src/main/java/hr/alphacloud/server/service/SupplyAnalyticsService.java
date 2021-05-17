package hr.alphacloud.server.service;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.supply_analytics.SupplyAnalyticsFilterCommand;
import hr.alphacloud.server.model.command.supply_analytics.SupplyAnalyticsSaveCommand;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.SupplyAnalyticsDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;

import java.util.List;

public interface SupplyAnalyticsService {

    ApiBaseDTO<List<SupplyAnalyticsDTO>> filterSupplyAnalytics(ApiBasePageCommand<SupplyAnalyticsFilterCommand> command);

    ApiBaseDTO<Boolean> saveSupplyAnalytic(ApiBaseCommand<SupplyAnalyticsSaveCommand> command);

    ApiBaseDTO<Boolean> deleteSupplyAnalytic(ApiBaseCommand<DeleteCommand> command);

    ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<SupplyAnalyticsFilterCommand> command);

}
