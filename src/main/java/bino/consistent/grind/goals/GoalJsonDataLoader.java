package bino.consistent.grind.goals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class GoalJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(GoalJsonDataLoader.class);
    private final ObjectMapper objectMapper;
    private final GoalRepository goalRepository;

    public GoalJsonDataLoader(ObjectMapper objectMapper, @Qualifier("goalRepository") GoalRepository goalRepository) {
        this.objectMapper = objectMapper;
        this.goalRepository = goalRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(goalRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/goals.json")) {
                Goals allGoals = objectMapper.readValue(inputStream, Goals.class);
                log.info("Reading {} goals from JSON data and saving to the database.", allGoals.goals().size());
                goalRepository.saveAll(allGoals.goals());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Goals from JSON data because the collection contains data.");
        }
    }

}
