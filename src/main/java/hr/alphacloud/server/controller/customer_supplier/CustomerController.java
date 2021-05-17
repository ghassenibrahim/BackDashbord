package hr.alphacloud.server.controller.customer_supplier;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.customer_supplier.CustomerSaveCommand;
import hr.alphacloud.server.model.command.customer_supplier.CustomerSupplierFilterCommand;
import hr.alphacloud.server.model.dto.CustomerSupplierDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.entity.reporting.customer_supplier.Customer;
import hr.alphacloud.server.service.customer_supplier.CustomerSupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final CustomerSupplierService<Customer> customerService;

    public CustomerController(CustomerSupplierService<Customer> customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<CustomerSupplierDTO>> filter(@RequestBody @Valid ApiBasePageCommand<CustomerSupplierFilterCommand> command) {
        return this.customerService.filter(command, Customer.class);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> save(@RequestBody @Valid ApiBaseCommand<CustomerSaveCommand> command) {
        return this.customerService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.customerService.delete(command);
    }

    @PostMapping("/table")
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(@RequestBody @Valid ApiBasePageCommand<CustomerSupplierFilterCommand> command) {
        return this.customerService.getDataTable(command, Customer.class);
    }
}
