package hr.alphacloud.server.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageUtil {

    private PageUtil() {
    }

    /**
     * Generates new page from collection
     *
     * @param pageable   - pageable
     * @param collection - collection
     * @param <T>
     * @return - page
     */
    public static <T> Page<T> ofCollection(Pageable pageable, List<T> collection) {
        int pageSize = Math.min(collection.size(), pageable.getPageSize());
        return new PageImpl<>(collection.subList(0, pageSize), pageable, collection.size());
    }
}
