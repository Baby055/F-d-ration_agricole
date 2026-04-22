package hei.school.act_agricole.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import hei.school.act_agricole.config.DataSource;
import org.springframework.stereotype.Repository;

import hei.school.act_agricole.entity.FinancialAccount;


@Repository
public class FinancialAccountRepository {

    public Optional<FinancialAccount> findById(String id) throws SQLException {
        String sql = "SELECT * FROM financial_account WHERE id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                FinancialAccount a = new FinancialAccount();
                a.setId(rs.getString("id"));
                a.setAccountType(rs.getString("account_type"));
                a.setHolderName(rs.getString("holder_name"));
                a.setAmount(rs.getDouble("amount"));
                a.setMobileService(rs.getString("mobile_service"));
                a.setMobileNumber(rs.getString("mobile_number"));
                a.setBankName(rs.getString("bank_name"));
                a.setBankCode(rs.getString("bank_code"));
                a.setBranchCode(rs.getString("branch_code"));
                a.setAccountNumber(rs.getString("account_number"));
                a.setRibKey(rs.getString("rib_key"));
                return Optional.of(a);
            }
            return Optional.empty();
        }
    }
}

