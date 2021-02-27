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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JavaMailService mailService;

    @Autowired
    private UserService userService;

    //Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/token/resetpassword/{email}")
    public ResponseEntity<Map<String,Object>> sendResetPasswordToken(@PathVariable String email){
        Map<String,Object> body = new HashMap<>();

        try{
            User user = userService.getUserByEmail(email);
            if(user != null){

                Token token = tokenService.createToken(user.getId(),2L);
                mailService.SendMimeMessageActivation(user.getPerson().getEmail(),token.getToken());
                //body.put("success","Se a enviado un enlace a tu correo electronico");
                return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","Correo no encontrado");
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
            }
        }catch(DataAccessException e){
            body.put("error","Hubo un error al momento de enviar el enlace");
            body.put("log","Hubo un error al momento de enviar el enlace: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/token/activeaccount/{email}")
    public ResponseEntity<Map<String,Object>> sendActivateAccountMail(@PathVariable String email){
        Map<String,Object> body = new HashMap<>();

        try{
            User user = userService.getUserByEmail(email);
            if(user != null){

                if(user.getActive()){
                   // body.put("success","El usuario ya esta activo");
                    return new ResponseEntity<>(body, HttpStatus.OK);
                }
                Token token = tokenService.createToken(user.getId(),1L);
                mailService.SendMimeMessageActivation(user.getPerson().getEmail(),token.getToken());
                body.put("success","Se a enviado un enlace a tu correo electronico");
                return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","Correo no encontrado");
                return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
            }
        }catch(DataAccessException e){
            body.put("error","Hubo un error al momento de enviar el enlace");
            body.put("log","Hubo un error al momento de enviar el enlace: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/token/activeaccount/{token}")
    public ResponseEntity<Map<String,Object>> activeAccountToken(@PathVariable String token){
        Map<String,Object> body = new HashMap<>();
        try{
            Token tokenDB = tokenService.getToken(token);
            if(tokenDB != null ){

                if(tokenDB.getActivated()){
                  //  body.put("success","El usuario ya esta activo");
                    return new ResponseEntity<>(body,HttpStatus.OK);
                }

                Long expirationDate = tokenDB.getExpirationDate().getTime();
                if(expirationDate > System.currentTimeMillis()
                        && tokenDB.getTokenType().getId() == 1L){
                    User user = tokenDB.getUser();
                    user.setActive(true);
                    userService.updateUser(user);
                    tokenDB.setActivated(true);
                    tokenService.activatedToken(tokenDB);
                    body.put("success","El usuario fue activado exitosamente");
                    return new ResponseEntity<>(body,HttpStatus.OK);
                }else{
                    body.put("error","El token no es valido");
                    return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
                }
            }else{
                body.put("error","El token no fue encontrado");
                return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
            }
        }catch(DataAccessException e){
            body.put("error","Hubo un problema al acivar la cuenta");
            return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/token/resetpassword/{token}")
    public ResponseEntity<Map<String,Object>> ResetPassword(@PathVariable String token, @RequestBody User userPassword){
        Map<String,Object> body = new HashMap<>();
        try{
            Token tokenDB = tokenService.getToken(token);

            if(tokenDB.getActivated()){
                body.put("error","El token ya fue utilizado");
                return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
            }

            if(tokenDB != null ){
                Long expirationDate = tokenDB.getExpirationDate().getTime();
                if(expirationDate > System.currentTimeMillis()
                        && tokenDB.getTokenType().getId() == 2L){
                    User user = tokenDB.getUser();
                    user.setPassword(userPassword.getPassword());
                    userService.updatePassword(user);
                    tokenDB.setActivated(true);
                    tokenService.activatedToken(tokenDB);
                    //body.put("success","La contraseña fue cambiada exitosamente");
                    return new ResponseEntity<>(body,HttpStatus.OK);
                }else{
                    body.put("error","El token no es valido");
                    return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
                }
            }else{
                body.put("error","El token no fue encontrado");
                return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
            }
        }catch(DataAccessException e){
            body.put("error","Hubo un problema, la contraseña no pudo ser cambiada");
            return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
        }
    }
}
