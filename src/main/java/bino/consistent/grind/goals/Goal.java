package bino.consistent.grind.goals;

import java.util.Objects;

public class Goal {
    private Integer id;
    private String goalTitle;
    private String goalDescription;

    Goal(Integer id, String goalTitle, String goalDescription) {
        this.id = id;
        this.goalTitle = goalTitle;
        this.goalDescription = goalDescription;
    }

    public Integer getId(){
        return this.id;
    }

    public String getGoalName() {
        return this.goalTitle + ":" + this.goalDescription;
    }

    public String getGoalTitle() {
        return this.goalTitle;
    }

    public String getGoalDescription() {
        return this.goalDescription;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setGoalName(String goalName) {
        String[] parts = goalName.split(":");
        this.goalTitle = parts[0];
        this.goalDescription = parts[1];
    }

    public void setGoalTitle(String goalTitle){
        this.goalTitle = goalTitle;
    }

    public void setGoalDescription(String goalDescription){
        this.goalDescription = goalDescription;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Goal))
            return false;
        Goal goal = (Goal) o;
            return Objects.equals(this.id, goal.id) && Objects.equals(this.goalTitle, goal.goalTitle)
                        && Objects.equals(this.goalDescription, goal.goalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.goalTitle, this.goalDescription);
    }

    @Override
    public String toString() {
        return "Goal{" + "id=" + this.id + ", goal name='" + this.getGoalName() + '}';
    }

}
