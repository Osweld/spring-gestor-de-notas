package com.osweld.dev.auth.controller;

import com.osweld.dev.auth.services.JWTService;
import com.osweld.dev.auth.services.JWTServiceImpl;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class authController {

    @Autowired
    private JWTService jwtService;

    @GetMapping("/refresh")
    public ResponseEntity<Map<String,Object>> refreshtoken(HttpServletRequest request) throws IOException {
        Map<String,Object> body =new HashMap<>();
        String header = request.getHeader(JWTServiceImpl.HEADER_STRING);
        if(!requiresAutehtication(header) || !jwtService.validate(header)) {
            body.put("error","Hubo un problema al refrescar el token");
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }
        try{
            String token = jwtService.refreshToken(jwtService.getId(header), jwtService.getUsername(header), jwtService.getAuthorities(header) );
            body.put("token",token);
            body.put("expiration",jwtService.getExpiration(JWTServiceImpl.TOKEN_PREFIX+token));
            return new ResponseEntity<>(body,HttpStatus.OK);
        }catch(JwtException e){
            body.put("error","Hubo un problema al crear el token");
            body.put("error","Hubo un problema al crear el token: "+e.getLocalizedMessage());
            return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
        }
    }


    protected Boolean requiresAutehtication(String header) {
        if(header == null || !header.startsWith(JWTServiceImpl.TOKEN_PREFIX)) return false;
        return true;
    }

}
