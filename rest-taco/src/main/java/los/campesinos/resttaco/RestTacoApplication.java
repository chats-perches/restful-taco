package los.campesinos.resttaco;

import com.nimbusds.oauth2.sdk.OAuth2Error;
import los.campesinos.resttaco.data.UserRepository;
//import los.campesinos.resttaco.domain.Role;
import los.campesinos.resttaco.model.User;
import los.campesinos.resttaco.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

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
//	@Bean
//	public WebClient rest(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
//		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 =
//				new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authz);
//		return WebClient.builder()
//				.filter(oauth2).build();
//	}
//
//	@Bean
//	public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz) {
//		WebClient rest = rest( clients,  authz);
//		DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
//		return request -> {
//			OAuth2User user = delegate.loadUser(request);
//			if (!"github".equals(request.getClientRegistration().getRegistrationId())) {
//				return user;
//			}
//
//			OAuth2AuthorizedClient client = new OAuth2AuthorizedClient
//					(request.getClientRegistration(), user.getName(), request.getAccessToken());
//			String url = user.getAttribute("organizations_url");
//			List<Map<String, Object>> orgs = rest
//					.get().uri(url)
//					.attributes(oauth2AuthorizedClient(client))
//					.retrieve()
//					.bodyToMono(List.class)
//					.block();
//
//			if (orgs.stream().anyMatch(org -> "spring-projects".equals(org.get("login")))) {
//				return user;
//			}
//
//			throw new OAuth2AuthenticationException("INVALID TOKEN");
//		};
//	}

}
