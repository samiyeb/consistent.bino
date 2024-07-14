package bino.consistent.grind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import bino.consistent.grind.user.*;
import java.util.List;


@SpringBootApplication
public class GrindApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrindApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRestClient client) {
		return args -> {
			List<User> users = client.findAll();
			User user = client.findById(1);

			System.out.println(users);
			System.out.println(user);


		};
	}


}
