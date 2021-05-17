package hr.alphacloud.server.model.command.settings.warehouse_type;

import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import hr.alphacloud.server.model.entity.reporting.settings.WarehouseType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WarehouseTypeSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    @NotEmpty
    private String value;
    @NotEmpty
    private String locale;
    private AccountBook initStateBalanceCode;
    private AccountBook finalStateBalanceCode;

    public WarehouseType convertToEntity() {
        WarehouseType warehouse = new WarehouseType();
        warehouse.setId(this.id);
        warehouse.setCompanyId(this.companyId);
        warehouse.setFinalStateBalanceCode(this.initStateBalanceCode);
        warehouse.setInitStateBalanceCode(this.finalStateBalanceCode);

        this.updateEntity(warehouse);
        return warehouse;
    }

    public void updateEntity(WarehouseType warehouseType) {
        warehouseType.setValue(this.value);
        warehouseType.setLocale(this.locale);
        warehouseType.setInitStateBalanceCode(this.initStateBalanceCode);
        warehouseType.setFinalStateBalanceCode(this.finalStateBalanceCode);
    }
}
