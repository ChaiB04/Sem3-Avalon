package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.business.exception.UserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginManager loginManager;

    @Test
    void login_withUnregisteredUser_ShouldReturnBadRequest() throws Exception {

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{ \"email\": \"\", \"password\": \"\" } "))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verifyNoInteractions(loginManager);
    }

    @Test
    void login_shouldReturn400InvalidCredentials() throws Exception{
        String email = "neuvi@gmail.com";
        String pass = "aaaa";

        when(loginManager.userLogin(email, pass)).thenThrow(new UserException("Invalid Credentials"));

        mockMvc.perform(post("/login")
                .contentType(APPLICATION_JSON_VALUE)
                .content("{ \"email\": \"neuvi@gmail.com\", \"password\": \"aaaa\"} "))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                    {
                        "type": "/validation-error",
                        "title": "Bad Request",
                        "status": 400,
                        "detail": "Invalid request",
                        "instance": "/login",
                        "properties": {
                            "errors": [
                                {
                                    "field": null,
                                    "error": "Invalid Credentials"
                                }
                            ]
                        }
                    }"""));




        verify(loginManager).userLogin(email, pass);
    }

    @Test
    void login_withRegisteredUser_ShouldReturn200AndAccesToken() throws Exception {
        when(loginManager.userLogin("neuvi@gmail.com", "neuvi"))
                .thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOZXV2aTJAZ21haWwuY29tIiwiaWF0" +
                        "IjoxNzAxNzcxMzY0LCJleHAiOjE3MDI2MzUzNjQsInJvbGVzIjoiQURNSU5JU1RSQ" +
                        "VRPUiIsInVzZXJJZCI6Nn0.4-YI0Zp5aeSiS2abrp2EMVD0A1B2udt1nArGzJhhRjE");

        mockMvc.perform(post("/login")
                .contentType(APPLICATION_JSON_VALUE)
                .content("{ \"email\": \"neuvi@gmail.com\", \"password\": \"neuvi\"} "))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                       {
                       "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJOZXV2aTJAZ21haWwuY29tIiwiaWF0IjoxNzAxNzcxMzY0LCJleHAiOjE3MDI2MzUzNjQsInJvbGVzIjoiQURNSU5JU1RSQVRPUiIsInVzZXJJZCI6Nn0.4-YI0Zp5aeSiS2abrp2EMVD0A1B2udt1nArGzJhhRjE"
                        } """));

        verify(loginManager).userLogin("neuvi@gmail.com", "neuvi");
    }

}
