package com.makeitvsolo.rainytoday.controller;

import com.makeitvsolo.rainytoday.config.security.JwtEncoder;
import com.makeitvsolo.rainytoday.controller.request.access.SignInRequest;
import com.makeitvsolo.rainytoday.controller.request.access.SignUpRequest;
import com.makeitvsolo.rainytoday.controller.response.ErrorMessageResponse;
import com.makeitvsolo.rainytoday.service.AccountService;
import com.makeitvsolo.rainytoday.service.dto.account.CreateAccountDto;
import com.makeitvsolo.rainytoday.service.exception.account.AccountAlreadyExistsException;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/access")
public class AccountAccessController {

    private final AccountService service;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public AccountAccessController(
            AccountService service, AuthenticationManager authenticationManager, JwtEncoder jwtEncoder
    ) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        try {
            var payload = new CreateAccountDto(request.getName(), (request.getPassword()));

            service.createAccount(payload);
            return ResponseEntity.status(HttpStatus.CREATED)
                           .build();
        } catch (AccountAlreadyExistsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .build();
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            var account = service.getAccountByName(request.getName());
            var token = jwtEncoder.encode(account.getId(), account.getName());

            return ResponseEntity.status(HttpStatus.OK)
                           .body(token);
        } catch (AccountDoesNotExistsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        }
        catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .build();
        }
    }
}
