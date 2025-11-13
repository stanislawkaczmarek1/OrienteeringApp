package com.example.orienteeringapp.web.controller;

import com.example.orienteeringapp.application.dto.GetUserResponseDto;
import com.example.orienteeringapp.application.service.UserService;
import com.example.orienteeringapp.application.dto.CreateUserDto;
import com.example.orienteeringapp.application.dto.CreateUserResponseDto;
<<<<<<< HEAD
import com.example.orienteeringapp.application.dto.UserDto;
=======
import org.springframework.http.HttpStatus;
>>>>>>> 8dabfd2 (added get user endpoint, global exeception handler, changed controller and service)
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponseDto> createUser(@RequestBody CreateUserDto dto) {
        CreateUserResponseDto responseDto = userService.createUser(dto);
        return  new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<GetUserResponseDto> getUser(@PathVariable Long id) {
        GetUserResponseDto responseDto = userService.getUser(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
