package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.Membership;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipRepository {

    public void save(Membership membership) throws SQLException {
        String sql = "INSERT INTO membership (id, member_id, collectivity_id, start_date, end_date) VALUES (?,?,?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, membership.getId());
            stmt.setString(2, membership.getMemberId());
            stmt.setString(3, membership.getCollectivityId());
            stmt.setDate(4, Date.valueOf(membership.getStartDate()));
            if (membership.getEndDate() != null) stmt.setDate(5, Date.valueOf(membership.getEndDate()));
            else stmt.setNull(5, Types.DATE);
            stmt.executeUpdate();
        }
    }

    public Optional<String> findCurrentCollectivityId(String memberId) throws SQLException {
        String sql = "SELECT collectivity_id FROM membership WHERE member_id = ? AND end_date IS NULL";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, memberId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return Optional.of(rs.getString("collectivity_id"));
            return Optional.empty();
        }
    }
}
