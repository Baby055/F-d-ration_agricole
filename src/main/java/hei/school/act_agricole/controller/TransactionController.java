package hei.school.act_agricole.controller;

import hei.school.act_agricole.dto.request.CreateMemberPaymentRequest;
import hei.school.act_agricole.dto.response.CollectivityTransactionResponse;
import hei.school.act_agricole.dto.response.MemberPaymentResponse;
import hei.school.act_agricole.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/collectivities/{id}/transactions")
    public ResponseEntity<List<CollectivityTransactionResponse>> getTransactions(
            @PathVariable String id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<CollectivityTransactionResponse> responses = service.getTransactions(id, from, to);
        return ResponseEntity.ok(responses);
    }
}
