package com.osweld.dev.controllers;

import com.osweld.dev.models.entity.*;
import com.osweld.dev.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/utils")
public class UtilsController {

    @Autowired
    private CareerService careerService;

    @Autowired
    private CycleService cycleService;

    @Autowired
    private YearService yearService;

    @Autowired
    private SubjectsPerCareerService subjectsPerCareerService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityNumberService activityNumberService;

    private ResponseEntity<Map<String,Object>> responseEntity;

    private Map<String,Object> body = new HashMap<>();

    @GetMapping("/career")
    public ResponseEntity<Map<String,Object>> getAllCareer(){

        Map<String,Object> body = new HashMap<>();
        try{
            List<Career> careerList = careerService.getallCareer();
            if(careerList != null){
                body.put("message","Se obtuvieron las carreras");
                body.put("career",careerList);
                responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("message","No se pudo obtener las carreras");
                body.put("error","Hubo un error");
                responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("message","No se pudo obtener las carreras");
            body.put("error","Hubo un error: "+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/cycle")
    public ResponseEntity<Map<String,Object>> getAllCycle(){
        try{
            List<Cycle> cycleList = cycleService.getAllCycle();
            if(cycleList != null){
                body.put("message","Se obtuvieron los ciclos");
                body.put("cycle",cycleList);
                responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("message","No se pudo obtener los ciclos");
                body.put("error","Hubo un error");
                responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("message","No se pudo obtener los ciclos");
            body.put("error","Hubo un error: "+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/year")
    public ResponseEntity<Map<String,Object>> getAllYear(){
        try{
            List<Year> yearList = yearService.getAllYear();
            if(yearList != null){
                body.put("message","Se obtuvieron los años");
                body.put("year",yearList);
                responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("message","No se pudo obtener los años");
                body.put("error","Hubo un error");
                responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("message","No se pudo obtener los años");
            body.put("error","Hubo un error: "+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/subjectspercareer/{idCareer}/{idCycle}")
    public ResponseEntity<Map<String,Object>> getAllSubjectsPerCareer(@PathVariable Long idCareer,@PathVariable Long idCycle){
        try{
           List<SubjectsPerCareer> subjectsPerCareerList = subjectsPerCareerService.getAllSubjectsPerCareerByCareerAndCycle(idCareer,idCycle);
            if(subjectsPerCareerList != null){
                body.put("message","Se obtuvieron las materias de la carrera");
                body.put("subjectspercareer",subjectsPerCareerList);
                responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("message","No se pudo obtener las materias de la carrera");
                body.put("error","Hubo un error");
                responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("message","No se pudo obtener las materias de la carrera");
            body.put("error","Hubo un error: "+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/activity")
    public ResponseEntity<Map<String,Object>> getAllActivity(){
        try{
            List<Activity> activityList = activityService.getAllActivity();
            if(activityList != null){
                body.put("message","Se obtuvieron las actividades");
                body.put("activitiy",activityList);
                responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("message","No se pudo obtener las actividades");
                body.put("error","Hubo un error");
                responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("message","No se pudo obtener las actividades");
            body.put("error","Hubo un error: "+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/activitynumber")
    public ResponseEntity<Map<String,Object>> getAllActivityNumber(){
        try{
            List<ActivityNumber> activityNumberList = activityNumberService.getAllActivityNumber();
            if(activityNumberList != null){
                body.put("message","Se obtuvo el numero de actividad");
                body.put("activitiynumber",activityNumberList);
                responseEntity = new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("message","No se pudo el numero de actividad");
                body.put("error","Hubo un error");
                responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("message","No se pudo obtener el numero de actividad");
            body.put("error","Hubo un error: "+e.getMostSpecificCause());
            responseEntity = new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
