package hei.school.act_agricole.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import hei.school.act_agricole.dto.request.CreateMembershipFeeRequest;  // ← correction
import hei.school.act_agricole.exception.BadRequestException;
import org.springframework.stereotype.Service;

import hei.school.act_agricole.dto.response.MembershipFeeResponse;
import hei.school.act_agricole.entity.MembershipFee;
import hei.school.act_agricole.enums.ActivityStatus;
import hei.school.act_agricole.exception.NotFoundException;
import hei.school.act_agricole.repository.CollectivityRepository;
import hei.school.act_agricole.repository.MembershipFeeRepository;

@Service
public class MembershipFeeService {

    private final MembershipFeeRepository feeRepo = new MembershipFeeRepository();
    private final CollectivityRepository collectivityRepo = new CollectivityRepository();

    public List<MembershipFeeResponse> getMembershipFees(String collectivityId) {
        try {
            if (collectivityRepo.findById(collectivityId).isEmpty())
                throw new NotFoundException("Collectivity not found");
            List<MembershipFee> fees = feeRepo.findByCollectivityId(collectivityId);
            return fees.stream().map(this::toResponse).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("DB error", e);
        }
    }

    public List<MembershipFeeResponse> createMembershipFees(String collectivityId, List<CreateMembershipFeeRequest> requests) {
        try {
            if (collectivityRepo.findById(collectivityId).isEmpty())
                throw new NotFoundException("Collectivity not found");
            List<MembershipFeeResponse> responses = new ArrayList<>();
            for (CreateMembershipFeeRequest req : requests) {  // ← correction
                if (req.getAmount() < 0) throw new BadRequestException("Amount cannot be negative");
                MembershipFee fee = new MembershipFee();
                fee.setId(UUID.randomUUID().toString());
                fee.setCollectivityId(collectivityId);
                fee.setEligibleFrom(req.getEligibleFrom());
                fee.setFrequency(req.getFrequency());
                fee.setAmount(req.getAmount());
                fee.setLabel(req.getLabel());
                fee.setStatus(ActivityStatus.ACTIVE);
                feeRepo.save(fee);
                responses.add(toResponse(fee));
            }
            return responses;
        } catch (SQLException e) {
            throw new RuntimeException("DB error", e);
        }
    }

    private MembershipFeeResponse toResponse(MembershipFee fee) {
        MembershipFeeResponse r = new MembershipFeeResponse();
        r.setId(fee.getId());
        r.setEligibleFrom(fee.getEligibleFrom());
        r.setFrequency(fee.getFrequency());
        r.setAmount(fee.getAmount());
        r.setLabel(fee.getLabel());
        r.setStatus(fee.getStatus());
        return r;
    }
}