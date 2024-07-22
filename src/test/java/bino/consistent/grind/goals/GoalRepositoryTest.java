package bino.consistent.grind.goals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(GoalRepository.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class GoalRepositoryTest {

    @Autowired
    GoalRepository repository;

    @BeforeEach
    void setUp(){
        repository.create(new Goal(1, "first goal", "goal 1"));
        repository.create(new Goal(2, "second goal", "goal 2"));
        repository.create(new Goal(3, "third goal", "goal 3"));
    }

    @Test
    void shouldFindAllGoals() {
        List<Goal> goals = repository.retrieveAllGoals();
        assertEquals(3, goals.size());
    }

    @Test
    void shouldFindGoalWithValidId() {
        var goal = repository.retrieveGoal(1);
        var goal3 = repository.retrieveGoal(3);
        assertEquals("first goal:goal 1", goal.get().getGoalName());
        assertEquals("third goal:goal 3", goal3.get().getGoalName());
    }

    @Test
    void shouldCreateNewGoal() {
        repository.create(new Goal(4, "fourth", "goal 4"));

        List<Goal> goals = repository.retrieveAllGoals();
        assertEquals(4, goals.size());
    }

    @Test
    void shouldUpdateGoal(){
        Goal goal1 = new Goal(2, "second goal", "goal 2 but better");

        repository.update(goal1, 2);

        var goal2 = repository.retrieveGoal(2);
        assertEquals("second goal:goal 2 but better", goal2.get().getGoalName());
    }

    @Test
    void shouldDeleteGoal() {
        repository.delete(1);
        List<Goal> goals = repository.retrieveAllGoals();
        assertEquals(2, goals.size());
    }


    
}
