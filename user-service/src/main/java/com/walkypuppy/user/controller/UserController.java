package com.walkypuppy.user.controller;

import com.walkypuppy.user.dto.UserDTO;
import com.walkypuppy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @RequestMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserDTO.CreateRequest request){
        //Todo 회원가입
        userService.createUser(request.toEntity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
