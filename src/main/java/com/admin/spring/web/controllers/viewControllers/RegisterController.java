package com.admin.spring.web.controllers.viewControllers;

import com.admin.spring.domain.User;
import com.admin.spring.service.UserService;
import com.admin.spring.web.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("")
public class RegisterController {
    private UserService userService;


    public RegisterController(UserService userService) {
        this.userService = userService;
       
    }

    @PostMapping(value = "/submit-registration")
    public ModelAndView saveUser(ModelAndView modelAndView, @ModelAttribute("userDto") @Valid final UserDto userDto,
                                 BindingResult bindingResult, HttpServletRequest request, Errors errors){

        User emailExists = userService.findByEmail(userDto.getEmail());
        User userNameExists = userService.findByUsername(userDto.getUsername());

        System.out.println(emailExists);

        if (emailExists != null) {
            modelAndView.setViewName("website/register");
            bindingResult.rejectValue("email", "emailAlreadyExists");
        }

        if (userNameExists!= null) {
            modelAndView.setViewName("website/register");
            bindingResult.rejectValue("username", "usernameAlreadyExists");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("website/register");
        }
        else { 

            User user = userService.createNewAccount(userDto);
            

            user.setEnabled(true);
            userService.save(user);

            modelAndView.setViewName("website/registered");
        }

        return modelAndView;
    }
}
