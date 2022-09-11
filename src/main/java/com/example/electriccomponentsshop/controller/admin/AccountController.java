package com.example.electriccomponentsshop.controller.admin;

import com.example.electriccomponentsshop.dto.*;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.Role;
import com.example.electriccomponentsshop.services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("admin/accounts")
@AllArgsConstructor
public class AccountController {

    final
    AccountService accountService;
    final RoleService roleService;
    final ProvinceService provinceService;
    final DistrictService districtService;
    final WardService wardService;
    @GetMapping("/add")
    public String viewAddForm(ModelMap model){
        model.addAttribute("accountDto", new AccountDTO());
        getAddress(model);
        return "administrator/add-employee";
    }

    private ModelMap getAddress(ModelMap model) {

        List<ProvinceDTO> provinceDTOS = provinceService.findAll();
        model.addAttribute("listProvince", provinceDTOS);
        List<DistrictDTO> districtDTOS = districtService.findByProvinceName(provinceDTOS.get(0).getName());
        model.addAttribute("listDistrict", districtDTOS);
        List<WardDTO> wardDTOS = wardService.findByDistrictName(districtDTOS.get(0).getName());
        model.addAttribute("listWard", wardDTOS);
        return model;
    }

    @PostMapping("/add")
    public String addAccount(ModelMap model, @Valid @ModelAttribute("accountDto") AccountDTO accountDTO, BindingResult bindingResult){
        System.out.println("gg48");
       // Set<String> strRoles = accountDTO.getRoles();
        boolean isExist = accountService.existsAccountByEmail(accountDTO.getEmail());
        if(bindingResult.hasErrors()|| isExist){
            if(isExist){
                model.addAttribute("duplicate","Email đã tồn tại");
            }
            System.out.println("đây f");
            bindingResult.getFieldErrors().forEach(fieldError -> {
                System.out.println(fieldError.getDefaultMessage()+"lol"+ fieldError.getField());
                        model.addAttribute(fieldError.getField(),fieldError.getDefaultMessage());});

            model.addAttribute("accountDto",accountDTO);
            List<ProvinceDTO> provinceDTOS = provinceService.findAll();
            model.addAttribute("listProvince", provinceDTOS);
            List<DistrictDTO> districtDTOS = districtService.findByProvinceName(accountDTO.getProvinceName());
            model.addAttribute("listDistrict", districtDTOS);
            List<WardDTO> wardDTOS = wardService.findByDistrictName(accountDTO.getDistrictName());
            model.addAttribute("listWard", wardDTOS);
            return "administrator/add-employee";
        }
        System.out.println("ggg");
        accountService.addAccount(accountDTO);
        return "redirect:/admin/accounts/system-account";
    }
    @GetMapping("/system-account")
    public String viewAllSystemAccount(Model model,@ModelAttribute("error") String mess){
        ArrayList<AccountDTO> systemAccounts = (ArrayList<AccountDTO>) accountService.findAllByRoleName("ROLE_EMPLOYEE","ROLE_MANAGER");
        if(systemAccounts.isEmpty()){
            model.addAttribute("empty", "Không có kết quả");
        }
        model.addAttribute("accounts",systemAccounts);
        model.addAttribute("error",mess);
        return "administrator/system-account-management";
    }
    @GetMapping("/customer-account")
    public String viewAllCustomerAccount(Model model){
        ArrayList<AccountDTO> customerAccounts = (ArrayList<AccountDTO>) accountService.findAllByRoleName("ROLE_CUSTOMER");
        model.addAttribute("accounts",customerAccounts);
        return "administrator/customer-account-management";
    }
    @PostMapping("/edit/{id}")
        public String editInformation(Model model, @PathVariable @Valid Integer id,@Valid @ModelAttribute("accountDto")AccountDTO accountDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            bindingResult.getFieldErrors().forEach(fieldError -> model.addAttribute(fieldError.getField(),fieldError.getDefaultMessage()));
        }
        try{
            accountService.updateAccount(accountDTO,id);
        } catch (NoSuchElementException elementException){
            model.addAttribute("notFound",elementException.getMessage());
        }

        return "redirect:/admin/accounts/edit/"+id;
    }
    @GetMapping("/edit/{strId}")
    public String viewEditForm(Model model, @PathVariable String strId, RedirectAttributes redirectAttributes) {

        try{
             Integer id = Integer.parseInt(strId);
             AccountDTO accountDTO = accountService.findById(id);
             List<RoleDTO> roles = roleService.findAll();
             List<ProvinceDTO> provinceDTOS = provinceService.findAll();
            model.addAttribute("listProvince", provinceDTOS);
            List<DistrictDTO> districtDTOS = districtService.findByProvinceName(accountDTO.getProvinceName());
            model.addAttribute("listDistrict", districtDTOS);
            List<WardDTO> wardDTOS = wardService.findByDistrictName(accountDTO.getDistrictName());
            model.addAttribute("listWard", wardDTOS);
             model.addAttribute("roles",roles);
             model.addAttribute("accountDto",accountDTO);
            }
        catch (NoSuchElementException | NumberFormatException e ){
            redirectAttributes.addFlashAttribute("error",e.getMessage());
            return "redirect:/admin/accounts/system-account";
        }




        return "administrator/setting-employee";
    }

}
