package com.tech.younsik.dto.response;

import com.tech.younsik.dto.object.CursorObject;
import java.util.List;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PagingResponse<T> {

    private CursorObject cursor;
    private List<T> records;

    public PagingResponse(Page page, List<T> records) {
        this.cursor = new CursorObject(page);
        this.records = records;
    }

}