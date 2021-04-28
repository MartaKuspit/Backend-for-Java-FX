package pl.jfxlux.luxbackend.autor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
class AutorControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void addNewAutor() throws Exception {
        //language=JSON
        String autor = "{\n" +
                "  \"firstName\": \"4444\",\n" +
                "  \"lastName\": \"bbb44\" \n" +
                "}";
        MockHttpServletRequestBuilder request = post("/list-of-autors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(autor);
        mockMvc.perform(request)
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.idAutor", is(1)));


    }


    @Test
    void getAllAutors() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/list-of-autors"))
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());

    }

    @Test
    void deleteAutorById() throws Exception {
        //language=JSON
        String autor = "{\n" +
                "  \"firstName\": \"aaa\",\n" +
                "  \"lastName\": \"bbb\" \n" +
                "}";
        MockHttpServletRequestBuilder request = post("/list-of-autors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(autor);
        mockMvc.perform(request);
        MockHttpServletRequestBuilder request2 = delete("/list-of-autors/1")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request2)
                .andExpect(status().isOk());


    }

    @Test
    void saveEditedAutor() throws Exception {
        String autor = "{\n" +
                "  \"firstName\": \"ccc\",\n" +
                "  \"lastName\": \"bbb\" \n" +
                "}";
        MockHttpServletRequestBuilder request = post("/list-of-autors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(autor);
        mockMvc.perform(request);
        String editedAutor = "{\n" +
                "\"idAutor\":\"2\",\n" +
                "  \"firstName\": \"fffff\",\n" +
                "  \"lastName\": \"bbb\" \n" +
                "}";

        MockHttpServletRequestBuilder request2 = post("/edit-autors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(editedAutor);
        mockMvc.perform(request2).andExpect(status().isOk());




    }

}
