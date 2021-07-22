package los.campesinos.resttaco.web;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class HomeController {


    @GetMapping("/")
    public OAuth2AccessToken accessToken(@RegisteredOAuth2AuthorizedClient(registrationId = "github")
                                                     OAuth2AuthorizedClient authorizedClient) {

        return authorizedClient.getAccessToken();
    }

    @GetMapping(value = "/private")
    public String privateArea(){
        return "Private area";
    }

}