package com.makeitvsolo.rainytoday.controller;

import com.makeitvsolo.rainytoday.config.security.JwtEncoder;
import com.makeitvsolo.rainytoday.controller.request.access.SignInRequest;
import com.makeitvsolo.rainytoday.controller.request.access.SignUpRequest;
import com.makeitvsolo.rainytoday.controller.response.ErrorMessageResponse;
import com.makeitvsolo.rainytoday.controller.response.TokenResponse;
import com.makeitvsolo.rainytoday.service.AccountService;
import com.makeitvsolo.rainytoday.service.dto.account.CreateAccountDto;
import com.makeitvsolo.rainytoday.service.exception.account.AccountAlreadyExistsException;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/access")
@Tag(name = "access")
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
    @Operation(summary = "sign up new account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "account created",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "account with given name already exists",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{ \"message\": \"Account :name already exists\" }")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
                            )
                    )
            }
    )
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
    @Operation(summary = "sign in account")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "account authenticated",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{ \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQSflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c\" }")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "account with given name does not exists",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{ \"message\": \"Account :name does not exists\" }")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "bad credentials",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{ message: \"Bad Credentials\" }")}
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {@ExampleObject(value = "{}")}
                            )
                    )
            }
    )
    public ResponseEntity<?> signIn(@RequestBody SignInRequest request) {
        try {
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getName(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(auth);

            var account = service.getAccountByName(request.getName());
            var token = jwtEncoder.encode(account.getId(), account.getName());

            return ResponseEntity.status(HttpStatus.OK)
                           .body(new TokenResponse(token));
        } catch (AccountDoesNotExistsException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                           .body(new ErrorMessageResponse(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                           .body(new ErrorMessageResponse(ex.getMessage()));
        }
    }
}
