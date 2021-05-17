package hr.alphacloud.server.model.dto.settings;

import hr.alphacloud.server.model.entity.reporting.settings.WarehouseType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class WarehouseTypeDTO implements Serializable {
    private Long id;
    private String value;
    private String locale;
    private Long companyId;
    private AccountBookDTO initStateBalanceCode;
    private AccountBookDTO finalStateBalanceCode;

    public static WarehouseTypeDTO of(WarehouseType warehouse) {
        if (warehouse != null) {
            return builder()
                    .id(warehouse.getId())
                    .value(warehouse.getValue())
                    .locale(warehouse.getLocale())
                    .companyId(warehouse.getCompanyId())
                    .initStateBalanceCode(AccountBookDTO.of(warehouse.getInitStateBalanceCode()))
                    .finalStateBalanceCode(AccountBookDTO.of(warehouse.getFinalStateBalanceCode()))
                    .build();
        }
        return null;
    }

    public static List<WarehouseTypeDTO> of(List<WarehouseType> warehouseList) {
        return warehouseList.stream()
                .map(WarehouseTypeDTO::of)
                .collect(Collectors.toList());
    }
}
