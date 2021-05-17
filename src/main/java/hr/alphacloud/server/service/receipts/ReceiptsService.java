package hr.alphacloud.server.service.receipts;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptDeleteCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptFilterCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.receipts.ReceiptDTO;

public interface ReceiptsService {

    ApiBasePageDTO<ReceiptDTO> filterReceipt(ApiBasePageCommand<ReceiptFilterCommand> command);

    ApiBaseDTO<ReceiptDTO> saveReceipt(ApiBaseCommand<ReceiptSaveCommand> command);

    ApiBaseDTO<ReceiptDTO> findReceipt(ApiBaseCommand<ReceiptFilterCommand> command);

    ApiBaseDTO<Boolean> deleteReceipt(ApiBaseCommand<ReceiptDeleteCommand> command);
}
