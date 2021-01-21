package com.osweld.dev.controllers;

import com.osweld.dev.models.entity.User;
import com.osweld.dev.services.UserService;
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
                body.put("user",userResult);
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

    @GetMapping("/one/{idUser}")
    public ResponseEntity<Map<String,Object>> getUserById(@PathVariable Long idUser){
        ResponseEntity<Map<String, Object>> responseEntity = null;
        Map<String,Object> body = new HashMap<String,Object>();
        try{
            User userResult = userService.getUser(idUser);
            if(userResult != null){
                body.put("message","Se obtuvo el usuario exitosamente");
                body.put("user",userResult);
                responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.OK);
            }else{
                body.put("message","No se pudo obtener el usuario");
                body.put("error","usuario no encontrado");
                responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch (DataAccessException e){
            body.put("message","No se pudo obtener el usuario");
            body.put("error","usuario no encontrado: "+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/all/")
    public ResponseEntity<Map<String,Object>> getAllUser(){
        ResponseEntity<Map<String,Object>> responseEntity = null;
        Map<String,Object> body = new HashMap<String,Object>();
        try{
            List<User> userList = userService.getAllUser();
            if(userList.size() != 0){
                body.put("message","Se obtuvieron los usuarios exitosamente");
                body.put("users",userList);
                responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.OK);
            }else{
                body.put("message","No se pudieron obtener los usuarios");
                body.put("error","No se han encontrado usuarios");
                responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("message","No se pudieron obtener los usuarios");
            body.put("error","No se han encontrado usuarios: "+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<Map<String,Object>>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
