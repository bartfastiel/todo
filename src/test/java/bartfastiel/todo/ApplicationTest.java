package bartfastiel.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Objects.requireNonNull;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.matchesRegex;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    private static final String UUID_PATTERN = "\\b[0-9a-f]{8}\\b-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-\\b[0-9a-f]{12}\\b";

    @Autowired
    private MockMvc mvc;

    @DirtiesContext
    @Test
    public void expectNewLocation_whenSendingTodoItem() throws Exception {
        mvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "some title",
                                    "status": "open"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, matchesRegex("http://localhost/todos/" + UUID_PATTERN)));
    }

    @Test
    public void expectStatusBadRequest_whenProvidingKeyOnInsert() throws Exception {
        mvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "key": "MY_OWN_KEY",
                                    "title": "some title",
                                    "status": "open"
                                }
                                """))
                .andExpect(status().isBadRequest());
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

    @DirtiesContext
    @Test
    public void expectSingleItemResponse_whenStoringItemThenQuerying() throws Exception {
        mvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "some title",
                            "status": "open"
                        }
                        """));

        mvc.perform(get("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().json("""
                        [
                            {
                                    "title": "some title",
                                    "status": "open"
                            }
                        ]
                        """));
    }

    @DirtiesContext
    @Test
    public void expectUpdatedResponse_whenInsertingThenUpdatingItem() throws Exception {
        var location = mvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "some title",
                                    "status": "open"
                                }
                                """))
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        requireNonNull(location);
        mvc.perform(put(location)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "title": "updated title",
                                    "status": "done"
                                }
                                """))
                .andExpect(status().isOk());

        mvc.perform(get("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(content().json("""
                        [
                            {
                                    "title": "updated title",
                                    "status": "done"
                            }
                        ]
                        """));
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
