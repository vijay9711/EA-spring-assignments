package edu.miu.cs544;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoogleController {

    @GetMapping("/welcome")
    public String index() {
        return "Welcome to Spring Boot Google SSO";
    }

    @GetMapping("/secured")
    public String secured(@AuthenticationPrincipal OidcUser oidcUser) {
        return "Welcome " + oidcUser.getFullName() + " to Spring Boot Google SSO";
    }

}
