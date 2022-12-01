package com.megaptera.makaogift.controllers;

import com.megaptera.makaogift.application.CreateUserService;
import com.megaptera.makaogift.exceptions.ExistingUsername;
import com.megaptera.makaogift.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@ActiveProfiles("test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateUserService createUserService;

    @Test
    void signUp() throws Exception {
        given(createUserService.create(any(), any(), any(), any()))
                .willReturn(User.fake());

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"myid\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"Abcdef1!\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"id\"")
                ));
    }

    @Test
    void signUpWithShortName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"길동\"," +
                                "\"username\":\"newid\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"Abcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithLongName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"길동길동길동길동\"," +
                                "\"username\":\"newid\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"Abcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithEnglishName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"Gildong\"," +
                                "\"username\":\"newid\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"Abcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithKoreanUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"홍길동씨\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"Abcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithShortUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"id1\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"Abcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithLongUsername() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"idididididididid1\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"Abcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithExistingUsername() throws Exception {
        given(createUserService.create(any(), any(), any(), any()))
                .willThrow(new ExistingUsername("myid"));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"myid\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"Abcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithShortPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"myid\"," +
                                "\"password\":\"Abcde1!\"," +
                                "\"passwordCheck\":\"Abcde1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutUppercase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"myid\"," +
                                "\"password\":\"abcdef1!\"," +
                                "\"passwordCheck\":\"abcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutLowercase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"myid\"," +
                                "\"password\":\"ABCDEF1!\"," +
                                "\"passwordCheck\":\"ABCDEF1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"myid\"," +
                                "\"password\":\"Abcdefg!\"," +
                                "\"passwordCheck\":\"Abcdefg!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithPasswordWithoutSpecialCharacter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"myid\"," +
                                "\"password\":\"Abcdefg1\"," +
                                "\"passwordCheck\":\"Abcdefg1\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUpWithUnmatchedPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"name\":\"홍길동\"," +
                                "\"username\":\"myid\"," +
                                "\"password\":\"Abcdef1!\"," +
                                "\"passwordCheck\":\"ABcdef1!\"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }
}
