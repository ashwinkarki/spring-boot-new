package com.ashwin.spring_app_vemployee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    @RequestMapping(value="/",method= RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        model.put("name","in28minutes");
        return "welcome";
    }

//    @RequestMapping(value="/login",method= RequestMethod.POST)
//    public String showWelcomePage(ModelMap model,@RequestParam String name,@RequestParam String password){
//       boolean isValidUser= loginService.validateUser(name,password);
//        if(!isValidUser){
//            model.put("errorMessage","invalid credintals");
//            return "login";
//        }
//        model.put("name",name);
//        return "welcome";
//    }

}
