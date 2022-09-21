package com.example.electriccomponentsshop.controller.customer;

import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.dto.CategoryDTO;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.Cart;
import com.example.electriccomponentsshop.entities.Category;
import com.example.electriccomponentsshop.entities.Role;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import com.example.electriccomponentsshop.repositories.CartRepository;
import com.example.electriccomponentsshop.repositories.RoleRepository;
import com.example.electriccomponentsshop.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SignupShopController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CategoryService categoryService;


    @GetMapping("/signup")
    public String goToSignUp (ModelMap map) {
        List<Category> categories = categoryService.findCategoriesByParentCategoryIdIsNull();

        map.addAttribute("categories", categories);

        return "customer/signup";
    }

    @PostMapping("/signup")
    public String signUp (@RequestParam Map<String, String> custom, ModelMap map) {

        if (accountRepository.existsAccountByEmail(custom.get("email"))) {
            map.addAttribute("error", "Email đã được sử dụng");

            return "customer/signup";
        } else {
            Account account = new Account(custom.get("name"), custom.get("email"), passwordEncoder.encode(custom.get("pwd")));

            List<Role> roles = new ArrayList<>();
            Role roleCustomer = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(roleCustomer);

            account.setRoles(roles);
            account.setStatus(true);
            accountRepository.save(account);

            Cart cart = new Cart(account.getId(), null);
            cart.setTotalProduct(new BigInteger("0"));
            cart.setTotalPayment(new BigDecimal(0));

            cartRepository.save(cart);
        }

        return "redirect:/auth/signin";
    }
}
