package bartfastiel.todo.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TodoItemRestController {

    @PostMapping("/todos")
    ResponseEntity<Void> newTodoItem(
            @RequestBody TodoItem item
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
