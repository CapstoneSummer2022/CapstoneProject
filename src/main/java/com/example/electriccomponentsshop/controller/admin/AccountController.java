package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.AccountDTO;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.Role;
import com.example.electriccomponentsshop.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@Controller
@RequestMapping("admin/accounts")
@AllArgsConstructor
public class AccountController {

    final
    AccountService accountService;
    @GetMapping("")
        public String viewAll(Model model){
        ArrayList<Account> accounts =(ArrayList<Account>) accountService.findAll();
        model.addAttribute("accounts",accounts);
        return "administrator/system-account-management";
    }
    @GetMapping("/add")
    public String viewAddForm(Model model){
        model.addAttribute("newAccount", new AccountDTO());
        return "administrator/add-account";
    }
    @PostMapping("/add")
    public String addAccount(Model model, @ModelAttribute("newAccount") AccountDTO accountDTO,BindingResult bindingResult){
        Set<String> strRoles = accountDTO.getRoles();

       Account account = new Account(accountDTO.getEmail(), accountDTO.getPassword());

          accountService.save(account);
          return "administrator/system-account-management";
    }
    @GetMapping("/system-account")
    public String viewAllSystemAccount(Model model){
        ArrayList<Account> systemAccounts = (ArrayList<Account>) accountService.findAllByRoleName("ROLE_EMPLOYEE","ROLE_MANAGER");
        if(systemAccounts.isEmpty()){
            model.addAttribute("empty", "Không có kết quả");
        }
        model.addAttribute("accounts",systemAccounts);
        return "administrator/system-account-management";
    }
    @GetMapping("/customer-account")
    public String viewAllCustomerAccount(Model model){
        ArrayList<Account> customerAccounts = (ArrayList<Account>) accountService.findAllByRoleName("ROLE_CUSTOMER");
        model.addAttribute("accounts",customerAccounts);
        return "administrator/customer-account-management";
    }
    @PostMapping("/edit/{id}")
        public String editInformation(Model model, @PathVariable @Valid Integer id, @ModelAttribute("account")AccountDTO accountDTO, BindingResult bindingResult){
        Optional<Account> account = accountService.findById(id);
        if(account.isPresent()){
            model.addAttribute("error", "Không tìm thấy nhân viên này");
        }
        else {
            Account account1 = account.get();
            account1.setName(accountDTO.getName());
            account1.setPhone(accountDTO.getPhone());
            accountService.save(account1);
            model.addAttribute("mess","Thay đổi thành công");
        }
        return "administrator/customer-account-management";
    }
    @GetMapping("/edit/{id}")
    public String viewEditForm(Model model, @PathVariable @Valid Integer id) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            model.addAttribute("error", "Không tìm thấy nhân viên này");
        } else {
            model.addAttribute("account",account.get());
        }
        return "administrator/setting-employee";
    }

}
