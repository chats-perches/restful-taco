package los.campesinos.resttaco.web;

import los.campesinos.resttaco.data.UserRepository;
import los.campesinos.resttaco.domain.RegistrationForm;
import los.campesinos.resttaco.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/register")
@CrossOrigin(origins="*")
public class RegistrationController {

    UserRepository userRepo;
    PasswordEncoder pw = new BCryptPasswordEncoder();

    @Autowired
    public RegistrationController(UserRepository userRepo, PasswordEncoder pw) {
        this.userRepo = userRepo;
        this.pw = pw;
    }



    @PostMapping(consumes="application/json")
    public User register(@RequestBody RegistrationForm dto){
        User user = dto.toUser(pw);
       return userRepo.save(user);
    }
}
