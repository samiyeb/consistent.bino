package bino.consistent.grind;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

// import bino.consistent.grind.goals.*;

@SpringBootApplication
public class GrindApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrindApplication.class, args);
	}

	// @Bean
	// CommandLineRunner runner(GoalRepository goalRepository) {
	// 	return args -> {
	// 		Goal goal = new Goal(1, "I will be king of the pirates!");
	// 		goalRepository.create(goal);

	// 	};
	// }

}
