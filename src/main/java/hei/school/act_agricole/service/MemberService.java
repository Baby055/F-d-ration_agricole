package hei.school.act_agricole.service;

import hei.school.act_agricole.dto.request.CreateMemberRequest;
import hei.school.act_agricole.dto.response.MemberResponse;
import hei.school.act_agricole.entity.Member;
import hei.school.act_agricole.entity.Membership;
import hei.school.act_agricole.enums.Gender;
import hei.school.act_agricole.enums.MemberOccupation;
import hei.school.act_agricole.exception.BadRequestException;
import hei.school.act_agricole.exception.NotFoundException;
import hei.school.act_agricole.repository.CollectivityRepository;
import hei.school.act_agricole.repository.MemberRepository;
import hei.school.act_agricole.repository.MembershipRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository = new MemberRepository();
    private final CollectivityRepository collectivityRepository = new CollectivityRepository();
    private final MembershipRepository membershipRepository = new MembershipRepository();

    public List<MemberResponse> createMembers(List<CreateMemberRequest> requests) {
        try {
            List<MemberResponse> responses = new ArrayList<>();
            for (CreateMemberRequest req : requests) {
                responses.add(createOne(req));
            }
            return responses;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    private MemberResponse createOne(CreateMemberRequest req) throws SQLException {
        // 1. Check that the collectivity exists
        if (collectivityRepository.findById(req.getCollectivityIdentifier()).isEmpty()) {
            throw new NotFoundException("Collectivity not found: " + req.getCollectivityIdentifier());
        }

        // 2. Check referees: at least 2, all must be SENIOR
        if (req.getReferees() == null || req.getReferees().size() < 2) {
            throw new BadRequestException("At least 2 referees required");
        }
        List<Member> referees = memberRepository.findByIds(req.getReferees());
        if (referees.size() != req.getReferees().size()) {
            throw new NotFoundException("One or more referees not found");
        }
        for (Member ref : referees) {
            if (ref.getOccupation() != MemberOccupation.SENIOR) {
                throw new BadRequestException("Referee " + ref.getId() + " is not a SENIOR member");
            }
        }

        // 3. Rule: internal referees >= external referees
        String targetCollectivityId = req.getCollectivityIdentifier();
        long internal = 0, external = 0;
        for (Member ref : referees) {
            Optional<String> currentColl = membershipRepository.findCurrentCollectivityId(ref.getId());
            if (currentColl.isPresent() && currentColl.get().equals(targetCollectivityId)) {
                internal++;
            } else {
                external++;
            }
        }
        if (internal < external) {
            throw new BadRequestException("Number of referees from target collectivity must be at least as many as from other collectivities");
        }

        // 4. Payments
        if (!req.isRegistrationFeePaid() || !req.isMembershipDuesPaid()) {
            throw new BadRequestException("Registration fee and/or membership dues not paid");
        }

        // 5. Create new member (occupation = JUNIOR)
        Member newMember = new Member();
        newMember.setId(UUID.randomUUID().toString());
        newMember.setFirstName(req.getFirstName());
        newMember.setLastName(req.getLastName());
        newMember.setBirthDate(req.getBirthDate());
        newMember.setGender(Gender.valueOf(req.getGender()));
        newMember.setAddress(req.getAddress());
        newMember.setProfession(req.getProfession());
        newMember.setPhoneNumber(req.getPhoneNumber());
        newMember.setEmail(req.getEmail());
        newMember.setOccupation(MemberOccupation.JUNIOR);
        newMember.setFederationJoiningDate(LocalDate.now());
        memberRepository.save(newMember);

        // 6. Register membership
        Membership membership = new Membership();
        membership.setId(UUID.randomUUID().toString());
        membership.setMemberId(newMember.getId());
        membership.setCollectivityId(targetCollectivityId);
        membership.setStartDate(LocalDate.now());
        membership.setEndDate(null);
        membershipRepository.save(membership);

        // 7. Build response
        List<MemberResponse> refereeResponses = referees.stream()
                .map(this::toMemberResponseWithoutReferees)
                .collect(Collectors.toList());
        MemberResponse response = toMemberResponseWithoutReferees(newMember);
        response.setReferees(refereeResponses);
        return response;
    }

    private MemberResponse toMemberResponseWithoutReferees(Member m) {
        MemberResponse r = new MemberResponse();
        r.setId(m.getId());
        r.setFirstName(m.getFirstName());
        r.setLastName(m.getLastName());
        r.setBirthDate(m.getBirthDate());
        r.setGender(m.getGender().name());
        r.setAddress(m.getAddress());
        r.setProfession(m.getProfession());
        r.setPhoneNumber(m.getPhoneNumber());
        r.setEmail(m.getEmail());
        r.setOccupation(m.getOccupation().name());
        r.setReferees(List.of()); // avoid recursion
        return r;
    }
}

