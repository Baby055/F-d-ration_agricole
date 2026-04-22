package hei.school.act_agricole.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.Collectivity;

    @Repository
public class CollectivityRepository {

    public void save(Collectivity collectivity) throws SQLException {
        String sql = "INSERT INTO collectivity (id, location, federation_approval) VALUES (?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivity.getId());
            stmt.setString(2, collectivity.getLocation());
            stmt.setBoolean(3, collectivity.isFederationApproval());
            stmt.executeUpdate();
        }
    }

    public Optional<Collectivity> findById(String id) throws SQLException {
        String sql = "SELECT * FROM collectivity WHERE id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Collectivity c = new Collectivity();
                c.setId(rs.getString("id"));
                c.setLocation(rs.getString("location"));
                c.setFederationApproval(rs.getBoolean("federation_approval"));
                return Optional.of(c);
            }
            return Optional.empty();
        }
    }

        public boolean existsByNumber(String number) throws SQLException {
            String sql = "SELECT 1 FROM collectivity WHERE unique_number = ?";
            try (Connection conn = DataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, number);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        }

        public boolean existsByName(String name) throws SQLException {
            String sql = "SELECT 1 FROM collectivity WHERE unique_name = ?";
            try (Connection conn = DataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        }

        public void updateNumberAndName(String collectivityId, String number, String name) throws SQLException {
            String sql = "UPDATE collectivity SET unique_number = ?, unique_name = ? WHERE id = ?";
            try (Connection conn = DataSource.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, number);
                stmt.setString(2, name);
                stmt.setString(3, collectivityId);
                int updated = stmt.executeUpdate();
                if (updated == 0) throw new SQLException("Collectivity not found");
            }
        }
}
