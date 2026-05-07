package hei.school.act_agricole.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hei.school.act_agricole.dto.request.CreateActivityRequest;
import hei.school.act_agricole.dto.request.CreateAttendanceRequest;
import hei.school.act_agricole.dto.response.ActivityResponse;
import hei.school.act_agricole.dto.response.AttendanceResponse;
import hei.school.act_agricole.service.ActivityService;


    @RestController
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @PostMapping("/collectivities/{id}/activities")
    public ResponseEntity<List<ActivityResponse>> createActivities(
            @PathVariable String id,
            @RequestBody List<CreateActivityRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createActivities(id, requests));
    }

    @GetMapping("/collectivities/{id}/activities")
    public ResponseEntity<List<ActivityResponse>> getActivities(@PathVariable String id) {
        return ResponseEntity.ok(service.getActivities(id));
    }

    @PostMapping("/collectivities/{id}/activities/{activityId}/attendance")
    public ResponseEntity<List<AttendanceResponse>> recordAttendance(
            @PathVariable String id,
            @PathVariable String activityId,
            @RequestBody List<CreateAttendanceRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.recordAttendance(id, activityId, requests));
    }

    @GetMapping("/collectivities/{id}/activities/{activityId}/attendance")
    public ResponseEntity<List<AttendanceResponse>> getAttendance(
            @PathVariable String id,
            @PathVariable String activityId) {
        return ResponseEntity.ok(service.getAttendance(id, activityId));
    }
}

