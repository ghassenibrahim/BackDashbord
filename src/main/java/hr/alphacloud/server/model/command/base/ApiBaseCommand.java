package hr.alphacloud.server.model.command.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Data
public class ApiBaseCommand<T> {
    @NotNull
    @Valid
    private ApiBaseStateCommand state;
    @Valid
    private T command;
}
