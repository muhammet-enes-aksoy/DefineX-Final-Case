package com.example.taskmanagement.ControllerTest;

import com.example.taskmanagement.controller.AuthenticationController;

import com.example.taskmanagement.security.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = AuthenticationController.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authService;

    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void test() throws Exception {

    }
}
