package los.campesinos.resttaco.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* THIS CONTROLLER CAN BE SAFELY DELETED
 * it's purpose was to testing the access_token on postman
* */

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public String index(){
        return "Hello world";
    }
 
    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private area";
    }

}