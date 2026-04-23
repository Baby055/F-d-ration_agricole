package hei.school.act_agricole.repository;

import hei.school.act_agricole.config.DataSource;
import hei.school.act_agricole.entity.Member;
import hei.school.act_agricole.enums.Gender;
import hei.school.act_agricole.enums.MemberOccupation;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class MemberRepository {

    public Optional<Member> findById(String id) throws SQLException {
        String sql = "SELECT * FROM member WHERE id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return Optional.of(map(rs));
            return Optional.empty();
        }
    }

    public List<Member> findByIds(List<String> ids) throws SQLException {
        if (ids == null || ids.isEmpty()) return List.of();
        String placeholders = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = "SELECT * FROM member WHERE id IN (" + placeholders + ")";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < ids.size(); i++) stmt.setString(i+1, ids.get(i));
            ResultSet rs = stmt.executeQuery();
            List<Member> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public void save(Member member) throws SQLException {
        String sql = "INSERT INTO member (id, first_name, last_name, birth_date, gender, address, profession, phone_number, email, occupation, federation_joining_date) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, member.getId());
            stmt.setString(2, member.getFirstName());
            stmt.setString(3, member.getLastName());
            stmt.setDate(4, Date.valueOf(member.getBirthDate()));
            stmt.setString(5, member.getGender().name());
            stmt.setString(6, member.getAddress());
            stmt.setString(7, member.getProfession());
            stmt.setString(8, member.getPhoneNumber());
            stmt.setString(9, member.getEmail());
            stmt.setString(10, member.getOccupation().name());
            stmt.setDate(11, Date.valueOf(member.getFederationJoiningDate()));
            stmt.executeUpdate();
        }
    }

    private Member map(ResultSet rs) throws SQLException {
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
        return m;
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