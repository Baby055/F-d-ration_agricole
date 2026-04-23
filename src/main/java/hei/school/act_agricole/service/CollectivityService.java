package hei.school.act_agricole.service;

import hei.school.act_agricole.dto.request.CreateCollectivityRequest;
import hei.school.act_agricole.dto.request.CreateCollectivityStructureRequest;
import hei.school.act_agricole.dto.response.CollectivityResponse;
import hei.school.act_agricole.dto.response.CollectivityStructureResponse;
import hei.school.act_agricole.dto.response.MemberResponse;
import hei.school.act_agricole.entity.*;
import hei.school.act_agricole.exception.BadRequestException;
import hei.school.act_agricole.exception.ConflictException;
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
        if (!req.isFederationApproval()) {
            throw new BadRequestException("Federation approval is required");
        }

        Set<String> allMemberIds = new HashSet<>(req.getMembers());
        CreateCollectivityStructureRequest struct = req.getStructure();
        allMemberIds.add(struct.getPresident());
        allMemberIds.add(struct.getVicePresident());
        allMemberIds.add(struct.getTreasurer());
        allMemberIds.add(struct.getSecretary());

        List<Member> members = memberRepository.findByIds(new ArrayList<>(allMemberIds));
        if (members.size() != allMemberIds.size()) {
            throw new NotFoundException("One or more members not found");
        }

        LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
        for (Member m : members) {
            if (m.getFederationJoiningDate().isAfter(sixMonthsAgo)) {
                throw new BadRequestException("Member " + m.getId() + " has less than 6 months seniority");
            }
        }

        if (allMemberIds.size() < 10) {
            throw new BadRequestException("At least 10 members required");
        }

        Collectivity collectivity = new Collectivity();
        collectivity.setId(UUID.randomUUID().toString());
        collectivity.setLocation(req.getLocation());
        collectivity.setFederationApproval(true);
        collectivityRepository.save(collectivity);

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

        // Removed redundant variable mandateStart, use today directly
        LocalDate mandateEnd = today.plusYears(1);
        saveMandate(struct.getPresident(), collectivity.getId(), "PRESIDENT", today, mandateEnd);
        saveMandate(struct.getVicePresident(), collectivity.getId(), "VICE_PRESIDENT", today, mandateEnd);
        saveMandate(struct.getTreasurer(), collectivity.getId(), "TREASURER", today, mandateEnd);
        saveMandate(struct.getSecretary(), collectivity.getId(), "SECRETARY", today, mandateEnd);

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

    public CollectivityResponse assignIdentification(String collectivityId, String number, String name) {
        try {
            Optional<Collectivity> opt = collectivityRepository.findById(collectivityId);
            if (opt.isEmpty()) {
                throw new NotFoundException("Collectivity not found: " + collectivityId);
            }
            Collectivity existing = opt.get();

            if (existing.getNumber() != null || existing.getName() != null) {
                throw new ConflictException("Collectivity already has a number or name assigned and cannot be changed");
            }

            if (collectivityRepository.existsByNumber(number)) {
                throw new BadRequestException("Number already used by another collectivity");
            }
            if (collectivityRepository.existsByName(name)) {
                throw new BadRequestException("Name already used by another collectivity");
            }

            collectivityRepository.updateNumberAndName(collectivityId, number, name);

            return getCollectivityWithDetails(collectivityId);

        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    private CollectivityResponse getCollectivityWithDetails(String collectivityId) throws SQLException {
        Optional<Collectivity> opt = collectivityRepository.findById(collectivityId);
        if (opt.isEmpty()) throw new NotFoundException("Collectivity not found");
        Collectivity coll = opt.get();

        List<Member> members = membershipRepository.findMembersByCollectivityId(collectivityId);
        Map<String, MemberResponse> memberMap = members.stream()
                .collect(Collectors.toMap(Member::getId, this::toMemberResponse));

        List<Mandate> mandates = mandateRepository.findByCollectivityId(collectivityId);
        CollectivityStructureResponse structResp = buildStructureFromMandates(mandates, memberMap);

        CollectivityResponse response = new CollectivityResponse();
        response.setId(coll.getId());
        response.setLocation(coll.getLocation());
        response.setStructure(structResp);
        response.setMembers(new ArrayList<>(memberMap.values()));
        response.setNumber(coll.getNumber());
        response.setName(coll.getName());
        return response;
    }

    private CollectivityStructureResponse buildStructureFromMandates(List<Mandate> mandates, Map<String, MemberResponse> memberMap) {
        CollectivityStructureResponse struct = new CollectivityStructureResponse();
        for (Mandate mandate : mandates) {
            MemberResponse member = memberMap.get(mandate.getMemberId());
            if (member == null) continue;
            switch (mandate.getRole()) {
                case "PRESIDENT":
                    struct.setPresident(member);
                    break;
                case "VICE_PRESIDENT":
                    struct.setVicePresident(member);
                    break;
                case "TREASURER":
                    struct.setTreasurer(member);
                    break;
                case "SECRETARY":
                    struct.setSecretary(member);
                    break;
            }
        }
        return struct;
    }

    public CollectivityResponse getCollectivityById(String id) {
    try {
        return getCollectivityWithDetails(id);
    } catch (SQLException e) {
        throw new RuntimeException("Database error", e);
    }
}

public List<FinancialAccount> getFinancialAccounts(String collectivityId, LocalDate atDate) {
    try {
        if (collectivityRepo.findById(collectivityId).isEmpty())
            throw new NotFoundException("Collectivity not found");
        List<FinancialAccount> accounts = accountRepo.findByCollectivityId(collectivityId);
        for (FinancialAccount acc : accounts) {
            double balance = accountRepo.getBalanceAtDate(acc.getId(), atDate);
            acc.setAmount(balance); // on remplace le solde actuel par le solde à la date demandée
        }
        return accounts;
    } catch (SQLException e) {
        throw new RuntimeException("Database error", e);
    }
}
}