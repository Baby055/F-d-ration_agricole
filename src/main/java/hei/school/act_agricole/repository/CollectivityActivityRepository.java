package hei.school.act_agricole.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.CollectivityActivity;

public class CollectivityActivityRepository {
    public void save(CollectivityActivity activity) throws SQLException {
        String sql = "INSERT INTO collectivity_activity (id, collectivity_id, title, description, activity_date, mandatory) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, activity.getId());
            stmt.setString(2, activity.getCollectivityId());
            stmt.setString(3, activity.getTitle());
            stmt.setString(4, activity.getDescription());
            stmt.setDate(5, java.sql.Date.valueOf(activity.getActivityDate()));
            stmt.setBoolean(6, activity.isMandatory());
            stmt.executeUpdate();
        }
    }

    public List<CollectivityActivity> findByCollectivityId(String collectivityId) throws SQLException {
        String sql = "SELECT * FROM collectivity_activity WHERE collectivity_id = ? ORDER BY activity_date";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            List<CollectivityActivity> list = new ArrayList<>();
            while (rs.next()) {
                list.add(map(rs));
            }
            return list;
        }
    }

    public Optional<CollectivityActivity> findById(String activityId) throws SQLException {
        String sql = "SELECT * FROM collectivity_activity WHERE id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, activityId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return Optional.of(map(rs));
            return Optional.empty();
        }
    }

    private CollectivityActivity map(ResultSet rs) throws SQLException {
        CollectivityActivity a = new CollectivityActivity();
        a.setId(rs.getString("id"));
        a.setCollectivityId(rs.getString("collectivity_id"));
        a.setTitle(rs.getString("title"));
        a.setDescription(rs.getString("description"));
        a.setActivityDate(rs.getDate("activity_date").toLocalDate());
        a.setMandatory(rs.getBoolean("mandatory"));
        return a;
    }
}
