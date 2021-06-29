package com.tech.younsik.dto.object;

import com.tech.younsik.constant.OrderConst;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseObject {
    private String id;
    private Long userId;
    private String email;
    private String name;
    private LocalDateTime paymentDate;
    private LocalDateTime createdAt;
    private OrderConst.Status status;
}
