package com.osweld.dev.controllers;

import com.osweld.dev.models.entity.*;
import com.osweld.dev.services.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin()
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


    @GetMapping("/career")
    public ResponseEntity<Map<String,Object>> getAllCareer(){

        Map<String,Object> body = new HashMap<>();
        try{
            List<Career> careerList = careerService.getallCareer();
            if(careerList.size() > 0){
                //body.put("success","Se obtuvieron las carreras");
                body.put("career",careerList);
                return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","No se pudo obtener las carreras");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se pudo obtener las carreras");
            body.put("log","Hubo un error: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cycle")
    public ResponseEntity<Map<String,Object>> getAllCycle(){
        Map<String,Object> body = new HashMap<>();
        try{
            List<Cycle> cycleList = cycleService.getAllCycle();
            if(cycleList.size() > 0){
                //body.put("success","Se obtuvieron los ciclos");
                body.put("cycle",cycleList);
                return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","No se pudo obtener los ciclos");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se pudo obtener los ciclos");
            body.put("log","Hubo un error: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/year")
    public ResponseEntity<Map<String,Object>> getAllYear(){
        Map<String,Object> body = new HashMap<>();
        try{
            List<Year> yearList = yearService.getAllYear();
            if(yearList.size() > 0){
                //body.put("success","Se obtuvieron los años");
                body.put("year",yearList);
               return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","No se pudo obtener los años");
               return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se pudo obtener los años");
            body.put("log","Hubo un error: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/subjectspercareer/{idCareer}/{idCycle}")
    public ResponseEntity<Map<String,Object>> getAllSubjectsPerCareer(@PathVariable Long idCareer,@PathVariable Long idCycle){
        Map<String,Object> body = new HashMap<>();
        try{
           List<SubjectsPerCareer> subjectsPerCareerList = subjectsPerCareerService.getAllSubjectsPerCareerByCareerAndCycle(idCareer,idCycle);
            if(subjectsPerCareerList.size() > 0){
                //body.put("success","Se obtuvieron las materias de la carrera");
                body.put("subjectsPerCareer",subjectsPerCareerList);
               return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","No se pudo obtener las materias de la carrera");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se pudo obtener las materias de la carrera");
            body.put("log","Hubo un error: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activity")
    public ResponseEntity<Map<String,Object>> getAllActivity(){
        Map<String,Object> body = new HashMap<>();
        try{
            List<Activity> activityList = activityService.getAllActivity();
            if(activityList.size() > 0){
                //body.put("success","Se obtuvieron las actividades");
                body.put("activity",activityList);
                return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","No se pudo obtener las actividades");
               return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se pudo obtener las actividades");
            body.put("log","Hubo un error: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/activitynumber")
    public ResponseEntity<Map<String,Object>> getAllActivityNumber(){
        Map<String,Object> body = new HashMap<>();
        try{
            List<ActivityNumber> activityNumberList = activityNumberService.getAllActivityNumber();
            if(activityNumberList.size() > 0){
                //body.put("success","Se obtuvo el numero de actividad");
                body.put("activityNumber",activityNumberList);
                return new ResponseEntity<>(body, HttpStatus.OK);
            }else{
                body.put("error","No se pudo el numero de actividad");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(DataAccessException e){
            body.put("error","No se pudo obtener el numero de actividad");
            body.put("log","Hubo un error: "+e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
