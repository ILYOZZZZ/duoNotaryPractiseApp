package uz.pdp.appduonotarypraktikaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.JwtResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqSignIn;
import uz.pdp.appduonotarypraktikaserver.payload.ReqUser;
import uz.pdp.appduonotarypraktikaserver.security.AuthService;
import uz.pdp.appduonotarypraktikaserver.security.JwtTokenProvider;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticate;

    @Autowired
    AuthService authService;




    @PostMapping("/login")
    public HttpEntity<?> login(@Valid @RequestBody ReqSignIn reqSignIn){
        return ResponseEntity.ok(getApiToken(reqSignIn.getPhoneNumberOrEmail(),reqSignIn.getPassword()));
    }

    public HttpEntity<?> getApiToken(String phoneNumberOrEmail, String password){
        Authentication authentication = authenticate.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumberOrEmail, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PreAuthorize("hasAuthority('SAVE_ADMIN')")
    @PostMapping("/saveAdmin")
    public HttpEntity<?> saveAdmin(@RequestBody ReqUser reqUser){
        return authService.saveUser(reqUser,true);
    }

    @PostMapping("/saveAgent")
    public HttpEntity<?> saveAgent(@RequestBody ReqUser reqUser){
        return authService.saveUser(reqUser,false);
    }

    @PostMapping("/saveClient")
    public HttpEntity<?> saveClient(@RequestBody ReqUser reqUser){
        return authService.saveUser(reqUser,null);
    }

    @GetMapping("/verify")
    public HttpEntity<?> verifyMail(@RequestParam boolean check ,
                                    @RequestParam UUID userId,
                                    @RequestParam String verificationCode,
                                    @RequestParam boolean changing) {
        ApiResponse apiResponse=authService.verifyEmail(check,userId,verificationCode,changing);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);

    }
}
