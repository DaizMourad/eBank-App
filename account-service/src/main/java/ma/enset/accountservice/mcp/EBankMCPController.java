package ma.enset.accountservice.mcp;

import ma.enset.accountservice.domain.Account;
import ma.enset.accountservice.domain.AccountTrensaction;
import ma.enset.accountservice.domain.AccountType;
import ma.enset.accountservice.repository.AccountRepository;
import ma.enset.accountservice.service.EbankService;
import org.springframework.ai.mcp.annotation.McpArg;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class EBankMCPController {
    private EbankService eBankService;

    public EBankMCPController(EbankService eBankService) {
        this.eBankService = eBankService;
    }
    @McpTool(description = "Get Account By Id")
    public Account getAccountById(
            @McpArg(description = "The accont id")String accountId) {
        return eBankService.getAccountById(accountId);
    }
    @McpTool(description = "Get All Account")
    public List<Account> getAllAccounts() {
        return eBankService.getAllAccounts();
    }
    @McpTool(description = "Save a new Account")
    @Transactional
    public Account saveNewAccount(
            @McpArg(description = "The initial balance")double initialBalance,
            @McpArg(description = "The Accont Typw")AccountType type,
            @McpArg(description = "The accont currency")String cirrency,
            @McpArg(description = "The customer Id")Long customerId) {
        return eBankService.saveNewAccount(initialBalance, type, cirrency, customerId);
    }
    @McpTool(description = "credit Account")
    @Transactional
    public AccountTrensaction credit(
            @McpArg(description = "The accont id")String accountId,
            @McpArg(description = "The credited amount")double amount) {
        return eBankService.credit(accountId, amount);
    }
    @McpTool(description = "debit Account")
    @Transactional
    public AccountTrensaction debit(
            @McpArg(description = "The accont id")String accountId,
            @McpArg(description = "The debited amount")double amount) {
        return eBankService.debit(accountId, amount);
    }

}
