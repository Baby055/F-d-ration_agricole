package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.CollectivityTransaction;
import hei.school.act_agricole.enums.PaymentMode;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CollectivityTransactionRepository {

    public void save(CollectivityTransaction transaction) throws SQLException {
        String sql = "INSERT INTO collectivity_transaction (id, collectivity_id, creation_date, amount, payment_mode, account_credited_id, member_debited_id) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaction.getId());
            stmt.setString(2, transaction.getCollectivityId());
            stmt.setDate(3, Date.valueOf(transaction.getCreationDate()));
            stmt.setDouble(4, transaction.getAmount());
            stmt.setString(5, transaction.getPaymentMode().name());
            stmt.setString(6, transaction.getAccountCreditedId());
            stmt.setString(7, transaction.getMemberDebitedId());
            stmt.executeUpdate();
        }
    }

    public List<CollectivityTransaction> findByCollectivityIdAndDateRange(String collectivityId, LocalDate from, LocalDate to) throws SQLException {
        String sql = "SELECT * FROM collectivity_transaction WHERE collectivity_id = ? AND creation_date BETWEEN ? AND ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(from));
            stmt.setDate(3, Date.valueOf(to));
            ResultSet rs = stmt.executeQuery();
            List<CollectivityTransaction> list = new ArrayList<>();
            while (rs.next()) {
                CollectivityTransaction t = new CollectivityTransaction();
                t.setId(rs.getString("id"));
                t.setCollectivityId(rs.getString("collectivity_id"));
                t.setCreationDate(rs.getDate("creation_date").toLocalDate());
                t.setAmount(rs.getDouble("amount"));
                t.setPaymentMode(PaymentMode.valueOf(rs.getString("payment_mode")));
                t.setAccountCreditedId(rs.getString("account_credited_id"));
                t.setMemberDebitedId(rs.getString("member_debited_id"));
                list.add(t);
            }
            return list;
        }
    }
}
