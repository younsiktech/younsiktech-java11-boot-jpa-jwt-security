package com.tech.younsik.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tech.younsik.constant.OrderConst;
import com.tech.younsik.dto.object.PurchaseObject;
import com.tech.younsik.util.converter.OrderStatusConverter;
import com.tech.younsik.util.serializer.ISODateTimeSerializer;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "purchase")
public class Purchase {

    // 중복이 불가능한 임의의 영문 대문자, 숫자 조합 - 12자 제한
    @Id
    @GeneratedValue(generator = "purchaseIdGenerator")
    @GenericGenerator(name = "purchaseIdGenerator",
        strategy = "com.tech.younsik.config.generator.PurchaseIdGenerator"
    )
    @Column(name = "id", length = 12, nullable = false, updatable = false, unique = true)
    private String id;

    // emoji 를 포함한 모든 문자 - 100자 제한
    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private Long userId;

    // 결제일
    @CreationTimestamp
    @JsonSerialize(using = ISODateTimeSerializer.class)
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "status")
    @Convert(converter = OrderStatusConverter.class)
    private OrderConst.Status status;

    // 주문 생성일
    @CreationTimestamp
    @JsonSerialize(using = ISODateTimeSerializer.class)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 주문 정보 수정일
    @UpdateTimestamp
    @JsonSerialize(using = ISODateTimeSerializer.class)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public PurchaseObject toObject() {
        return PurchaseObject.builder()
            .id(id)
            .name(name)
            .paymentDate(paymentDate)
            .status(status)
            .createdAt(createdAt)
            .build();
    }
}
