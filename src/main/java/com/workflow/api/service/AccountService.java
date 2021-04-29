package com.workflow.api.service;

import com.workflow.api.annotation.EnableWorkFlow;
import com.workflow.api.repository.schema.Account;
import com.workflow.api.repository.jpa.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AnnotationScannerService scannerService;

    public AccountService(AccountRepository accountRepository, AnnotationScannerService scannerService) {
        this.accountRepository = accountRepository;
        this.scannerService = scannerService;
    }

    @EnableWorkFlow()
    public void createAccount(Account account) {
       List<String> methods = scannerService.getMethods("com.workflow");
//        for (String method : methods) {
//            System.out.println(method);
//        }
//        if(methods.contains("createAccount")){
//            return;
//        }
//       configure("createAccount");
        accountRepository.save(account);
    }

    @EnableWorkFlow(value = "locaApplication")
    public void loanApplication(){
        List<String> methods = scannerService.getMethods("com.workflow");
        if(methods.contains("createAccount")){
            return;
        }
    }
}
