package com.example.springsecurityhashingandsalting14102022.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Controller
public class AccountController {

    private static final String CHILD = "child";
    private static final String MAIN = "main";

    @GetMapping("/account")
    public String showAccountStatus(Model model) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        model.addAttribute("securityContextHolderStrategy",
                SecurityContextHolder.getContextHolderStrategy().getClass().getSimpleName());
        storeSecurityContextDataModelInMainThread(model);
        storeSecurityContextDataModelInChildThread(model);
        return "account";
    }

    private void storeSecurityContextDataModelInMainThread(Model model) {
        storeSecurityContextDataInModel(MAIN, model);
    }

    private void storeSecurityContextDataModelInChildThread(Model model) throws InterruptedException {
        Thread thread = new Thread(() -> storeSecurityContextDataInModel(CHILD, model));
        thread.start();
        thread.join();
    }

    private void storeSecurityContextDataInModel(String prefix, Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        User user = (User) Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .orElse(null);
        model.addAttribute(prefix + "HashCode",
                Integer.toHexString(context.hashCode()));
        model.addAttribute(
                prefix + "Name",
                Optional.ofNullable(authentication)
                        .map(Authentication::getName)
                        .orElse("N/A")
        );
        model.addAttribute(
                prefix + "Username",
                Optional.ofNullable(user)
                        .map(User::getUsername)
                        .orElse("N/A")
        );
        model.addAttribute(
                prefix + "Authorities",
                Optional.ofNullable(authentication)
                        .map(Authentication::getAuthorities)
                        .orElse(null)
        );


    }
}
