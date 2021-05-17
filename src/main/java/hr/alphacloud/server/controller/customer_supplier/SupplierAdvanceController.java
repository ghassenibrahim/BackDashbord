package hr.alphacloud.server.controller.customer_supplier;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.customer_supplier.CustomerSupplierFilterCommand;
import hr.alphacloud.server.model.command.customer_supplier.SupplierAdvanceSaveCommand;
import hr.alphacloud.server.model.dto.CustomerSupplierDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.SupplierAdvance;
import hr.alphacloud.server.service.customer_supplier.CustomerSupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/supplier-advances")
public class SupplierAdvanceController {

    private final CustomerSupplierService<SupplierAdvance> supplierService;

    public SupplierAdvanceController(CustomerSupplierService<SupplierAdvance> supplierAdvanceService) {
        this.supplierService = supplierAdvanceService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<CustomerSupplierDTO>> filter(@RequestBody @Valid ApiBasePageCommand<CustomerSupplierFilterCommand> command) {
        return this.supplierService.filter(command, SupplierAdvance.class);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> save(@RequestBody @Valid ApiBaseCommand<SupplierAdvanceSaveCommand> command) {
        return this.supplierService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.supplierService.delete(command);
    }

    @PostMapping("/table")
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(@RequestBody @Valid ApiBasePageCommand<CustomerSupplierFilterCommand> command) {
        return this.supplierService.getDataTable(command, SupplierAdvance.class);
    }
}
