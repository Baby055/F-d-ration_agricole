package hei.school.act_agricole.service;

import hei.school.act_agricole.dto.response.CollectivityTransactionResponse;
import hei.school.act_agricole.dto.response.MemberPaymentResponse;
import hei.school.act_agricole.dto.response.MemberResponse;
import hei.school.act_agricole.entity.*;
import hei.school.act_agricole.enums.ActivityStatus;
import hei.school.act_agricole.exception.BadRequestException;
import hei.school.act_agricole.exception.NotFoundException;
import hei.school.act_agricole.repository.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final CollectivityTransactionRepository transactionRepo = new CollectivityTransactionRepository();
    private final MemberPaymentRepository paymentRepo = new MemberPaymentRepository();
    private final MembershipFeeRepository feeRepo = new MembershipFeeRepository();
    private final MemberRepository memberRepo = new MemberRepository();
    private final CollectivityRepository collectivityRepo = new CollectivityRepository();
    private final FinancialAccountRepository accountRepo = new FinancialAccountRepository();

    // GET /collectivities/{id}/transactions
    public List<CollectivityTransactionResponse> getTransactions(String collectivityId, LocalDate from, LocalDate to) {
        try {
            if (collectivityRepo.findById(collectivityId).isEmpty())
                throw new NotFoundException("Collectivity not found");
            List<CollectivityTransaction> transactions = transactionRepo.findByCollectivityIdAndDateRange(collectivityId, from, to);
            return transactions.stream().map(this::toTransactionResponse).collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException("DB error", e);
        }
    }

    // POST /members/{id}/payments
    public List<MemberPaymentResponse> createPayments(String memberId, List<CreateMemberPayment> requests) {
        try {
            // Vérifier que le membre existe
            if (memberRepo.findById(memberId).isEmpty())
                throw new NotFoundException("Member not found");
            // Récupérer la collectivité du membre (via membership actuel)
            String collectivityId = getCurrentCollectivityId(memberId)
                    .orElseThrow(() -> new BadRequestException("Member not attached to any collectivity"));

            List<MemberPaymentResponse> responses = new ArrayList<>();
            for (CreateMemberPayment req : requests) {
                // Vérifier que le frais existe et est actif
                MembershipFee fee = feeRepo.findById(req.getMembershipFeeIdentifier())
                        .orElseThrow(() -> new NotFoundException("Membership fee not found"));
                if (fee.getStatus() != ActivityStatus.ACTIVE)
                    throw new BadRequestException("Membership fee is not active");
                // Vérifier que le compte crédité existe
                FinancialAccount account = accountRepo.findById(req.getAccountCreditedIdentifier())
                        .orElseThrow(() -> new NotFoundException("Financial account not found"));

                // Créer la transaction
                CollectivityTransaction transaction = new CollectivityTransaction();
                transaction.setId(UUID.randomUUID().toString());
                transaction.setCollectivityId(collectivityId);
                transaction.setCreationDate(LocalDate.now());
                transaction.setAmount(req.getAmount());
                transaction.setPaymentMode(req.getPaymentMode());
                transaction.setAccountCreditedId(req.getAccountCreditedIdentifier());
                transaction.setMemberDebitedId(memberId);
                transactionRepo.save(transaction);

                // Créer le paiement membre
                MemberPayment payment = new MemberPayment();
                payment.setId(UUID.randomUUID().toString());
                payment.setMemberId(memberId);
                payment.setMembershipFeeId(req.getMembershipFeeIdentifier());
                payment.setAmount(req.getAmount());
                payment.setPaymentMode(req.getPaymentMode());
                payment.setTransactionId(transaction.getId());
                payment.setCreationDate(LocalDate.now());
                paymentRepo.save(payment);

                responses.add(toPaymentResponse(payment, account));
            }
            return responses;
        } catch (SQLException e) {
            throw new RuntimeException("DB error", e);
        }
    }

    private Optional<String> getCurrentCollectivityId(String memberId) throws SQLException {
        // À implémenter dans MembershipRepository
        // SELECT collectivity_id FROM membership WHERE member_id = ? AND end_date IS NULL
        // On suppose que MembershipRepository a une méthode findCurrentCollectivityId
        return new MembershipRepository().findCurrentCollectivityId(memberId);
    }

    private CollectivityTransactionResponse toTransactionResponse(CollectivityTransaction t) {
        try {
            Member member = memberRepo.findById(t.getMemberDebitedId()).orElse(null);
            MemberResponse memberResp = member != null ? toMemberResponse(member) : null;
            FinancialAccount account = accountRepo.findById(t.getAccountCreditedId()).orElse(null);
            CollectivityTransactionResponse r = new CollectivityTransactionResponse();
            r.setId(t.getId());
            r.setCreationDate(t.getCreationDate());
            r.setAmount(t.getAmount());
            r.setPaymentMode(t.getPaymentMode());
            r.setAccountCredited(account);
            r.setMemberDebited(memberResp);
            return r;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    private MemberPaymentResponse toPaymentResponse(MemberPayment p, FinancialAccount account) {
        MemberPaymentResponse r = new MemberPaymentResponse();
        r.setId(p.getId());
        r.setAmount(p.getAmount());
        r.setPaymentMode(p.getPaymentMode());
        r.setAccountCredited(account);
        r.setCreationDate(p.getCreationDate());
        return r;
    }

    private MemberResponse toMemberResponse(Member m) {
        MemberResponse r = new MemberResponse();
        r.setId(m.getId());
        r.setFirstName(m.getFirstName());
        r.setLastName(m.getLastName());
        // ... autres champs
        return r;
    }
}
