package hei.school.act_agricole.controller;

import hei.school.act_agricole.dto.request.CreateMembershipFeeRequest;
import hei.school.act_agricole.dto.response.MembershipFeeResponse;
import hei.school.act_agricole.service.MembershipFeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MembershipFeeController {

    private final MembershipFeeService service;

    public MembershipFeeController(MembershipFeeService service) {
        this.service = service;
    }

    @GetMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeResponse>> getMembershipFees(@PathVariable String id) {
        return ResponseEntity.ok(service.getMembershipFees(id));
    }

    @PostMapping("/collectivities/{id}/membershipFees")
    public ResponseEntity<List<MembershipFeeResponse>> createMembershipFees(
            @PathVariable String id,
            @RequestBody List<CreateMembershipFeeRequest> requests) {
        List<MembershipFeeResponse> responses = service.createMembershipFees(id, requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }
}