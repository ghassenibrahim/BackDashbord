package hr.alphacloud.server.controller;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.brutto_balance.BruttoBalanceFilterCommand;
import hr.alphacloud.server.model.command.brutto_balance.BruttoBalanceSaveCommand;
import hr.alphacloud.server.model.dto.BruttoBalanceDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.service.BruttoBalanceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/bruto-balance")
public class BruttoBalanceController {

    private final BruttoBalanceService bruttoBalanceService;

    public BruttoBalanceController(BruttoBalanceService bruttoBalanceService) {
        this.bruttoBalanceService = bruttoBalanceService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<BruttoBalanceDTO>> filterBruttoBalance(@RequestBody @Valid ApiBasePageCommand<BruttoBalanceFilterCommand> command) {
        return bruttoBalanceService.filterBruttoBalance(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> saveBruttoBalance(@RequestBody @Valid ApiBaseCommand<BruttoBalanceSaveCommand> command) {
        return this.bruttoBalanceService.saveBruttoBalance(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> deleteBruttoBalance(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.bruttoBalanceService.deleteBruttoBalance(command);
    }

    @PostMapping("/table")
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(@RequestBody @Valid ApiBasePageCommand<BruttoBalanceFilterCommand> command) {
        return this.bruttoBalanceService.getDataTable(command);
    }
}
