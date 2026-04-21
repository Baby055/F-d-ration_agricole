package hei.school.act_agricole.controller;

import hei.school.act_agricole.dto.request.CreateMemberRequest;
import hei.school.act_agricole.dto.response.MemberResponse;
import hei.school.act_agricole.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<List<MemberResponse>> createMembers(@RequestBody List<CreateMemberRequest> requests) {
        List<MemberResponse> responses = memberService.createMembers(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }
}