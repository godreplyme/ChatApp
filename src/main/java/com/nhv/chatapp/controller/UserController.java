package com.nhv.chatapp.controller;

import com.nhv.chatapp.dto.UserProfileDTO;
import com.nhv.chatapp.dto.response.APIResponse;
import com.nhv.chatapp.dto.response.APIResponseMessage;
import com.nhv.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    ResponseEntity<?> getUsers(){
        APIResponse apiResponse = APIResponse.builder()
                .message(APIResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .result(userService.getUsers())
                .status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}/profile")
    ResponseEntity<?> getUser(@PathVariable("userId") String userId){
        APIResponse apiResponse = APIResponse.builder()
                .message(APIResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .result(this.userService.getUserProfile(userId))
                .status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/profile")
    ResponseEntity<?> getProfile(){
        APIResponse apiResponse = APIResponse.builder()
                .message(APIResponseMessage.SUCCESSFULLY_RETRIEVED.name())
                .result(this.userService.getUserProfile())
                .status(HttpStatus.OK.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody UserProfileDTO userUpdateDTO){
        APIResponse apiResponse = APIResponse.builder()
                .message(APIResponseMessage.SUCCESSFULLY_UPDATED.name())
                .result(this.userService.getUserProfile(userId))
                .status(HttpStatus.OK.value()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<?> deleteUser(@PathVariable("userId") String userId){
        APIResponse apiResponse = APIResponse.builder()
                .message(APIResponseMessage.SUCCESSFULLY_DELETED.name())
                .status(HttpStatus.NO_CONTENT.value()).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
    }
}
