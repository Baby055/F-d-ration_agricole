package hei.school.act_agricole.controller;

import hei.school.act_agricole.dto.request.CreateMemberPaymentRequest;
import hei.school.act_agricole.dto.response.MemberPaymentResponse;
import hei.school.act_agricole.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/members/{id}/payments")
    public ResponseEntity<List<MemberPaymentResponse>> createPayments(
            @PathVariable String id,
            @RequestBody List<CreateMemberPaymentRequest> requests) {
        List<MemberPaymentResponse> responses = service.createPayments(id, requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }
}
