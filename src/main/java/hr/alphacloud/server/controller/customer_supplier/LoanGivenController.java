package hr.alphacloud.server.controller.customer_supplier;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.customer_supplier.CustomerSupplierFilterCommand;
import hr.alphacloud.server.model.command.customer_supplier.LoanGivenSaveCommand;
import hr.alphacloud.server.model.dto.CustomerSupplierDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.LoanGiven;
import hr.alphacloud.server.service.customer_supplier.CustomerSupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/loan-given")
public class LoanGivenController {

    private final CustomerSupplierService<LoanGiven> loanGivenService;

    public LoanGivenController(CustomerSupplierService<LoanGiven> loanGivenService) {
        this.loanGivenService = loanGivenService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<CustomerSupplierDTO>> filter(@RequestBody @Valid ApiBasePageCommand<CustomerSupplierFilterCommand> command) {
        return this.loanGivenService.filter(command, LoanGiven.class);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> save(@RequestBody @Valid ApiBaseCommand<LoanGivenSaveCommand> command) {
        return this.loanGivenService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.loanGivenService.delete(command);
    }

    @PostMapping("/table")
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(@RequestBody @Valid ApiBasePageCommand<CustomerSupplierFilterCommand> command) {
        return this.loanGivenService.getDataTable(command, LoanGiven.class);
    }
}
