package hr.alphacloud.server.service.customer_supplier;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.customer_supplier.CustomerSupplierFilterCommand;
import hr.alphacloud.server.model.command.customer_supplier.base.AbstractCustomerSupplierSaveCommand;
import hr.alphacloud.server.model.dto.CustomerSupplierDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.entity.reporting.base.AbstractCustomerSupplier;

import java.util.List;

public interface CustomerSupplierService<T extends AbstractCustomerSupplier> {

    ApiBaseDTO<List<CustomerSupplierDTO>> filter(ApiBasePageCommand<CustomerSupplierFilterCommand> command, Class<T> entity);

    ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<CustomerSupplierFilterCommand> command,
                                                      Class<T> entity);

    <S extends AbstractCustomerSupplierSaveCommand<T>> ApiBaseDTO<Boolean> save(ApiBaseCommand<S> saveCommand);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> deleteCommand);
}

