package bino.consistent.grind.goals;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GoalNotFoundException extends RuntimeException {
    public GoalNotFoundException() {
        super("Goal Not Found");
    }

}
