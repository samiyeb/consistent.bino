package bino.consistent.grind.goals;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class GoalModelAssembler implements RepresentationModelAssembler<Goal, EntityModel<Goal>> {
    
    @Override
    public EntityModel<Goal> toModel(Goal goal) {

        return EntityModel.of(goal,
            linkTo(methodOn(GoalController.class).retrieveGoal(goal.getId())).withSelfRel(),
            linkTo(methodOn(GoalController.class).retrieveAllGoals()).withRel("goals"));
    }

}
