package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.Mandate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
