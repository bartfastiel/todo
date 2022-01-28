package bartfastiel.todo.item;

import com.fasterxml.jackson.annotation.JsonProperty;

enum TodoItemStatus {
    @JsonProperty("open") OPEN,
    @JsonProperty("done") DONE,
}
