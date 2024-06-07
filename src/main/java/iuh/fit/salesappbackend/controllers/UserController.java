package iuh.fit.salesappbackend.controllers;

import iuh.fit.salesappbackend.dtos.requests.UserDto;
import iuh.fit.salesappbackend.dtos.responses.ResponseSuccess;
import iuh.fit.salesappbackend.mappers.UserMapper;
import iuh.fit.salesappbackend.models.User;
import iuh.fit.salesappbackend.service.interfaces.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseSuccess<?> createUser(@RequestBody @Valid UserDto userDto) {
        User user = userMapper.userDto2User(userDto);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "User created successfully",
                userService.save(user)
        );
    }

    @GetMapping
    public ResponseSuccess<?> getAllUsers() {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "Get all users successfully",
                userService.findAll()
        );
    }

    @PutMapping("/{id}")
    public ResponseSuccess<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        User user = userMapper.userDto2User(userDto);
        user.setId(id);
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "User updated successfully",
                userService.update(id, user)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseSuccess<?> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseSuccess<>(
                HttpStatus.NO_CONTENT.value(),
                "User deleted successfully with id: " + id
        );
    }

    @PatchMapping("/{id}")
    public ResponseSuccess<?> patchUser(@PathVariable Long id, @RequestBody Map<String, ?> data) {
        return new ResponseSuccess<>(
                HttpStatus.OK.value(),
                "User updated successfully",
                userService.updatePatch(id, data)
        );
    }
}
