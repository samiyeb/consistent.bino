package bino.consistent.grind.goals;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/goals")
public class GoalController {
    private final GoalRepository goalRepository;
    private final GoalModelAssembler assembler;

    GoalController(GoalRepository goalRepository, GoalModelAssembler assembler) {
        this.goalRepository = goalRepository;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Goal>> retrieveAllGoals(){
        List<EntityModel<Goal>> goals = goalRepository.retrieveAllGoals().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(goals, linkTo(methodOn(GoalController.class).retrieveAllGoals()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Goal> retrieveGoal(@PathVariable Integer id){
        Goal goal = goalRepository.retrieveGoal(id);
        return assembler.toModel(goal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody Goal goal){
        goalRepository.create(goal);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Goal goal, @PathVariable Integer id){
        goalRepository.update(goal, id);
        
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        goalRepository.delete(id);
    }

}
