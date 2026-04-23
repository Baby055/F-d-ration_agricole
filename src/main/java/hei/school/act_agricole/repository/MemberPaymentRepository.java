package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.MemberPayment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class MemberPaymentRepository {
    public void save(MemberPayment payment) throws SQLException {
        String sql = "INSERT INTO member_payment (id, member_id, membership_fee_id, amount, payment_mode, transaction_id, creation_date) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, payment.getId());
            stmt.setString(2, payment.getMemberId());
            stmt.setString(3, payment.getMembershipFeeId());
            stmt.setDouble(4, payment.getAmount());
            stmt.setString(5, payment.getPaymentMode().name());
            stmt.setString(6, payment.getTransactionId());
            stmt.setDate(7, Date.valueOf(payment.getCreationDate()));
            stmt.executeUpdate();
        }
    }
}