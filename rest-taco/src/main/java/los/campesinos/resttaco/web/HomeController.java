package los.campesinos.resttaco.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* THIS CONTROLLER CAN BE SAFELY DELETED
 * it's purpose was to testing the access_token on postman
* */

@RestController
public class HomeController {

    @GetMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException {

        response.sendRedirect("/login/oauth2/code/github");

    }
 
    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private area";
    }

}