package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.AccountDTO;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("admin/employees")
@AllArgsConstructor
public class EmployeeController {

    final
    AccountService accountService;
    @GetMapping("")
        public String viewInformationOfAllEmp(Model model){
        ArrayList<Account> accounts =(ArrayList<Account>) accountService.findAllByRoleName("ROLE_EMPLOYEE");
        model.addAttribute("accounts",accounts);
        return "administrator/personal-info";
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
        return "employee-infor-management";
    }


}
