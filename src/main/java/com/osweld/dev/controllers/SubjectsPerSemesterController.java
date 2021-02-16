package com.osweld.dev.controllers;

import com.osweld.dev.models.entity.Semester;
import com.osweld.dev.models.entity.SubjectsPerSemester;
import com.osweld.dev.services.SubjectsPerSemesterService;
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
public class SubjectsPerSemesterController {

    @Autowired
    private SubjectsPerSemesterService subjectsPerSemesterService;

    @PostMapping("/subjectspersemester/{subjectsPerCareerId}/{semesterId}")
    public ResponseEntity<Map<String,Object>> saveSubject(@PathVariable Long subjectsPerCareerId,@PathVariable Long semesterId,
                                                           Authentication auth){
        Map<String,Object > body = new HashMap<>();
//        if(result.hasErrors()){
//            Map<String,String> errors = result.getFieldErrors().stream()
//                    .collect(Collectors.
//                            toMap(error -> error.getField(),error -> error.getDefaultMessage()));
//            body.put("errors",errors);
//            body.put("error","Hubo un problema al crear la materia");
//            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
//        }

        try{
            Long userId = (Long)auth.getPrincipal();
            SubjectsPerSemester subjectsPerSemesterDB =
                    subjectsPerSemesterService.saveSubjectsPerSemester(subjectsPerCareerId,semesterId,userId);
            if(subjectsPerSemesterDB != null){
                //body.put("success","Materia guardado exitosamente");
                body.put("subjectsPerSemester",subjectsPerSemesterDB);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            }else{
                body.put("error","No se pudo crear la materia");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se pudo crear la materia");
            body.put("log","No se pudo crear la materia: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subjectpersemester/{subjectsPerSemesterId}")
    public ResponseEntity<Map<String,Object>> getSubject(@PathVariable Long subjectsPerSemesterId,Authentication auth){
        Map<String,Object> body = new HashMap<>();
        try{
            Long userId = (Long)auth.getPrincipal();
            SubjectsPerSemester subjectsPerSemester = subjectsPerSemesterService.getSubjectsPerSemester(subjectsPerSemesterId,userId);
            if(subjectsPerSemester != null){
                body.put("subjectsPerSemester",subjectsPerSemester);
                //body.put("success","Se a obtenido exitosamente la materia");
                return new ResponseEntity<>(body,HttpStatus.OK);
            }else{
                body.put("error","Materia no encontrada");
                return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (DataAccessException e){
            body.put("error","No se pudo obtener la materia");
            body.put("log","No se pudo obtener la materia: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subjectspersemester/{semesterId}")
    public ResponseEntity<Map<String,Object>> getAllSubjectsPerSemester(@PathVariable Long semesterId, Authentication auth){
        Map<String,Object> body = new HashMap<>();
        try{
            Long userId = (Long)auth.getPrincipal();
            List<SubjectsPerSemester> subjectsPerSemesterList = subjectsPerSemesterService.getAllSubjectsPerSemesterBySemesterId(semesterId,userId);
            if(subjectsPerSemesterList.size() > 0){
                body.put("subjectsPerSemester",subjectsPerSemesterList);
                //body.put("success","Se a obtenido exitosamente las materias");
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

    @DeleteMapping("/subjectpersemester/{subjectsPerSemesterId}")
    public ResponseEntity<Map<String,Object>> deleteSemester(@PathVariable Long subjectsPerSemesterId,Authentication auth){
        Map<String,Object> body = new HashMap<>();
        try{
            Long userId = (Long)auth.getPrincipal();
            subjectsPerSemesterService.deleteSubjectsPerSemester(subjectsPerSemesterId,userId);
            body.put("success","Materia eliminada exitosamente");
            return new ResponseEntity<>(body,HttpStatus.OK);
        }catch (DataAccessException e){
            body.put("error","No se pudo eliminar la materia");
            body.put("log","No se pudo eliminar la materia: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
