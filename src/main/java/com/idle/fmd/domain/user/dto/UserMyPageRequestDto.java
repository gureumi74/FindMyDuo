package com.idle.fmd.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserMyPageRequestDto {
    private String accountId;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    private String nickname;
}
