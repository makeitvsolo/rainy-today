package com.makeitvsolo.rainytoday.controller.request.access;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public final class SignUpRequest {

    private final String name;
    private final String password;
}
