package com.example.electriccomponentsshop.controller;


import com.example.electriccomponentsshop.common.ERole;
import com.example.electriccomponentsshop.common.JwtUtils;
import com.example.electriccomponentsshop.dto.AccountDTO;
import com.example.electriccomponentsshop.dto.MessageResponse;
import com.example.electriccomponentsshop.dto.SigninRequest;
import com.example.electriccomponentsshop.dto.SignupRequest;
import com.example.electriccomponentsshop.entities.Account;
import com.example.electriccomponentsshop.entities.RefreshToken;
import com.example.electriccomponentsshop.entities.Role;
import com.example.electriccomponentsshop.repositories.AccountRepository;
import com.example.electriccomponentsshop.repositories.CategoryRepository;
import com.example.electriccomponentsshop.repositories.RoleRepository;
import com.example.electriccomponentsshop.services.AccountDetailImpl;
import com.example.electriccomponentsshop.services.CategoryService;
import com.example.electriccomponentsshop.services.OrderService;
import com.example.electriccomponentsshop.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.Cookie;

@CrossOrigin
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    OrderService orderService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/change-password")
    public ModelAndView showChangePassword(){
        return new ModelAndView("change-password");
    }
    @PostMapping("/change-password/{id}")
    public ModelAndView changePassword(@Valid @PathVariable("id") int id, HttpServletResponse response , @ModelAttribute("account") AccountDTO login) throws Exception{
        Account accountFromDB = accountRepository.findById(id).orElseThrow();
        accountFromDB.setEmail(login.getEmail());
        accountFromDB.setPassword(login.getPassword());
        accountRepository.save(accountFromDB);
        Cookie cookie = new Cookie("accessToken",null);
        response.addCookie(cookie);
        cookie.setHttpOnly(true);
        return new ModelAndView("logout");
    }
    @GetMapping("/signin")
    public String login(Model model){
        model.addAttribute("signinRequest", new SigninRequest());
        return "customer/html/signin";
    }
    @PostMapping("/signin")
    public String authenticateUser(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("signinRequest") SigninRequest signinRequest, BindingResult bindingResult) {
        System.out.println("true");
        if(bindingResult.hasErrors()){
            System.out.println("gg");
            bindingResult.getFieldErrors().forEach(fieldError -> modelMap.addAttribute(fieldError.getField(),fieldError.getDefaultMessage()));
            return "customer/html/signin";
        }
        String email = signinRequest.getEmail();
        String password = signinRequest.getPassword();
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        if(accountOptional.isEmpty()){
            modelMap.addAttribute("wrongEmail","Không có tài khoản chứa email này");

            return "customer/html/signin";
        } else if (!passwordEncoder.matches(password,accountOptional.get().getPassword())) {
            System.out.println("gcccc");
            modelMap.addAttribute("wrongPassword","Sai mật khẩu");
            return "customer/html/signin";
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getEmail(), signinRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AccountDetailImpl accountDetail = (AccountDetailImpl) authentication.getPrincipal();
        List<String> roles = accountDetail.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        Optional<RefreshToken> refreshToken=refreshTokenService.findByAccount_Email(accountDetail.getEmail());

        if(!refreshToken.isPresent()){
            refreshTokenService.createRefreshToken(accountDetail.getEmail());
        }
       else  if(refreshTokenService.isExpiration(refreshToken.get())){
            refreshTokenService.delete(refreshToken.get());
            refreshTokenService.createRefreshToken(accountDetail.getEmail());
        }

        String jwt= jwtUtils.generateJwtToken(authentication);
        JwtUtils.token = jwt;
        Cookie cookie = new Cookie("accessToken",jwt);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1200);
        cookie.setPath("/");
        response.addCookie(cookie);
        if(roles.contains("ROLE_MANAGER")||roles.contains("ROLE_EMPLOYEE")){
            modelMap.addAttribute("roles", roles.get(0));
            return "redirect:/admin/home";
        } else if (roles.contains("ROLE_MANAGER")) {
            return "redirect:/home";
        }

        return null;
    }

    @PostMapping ("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {



        if (accountRepository.existsAccountByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("This email is registered"));
        } else {
            Account account = new Account(signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
            Set<String> strRoles = signupRequest.getRoles();
            List<Role> roles = new ArrayList<>();
            if (strRoles == null) {
                Role accountRole = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
                roles.add(accountRole);

            } else {
                strRoles.forEach(role -> {
                    switch (role) {
                        case "employee":

                            Role roleEmp = roleRepository.findByRoleName(ERole.ROLE_EMPLOYEE).orElseThrow(() -> new RuntimeException("Role not found"));
                            roles.add(roleEmp);
                            break;
                        case "manager":
                            Role roleManager = roleRepository.findByRoleName(ERole.ROLE_MANAGER).orElseThrow(() -> new RuntimeException("Role not found"));
                            roles.add(roleManager);
                            break;
                        default:
                            Role roleCustomer = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
                            roles.add(roleCustomer);
                            break;


                    }
                });
            }

            account.setRoles(roles);
            accountRepository.save(account);
            return ResponseEntity.ok().body(new MessageResponse("Register successfully"));
        }


    }
}

