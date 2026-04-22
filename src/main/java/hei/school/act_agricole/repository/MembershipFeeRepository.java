package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.MembershipFee;
import hei.school.act_agricole.enums.ActivityStatus;
import hei.school.act_agricole.enums.Frequency;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MembershipFeeRepository {

    public void save(MembershipFee fee) throws SQLException {
        String sql = "INSERT INTO membership_fee (id, collectivity_id, eligible_from, frequency, amount, label, status) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fee.getId());
            stmt.setString(2, fee.getCollectivityId());
            stmt.setDate(3, Date.valueOf(fee.getEligibleFrom()));
            stmt.setString(4, fee.getFrequency().name());
            stmt.setDouble(5, fee.getAmount());
            stmt.setString(6, fee.getLabel());
            stmt.setString(7, fee.getStatus().name());
            stmt.executeUpdate();
        }
    }

    public List<MembershipFee> findByCollectivityId(String collectivityId) throws SQLException {
        String sql = "SELECT * FROM membership_fee WHERE collectivity_id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            List<MembershipFee> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public Optional<MembershipFee> findById(String id) throws SQLException {
        String sql = "SELECT * FROM membership_fee WHERE id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return Optional.of(map(rs));
            return Optional.empty();
        }
    }

    private MembershipFee map(ResultSet rs) throws SQLException {
        MembershipFee f = new MembershipFee();
        f.setId(rs.getString("id"));
        f.setCollectivityId(rs.getString("collectivity_id"));
        f.setEligibleFrom(rs.getDate("eligible_from").toLocalDate());
        f.setFrequency(Frequency.valueOf(rs.getString("frequency")));
        f.setAmount(rs.getDouble("amount"));
        f.setLabel(rs.getString("label"));
        f.setStatus(ActivityStatus.valueOf(rs.getString("status")));
        return f;
    }
}
