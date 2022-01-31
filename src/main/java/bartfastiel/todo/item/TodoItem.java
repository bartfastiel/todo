package bartfastiel.todo.item;

import org.springframework.data.annotation.Id;

record TodoItem(
        @Id String key,
        String title,
        TodoItemStatus status
) {
}
