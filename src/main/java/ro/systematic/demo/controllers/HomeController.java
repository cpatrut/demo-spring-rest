package ro.systematic.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by neop on 14.03.2017.
 */

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "Welcome home!";
    }
}
