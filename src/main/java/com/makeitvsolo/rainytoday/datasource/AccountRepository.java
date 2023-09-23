package com.makeitvsolo.rainytoday.datasource;

import com.makeitvsolo.rainytoday.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
