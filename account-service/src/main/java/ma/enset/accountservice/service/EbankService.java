package ma.enset.accountservice.service;

import ma.enset.accountservice.domain.*;
import ma.enset.accountservice.exceptions.BanlanceNotSuffiecientException;
import ma.enset.accountservice.exceptions.NegativeAmountException;
import ma.enset.accountservice.repository.AccountRepository;
import ma.enset.accountservice.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.enset.accountservice.exceptions.AccountNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EbankService {
    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;

    public EbankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }
    public Account getAccountById(String accountId){
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException("Account not found"));
        return account;
    }
    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }
    @Transactional
    public Account saveNewAccount(double initialBalance, AccountType type, String cirrency , Long customerId) {

        if (initialBalance < 0) throw new NegativeAmountException("The amount should be positive"); //Exception

        Account account = Account.builder()
                .id(UUID.randomUUID().toString())
                .customerId(customerId)
                .type(type)
                .status(AccountStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .currency(cirrency)
                .balance(initialBalance)
                .build();
        Account savedAccount = accountRepository.save(account);
        AccountTrensaction transaction = AccountTrensaction.builder()
                .amount(initialBalance)
                .type(TransactionType.CREDIT)
                .dateTime(LocalDateTime.now())
                .account(savedAccount)
                .build();
        transactionRepository.save(transaction);
        return savedAccount;
    }
    @Transactional
    public AccountTrensaction credit(String accountId, double amount){
        if (amount <= 0) throw new NegativeAmountException("The amount should be positive");
        Account account = getAccountById(accountId);
        AccountTrensaction accountTrensaction =
                AccountTrensaction.builder()
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .account(account)
                .type(TransactionType.CREDIT)
                .build();
        account.setBalance(account.getBalance()+amount);
        Account saveAccount = accountRepository.save(account);
        AccountTrensaction transaction = transactionRepository.save(accountTrensaction);
        return transaction;
    }
    @Transactional
    public AccountTrensaction debit(String accountId, double amount){
        if (amount <= 0) throw new NegativeAmountException("The amount should be positive");
        Account account = getAccountById(accountId);
        if(account.getBalance() < amount) throw new BanlanceNotSuffiecientException("Banlance Not Suffiecient Exception");
        AccountTrensaction accountTrensaction = AccountTrensaction.builder()
                .amount(amount)
                .dateTime(LocalDateTime.now())
                .account(account)
                .type(TransactionType.DEBIT)
                .build();
        account.setBalance(account.getBalance()-amount);
        Account saveAccount = accountRepository.save(account);
        AccountTrensaction transaction = transactionRepository.save(accountTrensaction);
        return transaction;
    }
}

