package com.tech.younsik.dto.response;

import com.tech.younsik.constant.OrderConst;
import com.tech.younsik.dto.object.PurchaseObject;
import java.time.LocalDateTime;
import java.util.TimeZone;
import lombok.Data;

@Data
public class PurchaseResponse {

    private String name;
    private LocalDateTime paymentDate;
    private OrderConst.Status status;
    private String timeZone;

    public PurchaseResponse(PurchaseObject purchaseObject) {
        this.name = purchaseObject.getName();
        this.status = purchaseObject.getStatus();
        this.paymentDate = purchaseObject.getPaymentDate();
        this.timeZone = TimeZone.getDefault().getID();
    }
}
