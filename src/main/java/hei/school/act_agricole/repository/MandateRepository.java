package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.Mandate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MandateRepository {
    public void save(Mandate mandate) throws SQLException {
        String sql = "INSERT INTO mandate (id, member_id, collectivity_id, role, start_date, end_date) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, mandate.getId());
            stmt.setString(2, mandate.getMemberId());
            stmt.setString(3, mandate.getCollectivityId());
            stmt.setString(4, mandate.getRole());
            stmt.setDate(5, Date.valueOf(mandate.getStartDate()));
            stmt.setDate(6, Date.valueOf(mandate.getEndDate()));
            stmt.executeUpdate();
        }
    }

    public List<Mandate> findByCollectivityId(String collectivityId) throws SQLException {
        String sql = "SELECT id, member_id, collectivity_id, role, start_date, end_date FROM mandate WHERE collectivity_id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            List<Mandate> mandates = new ArrayList<>();
            while (rs.next()) {
                Mandate m = new Mandate();
                m.setId(rs.getString("id"));
                m.setMemberId(rs.getString("member_id"));
                m.setCollectivityId(rs.getString("collectivity_id"));
                m.setRole(rs.getString("role"));
                m.setStartDate(rs.getDate("start_date").toLocalDate());
                m.setEndDate(rs.getDate("end_date").toLocalDate());
                mandates.add(m);
            }
            return mandates;
        }
    }
}
