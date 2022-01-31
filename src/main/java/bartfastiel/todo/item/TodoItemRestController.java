package bartfastiel.todo.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
class TodoItemRestController {

    private final TodoItemRepository repo;

    TodoItemRestController(
            TodoItemRepository repo
    ) {
        this.repo = repo;
    }

    @PostMapping("/todos")
    ResponseEntity<?> newTodoItem(
            @RequestBody NewTodoItem item
    ) {
        var savedItem = repo.save(item.withNewGeneratedKey());
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedItem.key())
                        .toUri())
                .build();
    }

    @GetMapping("/todos")
    List<TodoItem> fetchTodoItems() {
        return repo.findAll();
    }
}
