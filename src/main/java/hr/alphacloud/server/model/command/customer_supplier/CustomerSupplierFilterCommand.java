package hr.alphacloud.server.model.command.customer_supplier;

import hr.alphacloud.server.model.command.base.AbstractReportFilterCommand;
import lombok.Getter;

@Getter
public class CustomerSupplierFilterCommand extends AbstractReportFilterCommand {
    private String name;
    private String liabilityType;
    private String receivableType;
}
