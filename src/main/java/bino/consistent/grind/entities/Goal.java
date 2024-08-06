package bino.consistent.grind.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Double progression = 0.0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        task.setGoal(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setGoal(null);
    }

    public Double getProgression(){
        return this.progression;
    }

    public void setProgression() {
        Double complete = 0.0;
        Double total = (double) tasks.size();

        if (total == 0) {
            this.progression = 0.0;
        }

        for (int i = 0; i < total; i++) {
            if (tasks.get(i).isCompleted()) {
                complete++;
            }
        }

        this.progression = ((complete / total) * 100);
    }

}

