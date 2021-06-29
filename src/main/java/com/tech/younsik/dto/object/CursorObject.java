package com.tech.younsik.dto.object;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class CursorObject {
    private int page;
    private int perPage;
    private int pageCount;
    private Long totalCount;
    private int recordCount;

    public CursorObject(Page page) {
        this.page = page.getNumber() + 1;
        this.perPage = page.getSize();
        this.pageCount = page.getTotalPages();
        this.totalCount = page.getTotalElements();
        this.recordCount = page.getNumberOfElements();
    }
}
