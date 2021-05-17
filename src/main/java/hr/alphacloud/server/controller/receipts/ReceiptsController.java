package hr.alphacloud.server.controller.receipts;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.planning.PlanDeleteCommand;
import hr.alphacloud.server.model.command.planning.PlanFilterCommand;
import hr.alphacloud.server.model.command.planning.PlanSaveCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptDeleteCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptFilterCommand;
import hr.alphacloud.server.model.command.receipts.ReceiptSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.planning.PlanDTO;
import hr.alphacloud.server.model.dto.receipts.ReceiptDTO;
import hr.alphacloud.server.service.receipts.ReceiptsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/receipts")
public class ReceiptsController {
    private final ReceiptsService receiptsService;

    public ReceiptsController(ReceiptsService receiptsService) {
        this.receiptsService = receiptsService;
    }

    @PostMapping("/save")
    public ApiBaseDTO<ReceiptDTO> savePlan(@RequestBody @Valid ApiBaseCommand<ReceiptSaveCommand> command){
        return this.receiptsService.saveReceipt(command);
    }

    @PostMapping("/find-plan")
    public ApiBaseDTO<ReceiptDTO> getPlan(@RequestBody ApiBaseCommand<ReceiptFilterCommand> command){
        return this.receiptsService.findReceipt(command);
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<ReceiptDTO> filter(@RequestBody @Valid ApiBasePageCommand<ReceiptFilterCommand> command){
        return this.receiptsService.filterReceipt(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<ReceiptDeleteCommand> command){
        return this. receiptsService.deleteReceipt(command);
    }
}
