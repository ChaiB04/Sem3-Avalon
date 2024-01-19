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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("dev")
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
                     "phonenumber": "9039032"}"""));

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

    @Test
    void createUser_shouldReturn201_WhenCreationSuccessfull() throws Exception{
        User newUser = User.builder().email("test@test.com")
                .password("test")
                .firstname("Chai")
                .lastname("Neuvi")
                .street("flamingostraat")
                .housenumber(69)
                .zipcode("5912XH")
                .city("Venlo")
                .country("The Netherlands")
                .phonenumber("0623839").build();

        User user = User.builder().id(1)
                .email("test@test.com")
                .password("test")
                .firstname("Chai")
                .lastname("Neuvi")
                .street("flamingostraat")
                .housenumber(69)
                .zipcode("5912XH")
                .city("Venlo")
                .country("The Netherlands")
                .phonenumber("0623839").build();

        when(userManager.createUser(newUser)).thenReturn(user);

        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{ \"email\": \"test@test.com\",\n" +
                                "    \"password\": \"test\",\n" +
                                "    \"firstname\": \"Chai\",\n" +
                                "    \"lastname\": \"Neuvi\",\n" +
                                "    \"street\": \"flamingostraat\",\n" +
                                "    \"housenumber\": 69,\n" +
                                "    \"zipcode\": \"5912XH\",\n" +
                                "    \"city\": \"Venlo\",\n" +
                                "    \"country\": \"The Netherlands\",\n" +
                                "    \"phonenumber\": \"0623839\"" +
                                "} "))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                        .andExpect(content().json("""
                            {
                                "id": 1
                            } """));

        verify(userManager).createUser(newUser);

    }

    @Test
    void createUser_ShouldReturn400_WhenInvalidInput() throws Exception{
        User newUser = User.builder()
                .housenumber(69)
                .zipcode("5912XH")
                .city("Venlo")
                .country("The Netherlands")
                .phonenumber("0623839").build();

        when(userManager.createUser(newUser)).thenThrow(new UserException("rawPassword cannot be null"));

        mockMvc.perform(post("/users")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{ \"housenumber\": \"69\"," +
                                "    \"zipcode\": \"5912XH\",\n" +
                                "    \"city\": \"Venlo\",\n" +
                                "    \"country\": \"The Netherlands\",\n" +
                                "    \"phonenumber\": \"0623839\"" +
                                "} "))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                    {
                        "type": "/validation-error",
                        "title": "Bad Request",
                        "status": 400,
                        "detail": "Invalid request",
                        "instance": "/users",
                        "properties": {
                            "errors": [
                                {
                                    "field": null,
                                    "error": "rawPassword cannot be null"
                                }
                            ]
                        }
                    }
                    """));

        verify(userManager).createUser(newUser);

    }


    @Test
    @WithMockUser(roles = "ADMINISTRATOR")
    void deleteUser_ShouldReturn204_WhenSuccessful() throws Exception{
        doNothing().when(userManager).deleteUser(1);

        mockMvc.perform(delete("/users/1")
                .contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(userManager).deleteUser(1);

    }



    //this one gives me an error where it has problems with equalling the user object in verify and the when method
    @Test
    void editUser_ShouldReturn204_WhenSuccessful() throws Exception{

        doNothing().when(userManager).editUser(any(User.class));

        mockMvc.perform(put("/users/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{  \"email\": \"test@test.com\"," +
                                "    \"password\": \"test\",\n" +
                                "    \"firstname\": \"Chai\",\n" +
                                "    \"lastname\": \"Neuvi\",\n" +
                                "    \"street\": \"flamingostraat\",\n" +
                                "    \"housenumber\": 69,\n" +
                                "    \"zipcode\": \"5912XH\",\n" +
                                "    \"city\": \"Venlo\",\n" +
                                "    \"country\": \"The Netherlands\",\n" +
                                "    \"phonenumber\": \"0623839\"}"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(userManager).editUser(any(User.class));

    }


    @Test
    void editUser_ShouldReturn400_WhenNoUserFound() throws Exception{

        doThrow(new UserException("User not found for ID: 1")).when(userManager).editUser(any(User.class));

        mockMvc.perform(put("/users/1")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{  \"email\": \"test@test.com\"," +
                                "    \"password\": \"test\",\n" +
                                "    \"firstname\": \"Chai\",\n" +
                                "    \"lastname\": \"Neuvi\",\n" +
                                "    \"street\": \"flamingostraat\",\n" +
                                "    \"housenumber\": 69,\n" +
                                "    \"zipcode\": \"5912XH\",\n" +
                                "    \"city\": \"Venlo\",\n" +
                                "    \"country\": \"The Netherlands\",\n" +
                                "    \"phonenumber\": \"0623839\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(userManager).editUser(any(User.class));

    }


}
