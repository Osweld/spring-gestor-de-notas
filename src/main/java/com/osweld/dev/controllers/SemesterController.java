package com.osweld.dev.controllers;

import com.osweld.dev.models.entity.Semester;
import com.osweld.dev.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @PostMapping("/semester/{cycleId}/{yearId}")
    public ResponseEntity<Map<String,Object>> saveSemester(@Valid @RequestBody Semester semester, BindingResult result,
                                                            @PathVariable Long cycleId, @PathVariable Long yearId,
                                                            Authentication auth){
        Map<String,Object > body = new HashMap<>();
        if(result.hasErrors()){
            Map<String,String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.
                            toMap(error -> error.getField(),error -> error.getDefaultMessage()));
            body.put("errors",errors);
            body.put("error","Hubo un problema al crear el usuario");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        try{
            Long userId = (Long)auth.getPrincipal();
            Semester semesterDB = semesterService.saveSemester(semester,cycleId,yearId,userId);
            if(semesterDB != null){
                body.put("success","Semestre guardado exitosamente");
                body.put("semester",semesterDB);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }else{
                body.put("error","No se pudo crear el semestre");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se pudo crear el semestre");
            body.put("log","No se pudo crear el semestre: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<Map<String,Object>> getSemester(@PathVariable Long semesterId,Authentication auth){
        Map<String,Object> body = new HashMap<>();
        try{
            Long userId = (Long)auth.getPrincipal();
            Semester semester = semesterService.getSemester(semesterId,userId);
            if(semester != null){
                body.put("semester",semester);
                body.put("success","Se a obtenido exitosamente el semestre");
                return new ResponseEntity<>(body,HttpStatus.OK);
            }else{
                body.put("error","Semestre no encontrado");
                return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (DataAccessException e){
            body.put("error","No se pudo obtener el semestre");
            body.put("log","No se pudo obtener el semestre: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/semesters")
    public ResponseEntity<Map<String,Object>> getAllSemester(Authentication auth){
        Map<String,Object> body = new HashMap<>();
        try{
            Long userId = (Long)auth.getPrincipal();
            List<Semester> semesterList = semesterService.getAllSemesterByUserId(userId);
            if(semesterList.size() > 0){
                body.put("semester",semesterList);
                body.put("success","Se a obtenido exitosamente los semestres");
                return new ResponseEntity<>(body,HttpStatus.OK);
            }else{
                body.put("error","Semestres no encontrados");
                return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (DataAccessException e){
            body.put("error","No se pudo obtener los semestres");
            body.put("log","No se pudo obtener los semestres: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/semester/{semesterId}")
    public ResponseEntity<Map<String,Object>> deleteSemester(@PathVariable Long semesterId,Authentication auth){
        Map<String,Object> body = new HashMap<>();
        try{
            Long userId = (Long)auth.getPrincipal();
            semesterService.deleteSemester(semesterId,userId);
            body.put("success","Semestre eliminado exitosamente");
            return new ResponseEntity<>(body,HttpStatus.OK);
        }catch (DataAccessException e){
            body.put("error","No se pudo eliminar el semestre");
            body.put("log","No se pudo eliminar el semestre: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
