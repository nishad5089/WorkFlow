package com.workflow.api.service;

import com.workflow.api.annotation.EnableWorkFlow;
import com.workflow.api.domain.*;
import com.workflow.api.repository.jpa.ConfigurationRepository;
import com.workflow.api.repository.jpa.InstanceRepository;
import com.workflow.api.repository.jpa.MapperRepository;
import com.workflow.api.repository.schema.Account;
import com.workflow.api.repository.jpa.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author Abdur Rahim Nishad
 * @since 1.0.0
 */
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AnnotationScannerService scannerService;
    private final ConfigurationRepository configurationRepository;
    private final MapperRepository mapperRepository;
    private final InstanceRepository instanceRepository;

    public AccountService(AccountRepository accountRepository, AnnotationScannerService scannerService, ConfigurationRepository configurationRepository, MapperRepository mapperRepository, InstanceRepository instanceRepository) {
        this.accountRepository = accountRepository;
        this.scannerService = scannerService;
        this.configurationRepository = configurationRepository;
        this.mapperRepository = mapperRepository;
        this.instanceRepository = instanceRepository;
    }

    @EnableWorkFlow()
    public void createAccount(Account account) {
        List<String> methods = scannerService.getMethods("com.workflow");
        if (methods.contains("createAccount")) {
            dataSeeding();
            return;
        }
        accountRepository.save(account);
    }

    private void dataSeeding() {
        Maker maker = new Maker()
                .setName("Asif")
                .setDepartment("IT")
                .setRole("STUFF");
        Checker checker1 = new Checker()
                .setName("Nishad")
                .setCheckerNo(1)
                .setAccess("WRITE")
                .setRole("MANAGER")
                .setIsOnline(true)
                .setStatus(CheckerStatus.NOT_ASSIGNED);
        Checker checker2 = new Checker()
                .setName("Azam")
                .setCheckerNo(2)
                .setAccess("WRITE")
                .setRole("MANAGER")
                .setIsOnline(true)
                .setStatus(CheckerStatus.NOT_ASSIGNED);
        List<Checker> checkers = Arrays.asList(checker1, checker2);

        Configuration configuration = new Configuration()
                .setMaker(maker)
                .setChecker(checkers)
                .setDepartment("MANAGER")
                .setLabel("1B")
                .setLabelLogic(LabelLogic.UPPER)
                .setMaxCheckerNo(2);

        Task task = new Task()
                .setName("Account Create")
                .setState(TaskState.PENDING)
                .setDescription("Account Creation")
                .setPriority(1);

        Mapper mapper = new Mapper()
                .setConfiguration(configuration)
                .setTask(task);
        mapper = mapperRepository.save(mapper);

        Instance instance = new Instance()
                .setName("Account Creation")
                .setEventName("Account Create")
                .setDescription("Customer Account Creation")
                .setMapper(mapper)
                .setStatus(Status.ACTIVE)
                .setStartAt(LocalDateTime.now())
                .setWorkPlaceName("Agrabad Branch")
                .setLabelTracker(0)
                .setPayload("Payload")
                .setEndAt(null);
        instanceRepository.save(instance);
    }

    @EnableWorkFlow(value = "locaApplication")
    public void loanApplication() {
        List<String> methods = scannerService.getMethods("com.workflow");
        if (methods.contains("createAccount")) {
            return;
        }
    }
}
