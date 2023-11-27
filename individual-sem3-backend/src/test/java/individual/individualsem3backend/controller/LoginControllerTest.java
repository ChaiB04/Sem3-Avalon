package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.business.exception.UserException;
import individual.individualsem3backend.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

    @Mock
    private UserRepository userRepositoryMock;

    @Test
    void login(){

    }


    @Test
    void login_withUnregisteredUser_ShouldReturnBadRequest() throws Exception {
//        String email = "Neuvi@gmail.com";
//        String password = "Aaaaa";
//
//        // Mock the behavior of LoginManager to throw UserException
//        when(loginManager.userLogin(email, password)).thenThrow(new UserException("Invalid Credentials"));

        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("{ \"email\": \"\", \"password\": \"\" } "))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verifyNoInteractions(loginManager);
//        verify(loginManager).userLogin(email, password);
    }





}
