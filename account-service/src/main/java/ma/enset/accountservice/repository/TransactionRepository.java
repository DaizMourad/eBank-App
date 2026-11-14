package ma.enset.accountservice.repository;

import ma.enset.accountservice.domain.AccountTrensaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<AccountTrensaction, Long> {
}
