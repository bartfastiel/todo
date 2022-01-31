package bartfastiel.todo.item;

import java.util.UUID;

record NewTodoItem(
        String title,
        TodoItemStatus status
) {
    public TodoItem withNewGeneratedKey() {
        return new TodoItem(
                UUID.randomUUID().toString(),
                title(),
                status()
        );
    }

    public TodoItem withKey(String key) {
        return new TodoItem(
                key,
                title(),
                status()
        );
    }
}
