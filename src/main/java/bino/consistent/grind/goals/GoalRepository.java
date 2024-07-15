package bino.consistent.grind.goals;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class GoalRepository {
    private final JdbcClient jdbcClient;

    public GoalRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Goal> retrieveAllGoals(){
        return jdbcClient.sql("select * from goal")
                .query(Goal.class)
                .list();
    }

    public Optional<Goal> retrieveGoal(long id){
        return jdbcClient.sql("SELECT id, goalName FROM Goal WHERE id = :id" )
            .param("id", id)
            .query(Goal.class)
            .optional();

    }

    public void create(Goal goal){
        var updated = jdbcClient.sql("INSERT INTO Goal(id, goalName) values(?,?)")
                .params(List.of(goal.id(), goal.goalName()))
                .update();

        Assert.state(updated == 1, "Failed to create goal " + goal.goalName());
    }

    public void update(Goal goal, long id){
        var updated = jdbcClient.sql("update goal set goalName = ? where id = ?")
                .params(List.of(goal.goalName(), id))
                .update();

        Assert.state(updated == 1, "Failed to update goal " + goal.goalName());

    }

    public void delete(long id){
        var updated = jdbcClient.sql("delete from goal where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete goal " + id);


    }

    public int count() {
        return jdbcClient.sql("select * from goal").query().listOfRows().size();
    }

    public void saveAll(List<Goal> goals) {
        goals.stream().forEach(this::create);
    }

    


}
