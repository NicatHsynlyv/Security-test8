package com.example.SpringS.Controller;

import com.example.SpringS.Entity.Enum.User;
import com.example.SpringS.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('WRITE')")
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return ResponseEntity.ok(newUser);
    }


    @PreAuthorize("hasAuthority('DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyAuthority('WRITE') or hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/findByUsername")
    public ResponseEntity<User> findByUsername(@RequestParam String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PreAuthorize("hasAuthority('WRITE')")
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
}

@GetMapping("/csrf-token")
public CsrfToken getCsrfToken(CsrfToken csrfToken) {
        return csrfToken;
}


}
