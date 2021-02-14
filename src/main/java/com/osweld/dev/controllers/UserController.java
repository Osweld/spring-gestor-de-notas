package com.osweld.dev.controllers;

import com.osweld.dev.models.entity.User;
import com.osweld.dev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public ResponseEntity<Map<String,Object>> getUserById(Authentication auth){
        Map<String,Object> body = new HashMap<>();
        try{
            Long userId = (Long) auth.getPrincipal();
            User userResult = userService.getUser(userId);
            if(userResult != null){
                userResult.setPassword(null);
                body.put("success","Se obtuvo el usuario exitosamente");
                body.put("user",userResult);
                return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","usuario no encontrado");
                return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (DataAccessException e) {
            body.put("error", "usuario no encontrado");
            body.put("log", "usuario no encontrado: " + e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
