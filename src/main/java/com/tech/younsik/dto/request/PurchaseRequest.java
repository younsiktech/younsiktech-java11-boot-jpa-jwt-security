package com.tech.younsik.dto.request;

import com.tech.younsik.dto.object.PurchaseObject;
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
    @Size(max = 100, message = "Wrong name : Max 100 required")
    private String name;

    public PurchaseObject toObject() {
        return PurchaseObject.builder()
            .name(name)
            .build();
    }
}
