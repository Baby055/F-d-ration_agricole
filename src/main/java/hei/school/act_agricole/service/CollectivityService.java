package hei.school.act_agricole.service;

import hei.school.act_agricole.dto.request.CreateCollectivityRequest;
import hei.school.act_agricole.dto.request.CreateCollectivityStructureRequest;
import hei.school.act_agricole.dto.response.CollectivityResponse;
import hei.school.act_agricole.dto.response.CollectivityStructureResponse;
import hei.school.act_agricole.dto.response.MemberResponse;
import hei.school.act_agricole.entity.*;
import hei.school.act_agricole.exception.BadRequestException;
import hei.school.act_agricole.exception.NotFoundException;
import hei.school.act_agricole.repository.*;
import org.springframework.stereotype.Service;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CollectivityService {

    private final MemberRepository memberRepository = new MemberRepository();
    private final CollectivityRepository collectivityRepository = new CollectivityRepository();
    private final MembershipRepository membershipRepository = new MembershipRepository();
    private final MandateRepository mandateRepository = new MandateRepository();

   public List<CollectivityResponse> createCollectivities(List<CreateCollectivityRequest> requests) {
        try {
            List<CollectivityResponse> responses = new ArrayList<>();
            for (CreateCollectivityRequest req : requests) {
                responses.add(createOne(req));
            }
            return responses;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    private CollectivityResponse createOne(CreateCollectivityRequest req) throws SQLException {
        // 1. Federation approval
        if (!req.isFederationApproval()) {
            throw new BadRequestException("Federation approval is required");
        }

        // 2. Collect all member IDs (members list + structure)
        Set<String> allMemberIds = new HashSet<>(req.getMembers());
        CreateCollectivityStructureRequest struct = req.getStructure();
        allMemberIds.add(struct.getPresident());
        allMemberIds.add(struct.getVicePresident());
        allMemberIds.add(struct.getTreasurer());
        allMemberIds.add(struct.getSecretary());

        // 3. Fetch members
        List<Member> members = memberRepository.findByIds(new ArrayList<>(allMemberIds));
        if (members.size() != allMemberIds.size()) {
            throw new NotFoundException("One or more members not found");
        }

        // 4. Seniority >= 6 months
        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        for (Member m : members) {
            if (m.getFederationJoiningDate().isAfter(sixMonthsAgo)) {
                throw new BadRequestException("Member " + m.getId() + " has less than 6 months seniority");
            }
        }

        // 5. Minimum 10 members
        if (allMemberIds.size() < 10) {
            throw new BadRequestException("At least 10 members required");
        }

        // 6. Create collectivity
        Collectivity collectivity = new Collectivity();
        collectivity.setId(UUID.randomUUID().toString());
        collectivity.setLocation(req.getLocation());
        collectivity.setFederationApproval(true);
        collectivityRepository.save(collectivity);

        // 7. Register memberships
        LocalDate today = LocalDate.now();
        for (String memberId : allMemberIds) {
            Membership membership = new Membership();
            membership.setId(UUID.randomUUID().toString());
            membership.setMemberId(memberId);
            membership.setCollectivityId(collectivity.getId());
            membership.setStartDate(today);
            membership.setEndDate(null);
            membershipRepository.save(membership);
        }

        // 8. Register mandates (1 year)
        LocalDate mandateStart = today;
        LocalDate mandateEnd = mandateStart.plusYears(1);
        saveMandate(struct.getPresident(), collectivity.getId(), "PRESIDENT", mandateStart, mandateEnd);
        saveMandate(struct.getVicePresident(), collectivity.getId(), "VICE_PRESIDENT", mandateStart, mandateEnd);
        saveMandate(struct.getTreasurer(), collectivity.getId(), "TREASURER", mandateStart, mandateEnd);
        saveMandate(struct.getSecretary(), collectivity.getId(), "SECRETARY", mandateStart, mandateEnd);

        // 9. Build response
        Map<String, MemberResponse> memberResponseMap = members.stream()
                .collect(Collectors.toMap(Member::getId, this::toMemberResponse));

        CollectivityStructureResponse structResp = new CollectivityStructureResponse();
        structResp.setPresident(memberResponseMap.get(struct.getPresident()));
        structResp.setVicePresident(memberResponseMap.get(struct.getVicePresident()));
        structResp.setTreasurer(memberResponseMap.get(struct.getTreasurer()));
        structResp.setSecretary(memberResponseMap.get(struct.getSecretary()));

        CollectivityResponse response = new CollectivityResponse();
        response.setId(collectivity.getId());
        response.setLocation(collectivity.getLocation());
        response.setStructure(structResp);
        response.setMembers(new ArrayList<>(memberResponseMap.values()));
        return response;
    }

    private void saveMandate(String memberId, String collectivityId, String role, LocalDate start, LocalDate end) throws SQLException {
        Mandate mandate = new Mandate();
        mandate.setId(UUID.randomUUID().toString());
        mandate.setMemberId(memberId);
        mandate.setCollectivityId(collectivityId);
        mandate.setRole(role);
        mandate.setStartDate(start);
        mandate.setEndDate(end);
        mandateRepository.save(mandate);
    }

    private MemberResponse toMemberResponse(Member m) {
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
        r.setReferees(List.of()); // empty for collectivity members
        return r;
    }
}
