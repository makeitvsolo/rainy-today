package com.makeitvsolo.rainytoday.service;

import com.makeitvsolo.rainytoday.datasource.AccountRepository;
import com.makeitvsolo.rainytoday.model.account.Account;
import com.makeitvsolo.rainytoday.service.dto.account.AccountDto;
import com.makeitvsolo.rainytoday.service.dto.account.CreateAccountDto;
import com.makeitvsolo.rainytoday.service.exception.account.AccountAlreadyExistsException;
import com.makeitvsolo.rainytoday.service.exception.account.AccountDoesNotExistsException;
import com.makeitvsolo.rainytoday.service.mapping.AccountMapper;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.*;
import static org.mockito.Mockito.*;

@DisplayName("AccountService")
public class AccountServiceTests {

    private AccountService service;

    @Mock
    private AccountRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccountMapper mapper;

    private AutoCloseable closeable;

    @BeforeEach
    public void beforeEach() {
        closeable = openMocks(this);

        service = new AccountService(repository, passwordEncoder, mapper);
    }

    @AfterEach
    public void afterEach() throws Exception {
        closeable.close();
    }

    @Nested
    @DisplayName("creates account")
    public class CreatesAccount {

        @Test
        @DisplayName("success when account with given name does not exists")
        public void successWhenAccountWithGivenNameDoesNotExists() {
            var name = "name";
            var password = "password";
            var payload = new CreateAccountDto(name, password);

            when(repository.existsByName(name))
                    .thenReturn(false);

            service.createAccount(payload);

            verify(repository).save(new Account(name, password));
        }

        @Test
        @DisplayName("failure when account with given name already exists")
        public void failureWhenAccountWithGivenNameAlreadyExists() {
            var name = "name";
            var password = "password";
            var payload = new CreateAccountDto(name, password);

            when(repository.existsByName(name))
                    .thenReturn(true);

            assertThrows(AccountAlreadyExistsException.class, () -> service.createAccount(payload));
        }
    }

    @Nested
    @DisplayName("gives account by name")
    public class GivesAccountByName {

        private Account existingAccount;
        private AccountDto expectedAccountDto;

        @BeforeEach
        public void beforeEach() {
            existingAccount = new Account("name", "password");

            expectedAccountDto = new AccountDto(
                    0L,
                    existingAccount.getName(),
                    existingAccount.getPassword()
            );
        }

        @Test
        @DisplayName("success when account with given name exists")
        public void successWhenAccountWithGivenNameExists() {
            var accountName = "name";

            when(repository.findByName(accountName))
                    .thenReturn(Optional.of(existingAccount));
            when(mapper.mapFrom(existingAccount))
                    .thenReturn(expectedAccountDto);

            assertEquals(expectedAccountDto, service.getAccountByName(accountName));
        }

        @Test
        @DisplayName("failure when account with given name does not exists")
        public void failureWhenAccountWithGivenNameDoesNotExists() {
            var accountName = "name";

            when(repository.findByName(accountName))
                    .thenReturn(Optional.empty());

            assertThrows(AccountDoesNotExistsException.class, () -> service.getAccountByName(accountName));
        }
    }
}
