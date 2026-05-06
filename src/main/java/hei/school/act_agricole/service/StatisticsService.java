package hei.school.act_agricole.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import hei.school.act_agricole.dto.response.CollectivityLocalStatisticsResponse;
import hei.school.act_agricole.dto.response.CollectivityOverallStatisticsResponse;
import hei.school.act_agricole.entity.Collectivity;
import hei.school.act_agricole.exception.NotFoundException;
import hei.school.act_agricole.repository.CollectivityRepository;
import hei.school.act_agricole.repository.MemberPaymentRepository;
import hei.school.act_agricole.repository.MembershipFeeRepository;
import hei.school.act_agricole.repository.MembershipRepository;

public class StatisticsService {
    @Service
public class StatisticsService {

    private final CollectivityRepository collectivityRepo = new CollectivityRepository();
    private final MembershipFeeRepository feeRepo = new MembershipFeeRepository();
    private final MembershipRepository membershipRepo = new MembershipRepository();

    // GET /collectivites/{id}/statistics
    public List<CollectivityLocalStatisticsResponse> getLocalStatistics(String collectivityId, LocalDate from, LocalDate to) {
        try {
            if (collectivityRepo.findById(collectivityId).isEmpty())
                throw new NotFoundException("Collectivity not found");

            double totalActiveFees = feeRepo.getTotalActiveFeesAmountForCollectivity(collectivityId, to);
            List<Member> activeMembers = membershipRepo.findActiveMembersWithPaymentsByCollectivityId(collectivityId, from, to);
            List<CollectivityLocalStatisticsResponse> result = new ArrayList<>();
            for (Member member : activeMembers) {
                double paid = getTotalPaidForMember(member.getId(), from, to); // on pourrait aussi obtenir le montant déjà chargé, mais on simplifie
                double unpaid = Math.max(0, totalActiveFees - paid);
                MemberDescription desc = buildMemberDescription(member);
                CollectivityLocalStatisticsResponse stat = new CollectivityLocalStatisticsResponse();
                stat.setMemberDescription(desc);
                stat.setEarnedAmount(paid);
                stat.setUnpaidAmount(unpaid);
                result.add(stat);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }

    private double getTotalPaidForMember(String memberId, LocalDate from, LocalDate to) throws SQLException {
        MemberPaymentRepository paymentRepo = new MemberPaymentRepository();
        return paymentRepo.getTotalPaidByMemberAndPeriod(memberId, from, to);
    }

    private MemberDescription buildMemberDescription(Member m) {
        MemberDescription desc = new MemberDescription();
        desc.setId(m.getId());
        desc.setFirstName(m.getFirstName());
        desc.setLastName(m.getLastName());
        desc.setEmail(m.getEmail());
        desc.setOccupation(m.getOccupation().name());
        return desc;
    }

    // GET /collectivites/statistics
    public List<CollectivityOverallStatisticsResponse> getOverallStatistics(LocalDate from, LocalDate to) {
        try {
            List<Collectivity> collectivities = collectivityRepo.findAll();
            List<CollectivityOverallStatisticsResponse> result = new ArrayList<>();
            for (Collectivity coll : collectivities) {
                double totalActiveFees = feeRepo.getTotalActiveFeesAmountForCollectivity(coll.getId(), to);
                List<Member> activeMembers = membershipRepo.findActiveMembersWithPaymentsByCollectivityId(coll.getId(), from, to);
                int upToDateCount = 0;
                for (Member m : activeMembers) {
                    double paid = getTotalPaidForMember(m.getId(), from, to);
                    if (paid >= totalActiveFees) upToDateCount++;
                }
                double percentage = activeMembers.isEmpty() ? 0 : (upToDateCount * 100.0 / activeMembers.size());
                int newMembers = membershipRepo.countNewMembersByCollectivityId(coll.getId(), from, to);

                CollectivityInformation info = new CollectivityInformation();
                info.setName(coll.getName() != null ? coll.getName() : "");
                int num = 0;
                try { if (coll.getNumber() != null) num = Integer.parseInt(coll.getNumber()); } catch (Exception e) {}
                info.setNumber(num);

                CollectivityOverallStatisticsResponse stat = new CollectivityOverallStatisticsResponse();
                stat.setCollectivityInformation(info);
                stat.setNewMembersNumber(newMembers);
                stat.setOverallMemberCurrentDuePercentage(Math.round(percentage * 100.0) / 100.0);
                result.add(stat);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Database error", e);
        }
    }
}
}
