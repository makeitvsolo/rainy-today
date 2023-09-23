package com.makeitvsolo.rainytoday.service.dto.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class AccountDto {

    private final String name;
    private final String password;
}
