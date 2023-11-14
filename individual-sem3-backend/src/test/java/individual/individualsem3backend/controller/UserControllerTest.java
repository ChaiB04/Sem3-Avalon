package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.UserManager;
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


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
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
                .phonenumber("9039032")
                 .role(Role.CUSTOMER)
                .build();

        when(userManager.getUser(1)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                    {"id": 1,
                     "email": "Neuvi@gmail.com",
                     "password": "idontlikefurina",
                     "firstname": "Neuvillette",
                     "lastname": "Dragonidk",
                     "street": "Courthouse",
                     "housenumber": 69,
                     "zipcode": "4829HF",
                     "city": "Court of Fontaine",
                     "country": "Fontaine",
                     "phonenumber": "9039032",
                     "role": "CUSTOMER"}
                                    """));

        verify(userManager).getUser(1);
    }


    //why does it return 200 in tests but 400 in the postman
    @Test
    void getUser_shouldReturn200WithoutUser_whenUserNotFound() throws Exception{
        when(userManager.getUser(1)).thenReturn(null);

        mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().doesNotExist(null));

        verify(userManager).getUser(1);
    }
}
