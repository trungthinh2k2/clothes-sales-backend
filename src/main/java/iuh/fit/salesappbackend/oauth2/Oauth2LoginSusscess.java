package iuh.fit.salesappbackend.oauth2;


import com.fasterxml.jackson.databind.ObjectMapper;
import iuh.fit.salesappbackend.dtos.responses.LoginResponse;
import iuh.fit.salesappbackend.models.Token;
import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.models.UserDetail;
import iuh.fit.salesappbackend.models.enums.Role;
import iuh.fit.salesappbackend.repositories.UserRepository;
import iuh.fit.salesappbackend.service.interfaces.JwtService;
import iuh.fit.salesappbackend.service.interfaces.TokenService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@Component
@Slf4j
@RequiredArgsConstructor
public class Oauth2LoginSusscess implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Value("${front-end.url}")
    private String frontEndUrl;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("Login success");
        log.info(authentication.getPrincipal().toString());

        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String registrationId = token.getAuthorizedClientRegistrationId();

        User user = new User();
        user.setPhoneNumber("");
        user.setVerify(true);
        user.setPassword("");
        user.setRole(Role.ROLE_USER);
        switch (registrationId) {
            case "google":
                GoogleAccount googleAccount = new GoogleAccount(
                    principal.getName(),
                    Objects.requireNonNull(principal.getAttribute("email")).toString(),
                    Objects.requireNonNull(principal.getAttribute("name")).toString(),
                    Objects.requireNonNull(principal.getAttribute("picture")).toString());
                user.setEmail(googleAccount.getEmail());
                user.setName(googleAccount.getName());
                user.setAvatarUrl(googleAccount.getPicUrl());
                user.setGoogleAccountId(googleAccount.getAccountId());
                break;
            case "facebook":
                FacebookAccount facebookAccount = new FacebookAccount(
                    principal.getName(),
                    Objects.requireNonNull(principal.getAttribute("email")).toString(),
                    Objects.requireNonNull(principal.getAttribute("name")).toString());
                user.setEmail(facebookAccount.getEmail());
                user.setName(facebookAccount.getName());
                user.setFacebookAccountId(facebookAccount.getAccountId());
                break;
            default:
                break;
        }
        User userDB = userRepository.findByEmail(user.getEmail()).orElse(user);
        if (userDB.getGoogleAccountId() == null) {
            userDB.setGoogleAccountId(user.getGoogleAccountId());
        }
        if (userDB.getFacebookAccountId() == null) {
            userDB.setFacebookAccountId(user.getFacebookAccountId());
        }
        userRepository.save(userDB);
        LoginResponse loginResponse = loginHandler(userDB);

        // Set cookies
        Cookie accessTokenCookie = new Cookie("accessToken", loginResponse.getAccessToken());
//        accessTokenCookie.setSecure(true); for https
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(30);

        Cookie refreshTokenCookie = new Cookie("refreshToken", loginResponse.getRefreshToken());
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(30);

        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        String url = String.format("%s/auth/login-success?accessToken=%s&refreshToken=%s&email=%s", frontEndUrl,
                loginResponse.getAccessToken(),
                loginResponse.getRefreshToken(),
                userDB.getEmail()
                );
        response.sendRedirect(url);
    }

    private LoginResponse loginHandler(User user) {
        UserDetail userDetail = new UserDetail(user);
        Token token = new Token();
        token.setUser(user);
        token.setAccessToken(jwtService.generateToken(userDetail));
        token.setRefreshToken(jwtService.generateRefreshToken(new HashMap<>(), userDetail));
        token.setExpiredDate(LocalDateTime.now());
        tokenService.saveToken(user, token);
        return LoginResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
