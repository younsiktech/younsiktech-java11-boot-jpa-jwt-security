package com.tech.younsik.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

@Data
public class PagingRequest {
    
    @Min(1)
    private int page;
    
    @Min(1)
    @Max(20)
    private int perPage;
    
    public PageRequest toPageRequest() {
        return PageRequest.of(this.page - 1, this.perPage);
    }
}