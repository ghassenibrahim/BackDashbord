package hr.alphacloud.server.service;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.purchase_recap.PurchaseRecapFilterCommand;
import hr.alphacloud.server.model.command.purchase_recap.PurchaseRecapSaveCommand;
import hr.alphacloud.server.model.dto.PurchaseRecapDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.dashboard.customer_analysis.PivotTableDataDTO;

import java.util.List;

public interface PurchaseRecapService {

    ApiBaseDTO<List<PurchaseRecapDTO>> filterPurchaseRecaps(ApiBasePageCommand<PurchaseRecapFilterCommand> command);

    ApiBaseDTO<List<PivotTableDataDTO>> filterPivotTable(ApiBaseCommand<PurchaseRecapFilterCommand> command);

    ApiBaseDTO<Boolean> savePurchaseRecap(ApiBaseCommand<PurchaseRecapSaveCommand> command);

    ApiBaseDTO<Boolean> deletePurchaseRecap(ApiBaseCommand<DeleteCommand> command);

    ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<PurchaseRecapFilterCommand> command);

}
