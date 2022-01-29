package bartfastiel.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void expectStatusCreated_whenSendingTodoItem() throws Exception {
        mvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "some title",
                                    "status": "open"
                                }
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    public void expectStatusBadRequest_whenSendingInvalidStatus() throws Exception {
        mvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "status": "foo"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void expectRedirectToSwaggerUi_whenOpeningRootUrl() throws Exception {
        mvc.perform(get("/"))
                .andExpect(redirectedUrl("/swagger-ui/index.html"));

    }

    @Test
    public void expectSwaggerUi_whenDirectlyNavigatingToIt() throws Exception {
        mvc.perform(get("/swagger-ui/index.html"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Swagger UI")));
    }
}
