package ma.enset.accountservice.repository;

import ma.enset.accountservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
