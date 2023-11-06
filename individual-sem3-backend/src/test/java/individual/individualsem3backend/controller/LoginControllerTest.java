package individual.individualsem3backend.controller;

import individual.individualsem3backend.business.LoginManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginManager loginManager;

//    @Test
//    void login_withUnregisteredUser_ShouldReturnBadRequest() throws Exception {
//        String email = "Neivi@gmail.com";
//        String password = "Aaaaa";
//        when(loginManager.userLogin(email, password)).thenReturn(null);
//
//        mockMvc.perform(post("/login"))
//                .andDo(print())
//                .andExpect(status().isBadRequest())
//                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE));
//
//        verify(loginManager).userLogin(email, password);
//    }


}
