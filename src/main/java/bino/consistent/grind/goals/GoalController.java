package bino.consistent.grind.goals;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Goal goal = goalRepository.retrieveGoal(id).orElseThrow(() -> new GoalNotFoundException());
        return assembler.toModel(goal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Goal goal) {

        EntityModel<Goal> entityModel = assembler.toModel(goalRepository.save(goal));

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Goal newGoal, @PathVariable Integer id) {
    
        Goal updated = goalRepository.retrieveGoal(id) //
            .map(goal -> {
                goal.setGoalTitle(newGoal.getGoalTitle());
                goal.setGoalDescription(newGoal.getGoalDescription());
                goalRepository.update(goal, id);
                return goal;
            }) //
            .orElseGet(() -> {
                return newGoal;
            });
        
        EntityModel<Goal> entityModel = assembler.toModel(updated);
        
        return ResponseEntity //
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(entityModel);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        goalRepository.delete(id);
        return ResponseEntity.noContent().build();
    }

}
