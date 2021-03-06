package com.osweld.dev.controllers;

import com.osweld.dev.email.JavaMailService;
import com.osweld.dev.models.entity.Token;
import com.osweld.dev.models.entity.User;
import com.osweld.dev.services.TokenService;
import com.osweld.dev.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin()
@RestController
@RequestMapping("api")
public class RegistrationController {

    @Autowired
    private UserService userService;






    @PostMapping("/registration/{careerId}")
    private ResponseEntity<Map<String ,Object>> saveUser(@Valid @RequestBody User user, BindingResult result,
                                                         @PathVariable Long careerId){
        Map<String, Object> body = new HashMap<String,Object>();
        if(result.hasErrors()){
            Map<String,String> errors = result.getFieldErrors().stream().
                    collect(Collectors.toMap(error ->
                            error.getField(),error -> error.getDefaultMessage()));

            body.put("erorrs",errors);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        try{
            User userExist = userService.getUserByUsernameAndEmail(user.getUsername(),user.getPerson().getEmail());
            if(userExist != null){
                if(user.getUsername().equals(userExist.getUsername())){
                    body.put("error","El nombre de usuario ya existe");
                    return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
                }else{
                    body.put("error","El email ya ha sido utilizado");
                    return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }


            User userResult = userService.saveUser(user,careerId);
            if(userResult != null){
               // body.put("success","El usuario a sido creado exitosamente");
                userResult.setPassword(null);
                body.put("user",userResult);
                return new ResponseEntity<>(body,HttpStatus.OK);
            }else{
                body.put("error","El usuario no pudo ser creado");
                return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch(DataAccessException e){
            body.put("error","El usuario no pudo ser creado");
            body.put("log","El usuario no pudo ser creado :"+e.getMessage());
            e.printStackTrace();
            return  new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
/*
    @GetMapping("/users/all")
    public ResponseEntity<Map<String,Object>> getAllUser(){
        Map<String,Object> body = new HashMap<String,Object>();
        try{
            List<User> userList = userService.getAllUser();
            if(userList.size() != 0){
                body.put("success","Se obtuvieron los usuarios exitosamente");
                body.put("user",userList);
                return new ResponseEntity<Map<String,Object>>(body,HttpStatus.OK);
            }else{
                body.put("error","No se han encontrado usuarios");
                return new ResponseEntity<Map<String,Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se han encontrado los usuarios");
            body.put("log","No se han encontrado usuarios: "+e.getMostSpecificCause());
            return new ResponseEntity<Map<String,Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
