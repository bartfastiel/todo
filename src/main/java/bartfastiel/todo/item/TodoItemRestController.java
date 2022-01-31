package bartfastiel.todo.item;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/todos")
class TodoItemRestController {

    private final TodoItemRepository repo;

    TodoItemRestController(
            TodoItemRepository repo
    ) {
        this.repo = repo;
    }

    @PostMapping
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

    @PutMapping(path = "/{key}")
    ResponseEntity<?> updateTodoItem(
            @PathVariable String key,
            @RequestBody NewTodoItem item
    ) {
        var existed = repo.existsById(key);
        repo.save(item.withKey(key));
        if (existed) {
            return ResponseEntity
                    .ok()
                    .build();
        } else {
            return ResponseEntity
                    .created(ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .build()
                            .toUri())
                    .build();
        }
    }

    @GetMapping
    List<TodoItem> fetchTodoItems() {
        return repo.findAll();
    }
}
