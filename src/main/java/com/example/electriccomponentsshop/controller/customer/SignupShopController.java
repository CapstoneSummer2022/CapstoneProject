package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.common.JwtUtils;
import com.example.electriccomponentsshop.dto.MessageResponse;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.Role;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import com.example.electriccomponentsshop.repositories.RoleRepository;
import com.example.electriccomponentsshop.services.OrderService;
import com.example.electriccomponentsshop.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class SignupShopController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String goToSignUp () {

        System.out.println("haha");

        return "customer/signup";
    }

    @PostMapping("/signup")
    public String signUp (@RequestParam Map<String, String> custom, ModelMap map) {
        if (accountRepository.existsAccountByEmail(custom.get("email"))) {
            map.addAttribute("error", "Email đã được sử dụng");
            return "customer/signup";
        } else {
            Account account = new Account(custom.get("email"), passwordEncoder.encode(custom.get("pwd")));

            List<Role> roles = new ArrayList<>();
            Role roleCustomer = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(roleCustomer);

            account.setRoles(roles);
            accountRepository.save(account);

        }

        return "redirect:/auth/signin";
    }
}
