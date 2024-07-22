package bino.consistent.grind.goals;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.Optional;


import java.util.List;

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

    public Optional<Goal> retrieveGoal(Integer id){
        return jdbcClient.sql("SELECT id, goalTitle, goalDescription FROM Goal WHERE id = :id" )
            .param("id", id)
            .query(Goal.class)
            .optional();
    }

    public void create(Goal goal){
        var updated = jdbcClient.sql("INSERT INTO Goal(id, goalTitle, goalDescription) values(?,?,?)")
                .params(List.of(goal.getId(), goal.getGoalTitle(), goal.getGoalDescription()))
                .update();

        Assert.state(updated == 1, "Failed to create goal " + goal.getGoalDescription());
    }

    public void update(Goal goal, Integer id){
        var updated = jdbcClient.sql("update goal set goalTitle = ?, goalDescription = ? where id = ?")
                        .params(List.of(goal.getGoalTitle(), goal.getGoalDescription(), id))
                        .update();
        
        Assert.state(updated == 1, "Failed to update goal " + id);
        

    }

    public void delete(Integer id){
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

    public Goal save(Goal goal) {
        this.create(goal);
        return goal;
    }


    
    

    
    

    


}
