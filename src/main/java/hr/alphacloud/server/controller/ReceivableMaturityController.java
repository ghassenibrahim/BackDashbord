package hr.alphacloud.server.controller;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.receivable_maturity.ReceivableMaturityFilterCommand;
import hr.alphacloud.server.model.command.receivable_maturity.ReceivableMaturitySaveCommand;
import hr.alphacloud.server.model.dto.ReceivableMaturityDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.service.ReceivableMaturityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/receivable-maturity")
public class ReceivableMaturityController {

    private final ReceivableMaturityService receivableMaturityService;

    public ReceivableMaturityController(ReceivableMaturityService receivableMaturityService) {
        this.receivableMaturityService = receivableMaturityService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<ReceivableMaturityDTO>> filteReceivableMaturity(@RequestBody @Valid ApiBasePageCommand<ReceivableMaturityFilterCommand> command) {
        return receivableMaturityService.filteReceivableMaturity(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> saveReceivableMaturity(@RequestBody @Valid ApiBaseCommand<ReceivableMaturitySaveCommand> command) {
        return this.receivableMaturityService.saveReceivableMaturity(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> deleteReceivableMaturity(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.receivableMaturityService.deleteReceivableMaturity(command);
    }

    @PostMapping("/table")
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(@RequestBody @Valid ApiBasePageCommand<ReceivableMaturityFilterCommand> command) {
        return this.receivableMaturityService.getDataTable(command);
    }
}
