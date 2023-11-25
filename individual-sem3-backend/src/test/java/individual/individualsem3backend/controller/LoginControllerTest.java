package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import individual.individualsem3backend.persistence.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

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


//    @Test
//    void login_withUnregisteredUser_ShouldReturnBadRequest() throws Exception {
//        String email = "Neuvi@gmail.com";
//        String password = "Aaaaa";
//
//        // Mock the behavior of LoginManager to throw UserException
//        when(loginManager.userLogin(email, password)).thenThrow(UserException.class);
//
//        mockMvc.perform(post("/login")
//                        .contentType(APPLICATION_JSON_VALUE)
//                        .content("{ \"email\": \"\", \"password\": \"\" } "))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
//                .andExpect(content().json("""
//                    {"type":"about:blank",
//                    "title":"Bad Request",
//                    "status":400,
//                    "detail":"Failed to read request",
//                    "instance":"/login",
//                    "properties":null}
//                     """));
//
//        verify(loginManager).userLogin(email, password);
//    }
//
//



}
