package hr.alphacloud.server.model.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class PaginationAndSortingCommand {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int UNLIMITED_PAGE_SIZE = -1;
    public static final String DEFAULT_SORT_PROPERTY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "ASC";

    // Using on request
    protected int currentPage = 1;
    protected int pageSize = DEFAULT_PAGE_SIZE;
    protected String sortProperty = DEFAULT_SORT_PROPERTY;
    protected String sortDirection = DEFAULT_SORT_DIRECTION;

    // Using on response
    protected int totalPages;
    protected long totalElements;
    protected long filterRange;

    public PaginationAndSortingCommand(long filterRange, int pageSize) {
        this.filterRange = filterRange;
        this.pageSize = pageSize;
    }

    public Pageable generatePagingAndSortingRequest() {
        final int pageSize = Optional.of(this.pageSize).filter(s -> s >= 0).orElse(DEFAULT_PAGE_SIZE);
        if (pageSize > 0) {
            final Integer page = Optional.of(this.currentPage - 1).orElse(0);
            final Sort sort = getSort();
            return PageRequest.of(page, pageSize, sort);
        } else {
            return Pageable.unpaged();
        }
    }

    public static <T> PaginationAndSortingCommand createFromPage(Page<T> page) {
        PaginationAndSortingCommand command = new PaginationAndSortingCommand();
        command.currentPage = page.getPageable().getPageNumber() + 1;
        command.setPageSize(page.getSize());
        command.setTotalPages(page.getTotalPages());
        command.setTotalElements(page.getTotalElements());
        return command;
    }

    private Sort getSort() {
        final Sort.Direction sortDir = Sort.Direction
                .fromOptionalString(sortDirection)
                .orElse(Sort.DEFAULT_DIRECTION);

        return Sort.by(sortDir, Optional.ofNullable(this.sortProperty)
                .filter(s -> !s.isEmpty()).orElse(DEFAULT_SORT_PROPERTY));
    }

    public int getStartAt() {
        return (this.getCurrentPage() - 1) * this.getPageSize();
    }

    public int getMaxResults() {
        return this.getPageSize();
    }

}