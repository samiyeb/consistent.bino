package bino.consistent.grind.goals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GoalControllerIntTest {

    @LocalServerPort
    int randomServerPort;

    RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + randomServerPort);
    }

    // @Test
    // void shouldFindAllGoals() {
    //     List<Goal> goals = restClient.get()
    //                     .uri("/api/goals")
    //                     .retrieve()
    //                     .body(new ParameterizedTypeReference<>() {});
    //     assertEquals(9, goals.size());
    // }

    @Test
    void shouldFindGoalById() {
        Goal goal = restClient.get()
                .uri("/api/goals/1")
                .retrieve()
                .body(Goal.class);

        assertAll(
                () -> assertEquals(1, goal.id()),
                () -> assertEquals("I will be king of the pirates!", goal.goalName()));
    }

    @Test
    void shouldCreateNewGoal() {
        Goal goal = new Goal(9, "I want to drink water");

        ResponseEntity<Void> newGoal = restClient.post()
                .uri("/api/goals")
                .body(goal)
                .retrieve()
                .toBodilessEntity();

        assertEquals(201, newGoal.getStatusCodeValue());
    }

    @Test
    void shouldUpdateExistingGoal(){
        Goal goal = restClient.get()
                .uri("/api/goals/1")
                .retrieve()
                .body(Goal.class);

        ResponseEntity<Void> updatedGoal = restClient.put()
                .uri("/api/goals/1")
                .body(goal)
                .retrieve()
                .toBodilessEntity();

        assertEquals(204, updatedGoal.getStatusCodeValue());
    }

    @Test
    void shouldDeleteGoal() {
        ResponseEntity<Void> goal = restClient.delete()
                .uri("/api/goals/1")
                .retrieve()
                .toBodilessEntity();

        assertEquals(204, goal.getStatusCodeValue());
    }

}
