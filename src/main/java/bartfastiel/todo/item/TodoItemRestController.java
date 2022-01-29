package bartfastiel.todo.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
class TodoItemRestController {

    private final List<TodoItem> items = new ArrayList<>();

    @PostMapping("/todos")
    ResponseEntity<Void> newTodoItem(
            @RequestBody TodoItem item
    ) {
        items.add(item);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/todos")
    List<TodoItem> fetchTodoItems() {
        return items;
    }
}
