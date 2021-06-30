package com.tech.younsik.dto.request;

import com.tech.younsik.dto.object.PurchaseObject;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseRequest {

    @NotNull
    @Size(max = 100, message = "Wrong purchase name : Max 100 required")
    @ApiModelProperty(name = "구매 상품 name", notes = "Emoji 허용 문자열, 최대 100자", required = true)
    private String name;

    public PurchaseObject toObject() {
        return PurchaseObject.builder()
            .name(name)
            .build();
    }
}
