package com.osweld.dev.controllers;

import com.osweld.dev.models.entity.Assignment;
import com.osweld.dev.models.entity.SubjectsPerSemester;
import com.osweld.dev.services.AssignmentService;
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
@RequestMapping("/api")
@RestController
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/assignment/{subjectsPerSemesterId}/{activityId}/{activityNumberId}")
    public ResponseEntity<Map<String, Object>> saveSubject(@Valid @RequestBody Assignment assignment, BindingResult result,
                                                           @PathVariable Long subjectsPerSemesterId, @PathVariable Long activityId,
                                                           @PathVariable Long activityNumberId, Authentication auth) {
        Map<String, Object> body = new HashMap<>();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.
                            toMap(error -> error.getField(), error -> error.getDefaultMessage()));
            body.put("errors", errors);
            body.put("error", "Hubo un problema al crear la materia");
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        try {
            Long userId = (Long) auth.getPrincipal();
            Assignment assignmentDB = assignmentService.saveAssignment(assignment,activityId,activityNumberId,subjectsPerSemesterId,userId);
            if (assignmentDB != null) {
                body.put("success", "Actividad guardado exitosamente");
                body.put("assignment", assignmentDB);
                return new ResponseEntity<>(body, HttpStatus.CREATED);
            } else {
                body.put("error", "No se pudo crear la actividad");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (DataAccessException e) {
            body.put("error", "No se pudo crear la actividad");
            body.put("log", "No se pudo crear la actividad: " + e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<Map<String, Object>> getSubject(@PathVariable Long assignmentId, Authentication auth) {
        Map<String, Object> body = new HashMap<>();
        try {
            Long userId = (Long) auth.getPrincipal();
            Assignment assignment = assignmentService.getAssignment(assignmentId,userId);
            if (assignment != null) {
                body.put("assignment", assignment);
                body.put("success", "Se a obtenido exitosamente la actividad");
                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                body.put("error", "Actividad no encontrada");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (DataAccessException e) {
            body.put("error", "No se pudo obtener la actividad");
            body.put("log", "No se pudo obtener la actividad: " + e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/assignments/{subjectsPerSemesterId}")
    public ResponseEntity<Map<String, Object>> getAllSubjectsPerSemester(@PathVariable Long subjectsPerSemesterId, Authentication auth) {
        Map<String, Object> body = new HashMap<>();
        try {
            Long userId = (Long) auth.getPrincipal();
            List<Assignment> assignmentList = assignmentService.getAllAssignmentBySubjectsPerSemesterId(subjectsPerSemesterId,userId);
            if (assignmentList.size() > 0) {
                body.put("assignment", assignmentList);
                body.put("success", "Se a obtenido exitosamente las actividades");
                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                body.put("error", "Actividades no encontrados");
                return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (DataAccessException e) {
            body.put("error", "No se pudo obtener las actividades");
            body.put("log", "No se pudo obtener las actividades: " + e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/assignment/{assignmentId}")
    public ResponseEntity<Map<String, Object>> deleteSemester(@PathVariable Long assignmentId, Authentication auth) {
        Map<String, Object> body = new HashMap<>();
        try {
            Long userId = (Long) auth.getPrincipal();
            assignmentService.deleteAssignment(assignmentId,userId);
            body.put("success", "Actividad eliminada exitosamente");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (DataAccessException e) {
            body.put("error", "No se pudo eliminar la actividad");
            body.put("log", "No se pudo eliminar la actividad: " + e.getMostSpecificCause());
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
