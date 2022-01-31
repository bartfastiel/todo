package bartfastiel.todo.item;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/todos")
class TodoItemRestController {

    public static final String PATH_TO_ITEM_KEY = "/{key}";
    private final TodoItemRepository repo;

    TodoItemRestController(
            TodoItemRepository repo
    ) {
        this.repo = repo;
    }

    @PostMapping
    @Operation(summary = "Add a todo item (generates new key)", tags = {"todos"})
    @ApiResponse(
            responseCode = "201",
            description = "The resource has successfully been stored.",
            headers = @Header(name = HttpHeaders.LOCATION, description = "URL of the newly created resource", schema = @Schema(type = "string")),
            content = @Content(schema = @Schema(hidden = true))
    )
    ResponseEntity<Void> newTodoItem(
            @RequestBody NewTodoItem item
    ) {
        var savedItem = repo.save(item.withNewGeneratedKey());
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path(PATH_TO_ITEM_KEY)
                        .buildAndExpand(savedItem.key())
                        .toUri())
                .build();
    }

    @PutMapping(path = PATH_TO_ITEM_KEY)
    @Operation(summary = "Add or update a todo item", tags = {"todos"})
    @ApiResponse(
            responseCode = "201",
            description = "The resource did not exist, but now has successfully been stored.",
            headers = @Header(name = HttpHeaders.LOCATION, description = "URL of the newly created resource", schema = @Schema(type = "string")),
            content = @Content(schema = @Schema(hidden = true))
    )
    @ApiResponse(
            responseCode = "200",
            description = "The resource already existed, and has successfully been updated.",
            content = @Content(schema = @Schema(hidden = true))
    )
    ResponseEntity<Void> updateTodoItem(
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
    @Operation(summary = "Query todo items", tags = {"todos"})
    List<TodoItem> fetchTodoItems() {
        return repo.findAll();
    }
}
