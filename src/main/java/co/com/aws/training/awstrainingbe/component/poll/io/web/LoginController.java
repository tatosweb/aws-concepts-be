package co.com.aws.training.awstrainingbe.component.poll.io.web;

import co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto.BasicAccessDto;
import co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto.LoginDto;
import co.com.aws.training.awstrainingbe.component.poll.io.web.v1.dto.LoginResponse;
import co.com.aws.training.awstrainingbe.component.poll.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/v1/security", produces = "application/json;charset=UTF-8")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Value("${demo:default}")
    private String demo;

    @Autowired
    private SecurityService securityService;

    @PostMapping(value = "token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> validateToken() {
        return new ResponseEntity<LoginResponse>(
                new LoginResponse("OK", SecurityContextHolder.getContext().getAuthentication().getPrincipal()), HttpStatus.OK);
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginDto login) {
        logger.info("demo value: " + demo);
        return new ResponseEntity<LoginResponse>(
                securityService.getToken(login.getUsername(), login.getPassword()), HttpStatus.OK);
    }

    @PostMapping(value = "signout", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> signOut(
            @RequestBody BasicAccessDto token) {
        return new ResponseEntity<LoginResponse>(
                securityService.signOut(token.getToken()), HttpStatus.OK);
    }

    @PostMapping(value = "refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> refreshToken(
            @RequestBody BasicAccessDto token) {
        return new ResponseEntity<LoginResponse>(
                securityService.refreshToken(token.getToken()), HttpStatus.OK);
    }


}
