package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/mvc")
public class SayHiControl {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    //match automatically
    @RequestMapping("/person")
    public String toPerson(String name,double age){
        System.out.println(name+" "+age);
        return "hello";
    }

    //boxing automatically
    @RequestMapping("/person1")
    public String toPerson(Person p){
        System.out.println(p.getName()+" "+p.getAge());
        return "hello";
    }

    //the parameter was converted in initBinder
        @RequestMapping("/date")
    public String date(Date date){
        System.out.println(date);
        return "hello";
    }

    //At the time of initialization,convert the type "String" to type "date"
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                true));
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String saveUser(@RequestBody @ModelAttribute Person p){
        System.out.println(p);
        userService.save(p);
        return "hello";
    }
}
