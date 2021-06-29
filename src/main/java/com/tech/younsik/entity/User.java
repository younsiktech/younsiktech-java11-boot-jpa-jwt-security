package com.tech.younsik.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tech.younsik.constant.AuthConst;
import com.tech.younsik.constant.UserConst.Gender;
import com.tech.younsik.constant.UserConst.Status;
import com.tech.younsik.dto.object.UserObject;
import com.tech.younsik.util.serializer.ISODateTimeSerializer;
import com.tech.younsik.util.converter.AuthRoleConverter;
import com.tech.younsik.util.converter.UserGenderConverter;
import com.tech.younsik.util.converter.UserStatusConverter;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(value = {AuditingEntityListener.class})
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이메일 형식, 100자 제한
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    // 영어 대소문자, 특수문자, 숫자 1개 이상 씩 포함, 10자 이상
    @JsonIgnore
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    // 20자 제한 한글, 영대소문자 허용
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    // 30자 제한, 영소문자만 허용
    @Column(name = "nickname", length = 30, nullable = false)
    private String nickname;

    // 숫자로만 입력
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "gender")
    @Convert(converter = UserGenderConverter.class)
    private Gender gender;

    @JsonIgnore
    @Column(name = "status", nullable = false)
    @Convert(converter = UserStatusConverter.class)
    private Status status;

    @Column(name="role")
    @Convert(converter = AuthRoleConverter.class)
    private AuthConst.Role role;

    // 회원 가입일
    @CreationTimestamp
    @JsonSerialize(using = ISODateTimeSerializer.class)
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // 회원 정보 수정일
    @UpdateTimestamp
    @JsonSerialize(using = ISODateTimeSerializer.class)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public UserObject toObject() {
        return UserObject.builder()
            .id(id)
            .email(email)
            .name(name)
            .nickname(nickname)
            .phoneNumber(phoneNumber)
            .gender(gender)
            .role(role)
            .updatedAt(updatedAt)
            .build();
    }
}
