package bino.consistent.grind.goals;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GoalController.class)
public class GoalControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    GoalRepository repository;

    private final List<Goal> goals = new ArrayList<>();

    @BeforeEach
    void setUp() {
        goals.add(new Goal(1, "I want to be a Navy Admiral"));
    }

    @Test
    void testCreate() throws Exception{
        var goal = new Goal(null, "I want to be a father");
        mvc.perform(post("/api/goals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goal))
                )
                .andExpect(status().isCreated());

    }

    @Test
    void testRetrieveAllGoals() throws Exception{
        when(repository.retrieveAllGoals()).thenReturn(goals);
        mvc.perform(get("/api/goals")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(goals.size())));

    }

    @Test
    void testRetrieveGoal() throws Exception {
        Goal goal = goals.get(0);
        when(repository.retrieveGoal(ArgumentMatchers.anyInt())).thenReturn(Optional.of(goal));
        mvc.perform(get("/api/goals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(goal.id())))
                .andExpect(jsonPath("$.goalName", is(goal.goalName())));
      

    }

    @Test
    void testUpdate() throws Exception {
        var goal = new Goal(null,"Reach level 5");
        mvc.perform(put("/api/goals/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goal))
                )
                .andExpect(status().isNoContent());

    }

    @Test
    public void testDelete() throws Exception {
        mvc.perform(delete("/api/goals/1"))
                .andExpect(status().isNoContent());
    }
}
