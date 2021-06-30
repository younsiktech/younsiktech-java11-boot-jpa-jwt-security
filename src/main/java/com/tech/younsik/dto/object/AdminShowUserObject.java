package com.tech.younsik.dto.object;

import com.tech.younsik.constant.AuthConst;
import com.tech.younsik.constant.OrderConst;
import com.tech.younsik.constant.UserConst;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminShowUserObject {
    // user
    private Long id;
    private String email;
    private String name;
    private String nickname;
    private String phoneNumber;
    private AuthConst.Role role;
    private UserConst.Gender gender;
    private UserConst.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // purchase
    private String purchaseId;
    private String purchaseName;
    private OrderConst.Status purchaseStatus;
    private LocalDateTime paymentDate;
    private LocalDateTime purchaseCreatedAt;
    private LocalDateTime purchaseUpdatedAt;
}
