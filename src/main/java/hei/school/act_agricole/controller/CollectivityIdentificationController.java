package hei.school.act_agricole.controller;

import hei.school.act_agricole.dto.request.AssignIdentificationRequest;
import hei.school.act_agricole.dto.response.CollectivityResponse;
import hei.school.act_agricole.service.CollectivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectivityIdentificationController {

    private final CollectivityService collectivityService;

    public CollectivityIdentificationController(CollectivityService collectivityService) {
        this.collectivityService = collectivityService;
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
