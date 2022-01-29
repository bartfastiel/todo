package bartfastiel.todo.item;

import org.springframework.data.mongodb.repository.MongoRepository;

interface TodoItemRepository extends MongoRepository<TodoItem, String> {
}
