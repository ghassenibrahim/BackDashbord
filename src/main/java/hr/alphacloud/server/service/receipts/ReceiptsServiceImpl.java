package hr.alphacloud.server.service.receipts;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptDeleteCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptFilterCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.receipts.ReceiptDTO;
import hr.alphacloud.server.model.entity.reporting.receipts.Receipt;
import hr.alphacloud.server.repository.receipts.ReceiptRepository;
import hr.alphacloud.server.specification.ReceiptSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ReceiptsServiceImpl implements ReceiptsService {

    private final ReceiptRepository receiptRepository;

    public ReceiptsServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    @Transactional
    public ApiBasePageDTO<ReceiptDTO> filterReceipt(ApiBasePageCommand<ReceiptFilterCommand> command) {
        final Page<Receipt> receiptPage = this.receiptRepository.findAll(
                ReceiptSpecification.getReceiptFilterSpecification(command.getCommand()),
                command.getPaginationAndSorting().generatePagingAndSortingRequest());
        return ApiBasePageDTO.generateSuccessResponse(ReceiptDTO.of(receiptPage.getContent()),
                PageDTO.ofPage(receiptPage));
    }

    @Override
    @Transactional
    public ApiBaseDTO<ReceiptDTO> saveReceipt(ApiBaseCommand<ReceiptSaveCommand> command) {
        Receipt receipt = command.getCommand().convertToEntity();

        return ApiBaseDTO.generateSuccessResponse(ReceiptDTO.fromEntity(this.receiptRepository.save(receipt)));
    }

    @Override
    @Transactional
    public ApiBaseDTO<ReceiptDTO> findReceipt(ApiBaseCommand<ReceiptFilterCommand> command) {
        final Optional<Receipt> receiptOptional = this.receiptRepository.findById(command.getCommand().getId());
        return receiptOptional.map(item -> ApiBaseDTO.generateSuccessResponse(ReceiptDTO.fromEntity(item))).orElse(
                ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    @Override
    public ApiBaseDTO<Boolean> deleteReceipt(ApiBaseCommand<ReceiptDeleteCommand> command) {
        this.receiptRepository.deleteById(command.getCommand().getId());
        return ApiBaseDTO.generateSuccessResponse(true);
    }
}
