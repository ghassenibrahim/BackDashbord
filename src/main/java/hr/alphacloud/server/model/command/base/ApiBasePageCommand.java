package hr.alphacloud.server.model.command.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiBasePageCommand<T> extends ApiBaseCommand<T> {
    private PaginationAndSortingCommand paginationAndSorting = new PaginationAndSortingCommand();
}
