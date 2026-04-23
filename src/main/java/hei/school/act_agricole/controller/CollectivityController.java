package hei.school.act_agricole.controller;

import hei.school.act_agricole.dto.request.AssignIdentificationRequest;
import hei.school.act_agricole.dto.request.CreateCollectivityRequest;
import hei.school.act_agricole.dto.response.CollectivityResponse;
import hei.school.act_agricole.entity.FinancialAccount;
import hei.school.act_agricole.service.CollectivityService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        CollectivityResponse response = service.assignIdentification(
                collectivityId, request.getNumber(), request.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/collectivities/{id}")
    public ResponseEntity<CollectivityResponse> getCollectivityById(@PathVariable String id) {
        CollectivityResponse response = service.getCollectivityById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/collectivities/{id}/financialAccounts")
    public ResponseEntity<List<FinancialAccount>> getFinancialAccounts(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate at) {
        List<FinancialAccount> accounts = service.getFinancialAccounts(id, at);
        return ResponseEntity.ok(accounts);
    }
}