package ma.enset.accountservice.rest;

import ma.enset.accountservice.domain.Account;
import ma.enset.accountservice.domain.AccountTrensaction;
import ma.enset.accountservice.domain.AccountType;
import ma.enset.accountservice.service.EbankService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class EBankRestController {
    private EbankService eBankService;

    public EBankRestController(EbankService eBankService) {
        this.eBankService = eBankService;
    }
    @GetMapping("/accounts/{accountId}")
    public Account getAccountById(@PathVariable String accountId) {
        return eBankService.getAccountById(accountId);
    }
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return eBankService.getAllAccounts();
    }

    @PostMapping("/accounts/save")
    public Account saveNewAccount(double initialBalance, AccountType type, String cirrency, Long customerId) {
        return eBankService.saveNewAccount(initialBalance, type, cirrency, customerId);
    }

    @PostMapping("/accounts/credit")
    public AccountTrensaction credit(String accountId, double amount) {
        return eBankService.credit(accountId, amount);
    }

    @PostMapping("/accounts/debit")
    public AccountTrensaction debit(String accountId, double amount) {
        return eBankService.debit(accountId, amount);
    }

    @ExceptionHandler
    public ResponseEntity exceptionHandler(Exception e){
        return ResponseEntity.internalServerError().body(Map.of("errorMassage", e.getMessage()));
    }
}
