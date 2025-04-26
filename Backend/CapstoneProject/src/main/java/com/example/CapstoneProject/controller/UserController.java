package com.example.CapstoneProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.CapstoneProject.model.User;
import com.example.CapstoneProject.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:5500")
public class UserController {
	@Autowired private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {
        return userService.login(data.get("email"), data.get("password"))
                .<ResponseEntity<?>>map(user -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("userId", user.getId());
                    result.put("name", user.getName());
                    return ResponseEntity.ok(result);
                })
                .orElseGet(() -> ResponseEntity.badRequest().body("Invalid credentials"));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<?> profile(@PathVariable("userId") Long id) {
    	 User user = userService.getProfile(id); // returns User, or null
    	    if (user != null) {
    	        return ResponseEntity.ok(user);
    	    } else {
    	        return ResponseEntity.notFound().build();
    	    }

    }
   
    

    @PostMapping("/change-password/{name}")
    public ResponseEntity<String> changePassword(
    		@PathVariable String name,
            @RequestParam("oldPassword") String oldPassword,  
            @RequestParam("newPassword") String newPassword) {
        try {
            userService.changePassword(name, oldPassword, newPassword);
            return ResponseEntity.ok("Password changed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

}

