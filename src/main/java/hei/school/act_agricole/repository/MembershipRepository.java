package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.Member;
import hei.school.act_agricole.entity.Membership;
import hei.school.act_agricole.enums.Gender;
import hei.school.act_agricole.enums.MemberOccupation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    public List<Member> findMembersByCollectivityId(String collectivityId) throws SQLException {
        String sql = "SELECT m.* FROM member m " +
                     "JOIN membership ms ON m.id = ms.member_id " +
                     "WHERE ms.collectivity_id = ? AND ms.end_date IS NULL";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getString("id"));
                m.setFirstName(rs.getString("first_name"));
                m.setLastName(rs.getString("last_name"));
                m.setBirthDate(rs.getDate("birth_date").toLocalDate());
                m.setGender(Gender.valueOf(rs.getString("gender")));
                m.setAddress(rs.getString("address"));
                m.setProfession(rs.getString("profession"));
                m.setPhoneNumber(rs.getString("phone_number"));
                m.setEmail(rs.getString("email"));
                m.setOccupation(MemberOccupation.valueOf(rs.getString("occupation")));
                m.setFederationJoiningDate(rs.getDate("federation_joining_date").toLocalDate());
                members.add(m);
            }
            return members;
        }
    }

    public List<Member> findActiveMembersWithPaymentsByCollectivityId(String collectivityId, LocalDate from, LocalDate to) throws SQLException {
        String sql =
                "SELECT m.id, m.first_name, m.last_name, m.email, m.occupation, COALESCE(SUM(mp.amount), 0) AS total_paid " +
                        "FROM member m " +
                        "JOIN membership ms ON m.id = ms.member_id " +
                        "LEFT JOIN member_payment mp ON m.id = mp.member_id AND mp.creation_date BETWEEN ? AND ? " +
                        "WHERE ms.collectivity_id = ? AND ms.start_date <= ? AND (ms.end_date IS NULL OR ms.end_date >= ?) " +
                        "GROUP BY m.id";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(from));
            stmt.setDate(2, Date.valueOf(to));
            stmt.setString(3, collectivityId);
            stmt.setDate(4, Date.valueOf(to));
            stmt.setDate(5, Date.valueOf(from));
            ResultSet rs = stmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member m = new Member();
                m.setId(rs.getString("id"));
                m.setFirstName(rs.getString("first_name"));
                m.setLastName(rs.getString("last_name"));
                m.setEmail(rs.getString("email"));
                m.setOccupation(MemberOccupation.valueOf(rs.getString("occupation")));
                // On ne stocke pas le total_paid ici, mais on peut l'ajouter dans un attribut temporaire si besoin
                members.add(m);
            }
            return members;
        }
    }

    public int countNewMembersByCollectivityId(String collectivityId, LocalDate from, LocalDate to) throws SQLException {
        String sql = "SELECT COUNT(*) FROM membership WHERE collectivity_id = ? AND start_date BETWEEN ? AND ?";
        try (Connection conn = DataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            stmt.setDate(2, Date.valueOf(from));
            stmt.setDate(3, Date.valueOf(to));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }
}