package bartfastiel.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TodoItemRestController {

    @PutMapping("/todos")
    ResponseEntity<Void> newTodoItem() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
