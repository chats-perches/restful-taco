package los.campesinos.resttaco.web;

import los.campesinos.resttaco.data.UserRepository;
import los.campesinos.resttaco.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * THIS CONTROLLER IS FOR TESTING REGISTRATION
 *
 * */



@RestController
@RequestMapping(path="/user", produces="application/json")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    UserRepository userRepo;

    @GetMapping("/{id}")
    public User one(@PathVariable("id") Long userId){
        Optional<User> user = userRepo.findById(userId);
        return user.isPresent() ?
                user.get() : new User();
    }
}
