package com.example.CapstoneProject.controller;

import com.example.CapstoneProject.model.User;
import com.example.CapstoneProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegister_Success() {
        User user = new User();
        when(userService.register(user)).thenReturn("Registered Successfully");

        ResponseEntity<?> response = userController.register(user);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Registered Successfully", response.getBody());
    }

    @Test
    void testLogin_Success() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "test@example.com");
        data.put("password", "password");

        User user = new User();
        user.setId(1L);
        user.setName("Test User");

        when(userService.login("test@example.com", "password")).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userController.login(data);

        assertEquals(200, response.getStatusCodeValue());
        Map<?, ?> body = (Map<?, ?>) response.getBody();
        assertEquals(1L, body.get("userId"));
        assertEquals("Test User", body.get("name"));
    }

    @Test
    void testLogin_Failure() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "wrong@example.com");
        data.put("password", "wrongpass");

        when(userService.login("wrong@example.com", "wrongpass")).thenReturn(Optional.empty());

        ResponseEntity<?> response = userController.login(data);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid credentials", response.getBody());
    }

    @Test
    void testProfile_Success() {
        User user = new User();
        user.setId(1L);

        when(userService.getProfile(1L)).thenReturn(user);

        ResponseEntity<?> response = userController.profile(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(user, response.getBody());
    }

    @Test
    void testProfile_NotFound() {
        when(userService.getProfile(1L)).thenReturn(null);

        ResponseEntity<?> response = userController.profile(1L);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testChangePassword_Success() {
        doNothing().when(userService).changePassword("TestUser", "oldpass", "newpass");

        ResponseEntity<String> response = userController.changePassword("TestUser", "oldpass", "newpass");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Password changed successfully!", response.getBody());
    }

    @Test
    void testChangePassword_Failure() {
        doThrow(new RuntimeException("Invalid old password")).when(userService)
                .changePassword("TestUser", "wrongold", "newpass");

        ResponseEntity<String> response = userController.changePassword("TestUser", "wrongold", "newpass");

        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Error"));
    }
}

