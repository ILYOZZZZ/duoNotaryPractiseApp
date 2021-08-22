package uz.pdp.appduonotarypraktikaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.repository.UserRepository;
import uz.pdp.appduonotarypraktikaserver.service.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {

    @Autowired
    MailService mailService;

    @Autowired
    UserRepository userRepository;




}
