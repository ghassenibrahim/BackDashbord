package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.model.entity.reporting.base.AbstractCustomerSupplier;
import hr.alphacloud.server.model.entity.reporting.base.CustomerSupplierProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CustomerSupplierDTO extends AbstractReportDTO implements Serializable {

    List<CustomerSupplierProperties> customerSupplierPropertiesList;

    public static <T extends AbstractCustomerSupplier> CustomerSupplierDTO of(T customerSupplier) {
        CustomerSupplierDTO cmd = new CustomerSupplierDTO();
        cmd.customerSupplierPropertiesList = customerSupplier.getCustomerSupplierProperties();
        cmd.id = customerSupplier.getId();
        cmd.importType = customerSupplier.getImportType();
        cmd.report = ReportDTO.of(customerSupplier.getReport());
        cmd.premises = PremisesDTO.fromEntity(customerSupplier.getPremises());
        cmd.sectorType = SectorTypeDTO.fromEntity(customerSupplier.getSectorType());
        cmd.spendingLocation = SpendingLocationDTO.of(customerSupplier.getSpendingLocation());

        return cmd;


    }

    public static List<CustomerSupplierDTO> of(List<? extends AbstractCustomerSupplier> list) {
        return list.stream().map(CustomerSupplierDTO::of).collect(Collectors.toList());
    }
}
