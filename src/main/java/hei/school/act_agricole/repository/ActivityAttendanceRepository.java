package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.ActivityAttendance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActivityAttendanceRepository {
    public void save(ActivityAttendance attendance) throws SQLException {
        String sql = "INSERT INTO activity_attendance (id, activity_id, member_id, present, excused, reason) VALUES (?,?,?,?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, attendance.getId());
            stmt.setString(2, attendance.getActivityId());
            stmt.setString(3, attendance.getMemberId());
            stmt.setBoolean(4, attendance.isPresent());
            stmt.setBoolean(5, attendance.isExcused());
            stmt.setString(6, attendance.getReason());
            stmt.executeUpdate();
        }
    }

    public List<ActivityAttendance> findByActivityId(String activityId) throws SQLException {
        String sql = "SELECT * FROM activity_attendance WHERE activity_id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, activityId);
            ResultSet rs = stmt.executeQuery();
            List<ActivityAttendance> list = new ArrayList<>();
            while (rs.next()) {
                ActivityAttendance a = new ActivityAttendance();
                a.setId(rs.getString("id"));
                a.setActivityId(rs.getString("activity_id"));
                a.setMemberId(rs.getString("member_id"));
                a.setPresent(rs.getBoolean("present"));
                a.setExcused(rs.getBoolean("excused"));
                a.setReason(rs.getString("reason"));
                list.add(a);
            }
            return list;
        }
    }

    public Optional<ActivityAttendance> findByActivityAndMember(String activityId, String memberId) throws SQLException {
        String sql = "SELECT * FROM activity_attendance WHERE activity_id = ? AND member_id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, activityId);
            stmt.setString(2, memberId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ActivityAttendance a = new ActivityAttendance();
                a.setId(rs.getString("id"));
                a.setActivityId(rs.getString("activity_id"));
                a.setMemberId(rs.getString("member_id"));
                a.setPresent(rs.getBoolean("present"));
                a.setExcused(rs.getBoolean("excused"));
                a.setReason(rs.getString("reason"));
                return Optional.of(a);
            }
            return Optional.empty();
        }
    }
}
