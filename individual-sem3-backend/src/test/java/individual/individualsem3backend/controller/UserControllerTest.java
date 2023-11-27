package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.UserManager;
import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.domain.User;
import individual.individualsem3backend.domain.enumeration.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserManager userManager;

    @Test
    void getUser_shouldReturn200WithUser_whenUserFound() throws Exception{
         User user = User.builder()
                .id(1)
                .firstname("Neuvillette")
                .lastname("Dragonidk")
                .email("Neuvi@gmail.com")
                .password("idontlikefurina")
                .country("Fontaine")
                .city("Court of Fontaine")
                .housenumber(69)
                .street("Courthouse")
                .zipcode("4829HF")
                .phonenumber("9039032").role(Role.CUSTOMER)
                .build();

        when(userManager.getUser(1)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {"id": 1,
                     "email": "Neuvi@gmail.com",
                     "firstname": "Neuvillette",
                     "lastname": "Dragonidk",
                     "street": "Courthouse",
                     "housenumber": 69,
                     "zipcode": "4829HF",
                     "city": "Court of Fontaine",
                     "country": "Fontaine",
                     "phonenumber": "9039032"
                 }   
                                    """));

        verify(userManager).getUser(1);
    }

    @Test
    void getUser_shouldReturn400WithoutUser_whenUserNotFound() throws Exception{
        when(userManager.getUser(1)).thenThrow(new UserException("User not found for ID: 1"));
        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                {
                    "type": "/validation-error",
                    "title": "Bad Request",
                    "status": 400,
                    "detail": "Invalid request",
                    "instance": "/users/1",
                    "properties": {
                        "errors": [
                            {
                                "field": null,
                                "error": "User not found for ID: 1"
                            }
                        ]
                    }
                }"""));

        verify(userManager).getUser(1);
    }
}
