package los.campesinos.resttaco.web;

import los.campesinos.resttaco.data.UserRepository;
import los.campesinos.resttaco.domain.AuthenticationRequest;
import los.campesinos.resttaco.domain.AuthenticationResponse;
import los.campesinos.resttaco.services.CustomUserDetailsService;
import los.campesinos.resttaco.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/authenticate")
@CrossOrigin(origins="*")
public class LoginController {

    private UserRepository userRepo;
    private PasswordEncoder pw = new BCryptPasswordEncoder();
    private AuthenticationManager authMan;
    private CustomUserDetailsService userDetailsService;
    private JwtUtil jwtTokenUtil;

    @Autowired
    public LoginController(UserRepository userRepo, PasswordEncoder pw, AuthenticationManager aM,
                           CustomUserDetailsService cuds, JwtUtil jwt) {
        this.userRepo = userRepo;
        this.pw = pw;
        this.authMan = aM;
        this.userDetailsService = cuds;
        this.jwtTokenUtil = jwt;
    }

    @PostMapping(consumes="application/json")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest login) throws Exception{
        try{
            authMan.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
            );
        }
        catch (BadCredentialsException incredible) {
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeep!!!!!!!!!!!!!");
            throw new Exception("Incorrect username or password...\nYou're incredible!", incredible);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(login.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


}
