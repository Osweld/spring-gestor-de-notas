package com.osweld.dev.controllers;

import com.osweld.dev.models.entity.User;
import com.osweld.dev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class RegistrationController {

    @Autowired
    private UserService userService;


    @PostMapping("/")
    private ResponseEntity<Map<String ,Object>> saveUser(@Valid @RequestBody User user, BindingResult result){
        ResponseEntity<Map<String,Object>> responseEntity = null;
        Map<String, Object> body = new HashMap<String,Object>();
        if(result.hasErrors()){
            Map<String,String> errors = new HashMap<String,String>();
            for(FieldError error : result.getFieldErrors()){
                errors.put(error.getField(),error.getDefaultMessage());
            }
            body.put("erorrs",errors);
            responseEntity = new ResponseEntity<Map<String,Object>>(body, HttpStatus.BAD_REQUEST);
            return responseEntity;
        }

        try{
            User userResult = userService.saveUser(user);
            if(userResult != null){
                body.put("message","El usuario a sido creado exitosamente");
                body.put("user",user);
                responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.OK);
            }else{
                body.put("message","Hubo un error al crear el usuario");
                body.put("error","El usuario no pudo ser creado");
                responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch(DataAccessException e){
            body.put("message","Hubo un error al crear el usuario");
            body.put("error","El usuario no pudo ser creado :"+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
