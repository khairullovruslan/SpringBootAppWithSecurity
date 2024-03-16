package ru.spring.SecurityApp.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.SecurityApp.models.Person;
import ru.spring.SecurityApp.services.RegistrationService;
import ru.spring.SecurityApp.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthContoller {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;
    @Autowired
    public AuthContoller(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }
    @GetMapping("/registration")

    public String registrationPage(@ModelAttribute("person") Person person){
        System.out.println("regisss");

        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult){
        System.out.println("poss");
        personValidator.validate(person, bindingResult);
        System.out.println(1);
        if(bindingResult.hasErrors()){
            return "redirect:/auth/registration";
        }
        registrationService.register(person);
        return "redirect:";
    }
}
