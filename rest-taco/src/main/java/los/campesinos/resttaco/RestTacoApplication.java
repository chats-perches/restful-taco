package los.campesinos.resttaco;

import los.campesinos.resttaco.data.UserRepository;
//import los.campesinos.resttaco.domain.Role;
import los.campesinos.resttaco.domain.User;
import los.campesinos.resttaco.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class RestTacoApplication {

	@Bean
	public CommandLineRunner setupDefaultUser(UserService service) {
		return args -> {
			service.save(new User(
					"user", //username
					"user", //password
					"USER,ACTUATOR",//roles
					true//Active
			));
		};
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}



	public static void main(String[] args) {
		SpringApplication.run(RestTacoApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepo) {
		// user repo for ease of testing with a built-in user
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {

				userRepo.save(new User("taco", "password",
						"Craig Walls", "123 North Street", "Cross Roads", "TX",
						"76227", "123-123-1234", "USER,ACTUATOR", true));
			}
		};
	}
}
