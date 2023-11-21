package com.blog.blogappapis.controllers;

import com.blog.blogappapis.payloads.ApiResponse;
import com.blog.blogappapis.payloads.UserDto;
import com.blog.blogappapis.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto userDto){
        UserDto userDto1 = this.userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{userID}")
    public ResponseEntity<UserDto> updateUserDetails(@Valid @RequestBody UserDto userDto,@PathVariable Integer userID){
        UserDto userDto1 = this.userService.updateUser(userDto,userID);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userID){
        this.userService.deleteUser(userID);
        return new ResponseEntity(new ApiResponse("User deleted successfully!",true),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = this.userService.getAllUsers();
        return new ResponseEntity<>(userDtos,HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userID){
        UserDto userDto = this.userService.getUserById(userID);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
