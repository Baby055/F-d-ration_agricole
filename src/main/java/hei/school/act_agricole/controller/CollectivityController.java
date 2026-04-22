package hei.school.act_agricole.controller;

import hei.school.act_agricole.dto.request.AssignIdentificationRequest;
import hei.school.act_agricole.dto.request.CreateCollectivityRequest;
import hei.school.act_agricole.dto.response.CollectivityResponse;
import hei.school.act_agricole.service.CollectivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CollectivityController {

    private final CollectivityService service;

    public CollectivityController(CollectivityService service) {
        this.service = service;
    }

    @PostMapping("/collectivities")
    public ResponseEntity<List<CollectivityResponse>> createCollectivities(@RequestBody List<CreateCollectivityRequest> requests) {
        List<CollectivityResponse> responses = service.createCollectivities(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }

    @PutMapping("/collectivities/{collectivityId}/identification")
    public ResponseEntity<CollectivityResponse> assignIdentification(
            @PathVariable String collectivityId,
            @RequestBody AssignIdentificationRequest request) {
        CollectivityResponse response = collectivityService.assignIdentification(
                collectivityId, request.getNumber(), request.getName());
        return ResponseEntity.ok(response);
    }
}