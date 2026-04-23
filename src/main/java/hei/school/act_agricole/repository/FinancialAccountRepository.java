package hei.school.act_agricole.repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import hei.school.act_agricole.config.DataSource;
import org.springframework.stereotype.Repository;

import hei.school.act_agricole.entity.FinancialAccount;

import static sun.awt.image.MultiResolutionCachedImage.map;


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

    public List<FinancialAccount> findByCollectivityId(String collectivityId) throws SQLException {
        String sql = "SELECT * FROM financial_account WHERE collectivity_id = ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, collectivityId);
            ResultSet rs = stmt.executeQuery();
            List<FinancialAccount> list = new ArrayList<>();
            while (rs.next()) {
                list.add(map(rs));
            }
            return list;
        }
    }

    public double getBalanceAtDate(String accountId, LocalDate date) throws SQLException {
        String sql = "SELECT COALESCE(SUM(amount), 0) FROM collectivity_transaction WHERE account_credited_id = ? AND creation_date <= ?";
        try (Connection conn = DataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountId);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return 0;
        }
    }

    private FinancialAccount map(ResultSet rs) throws SQLException {
        FinancialAccount a = new FinancialAccount();
        a.setId(rs.getString("id"));
        a.setCollectivityId(rs.getString("collectivity_id")); // à ajouter dans l'entité
        a.setAccountType(rs.getString("account_type"));
        a.setHolderName(rs.getString("holder_name"));
        a.setAmount(rs.getDouble("amount")); // solde actuel (peut être utilisé pour le solde à aujourd'hui)
        a.setMobileService(rs.getString("mobile_service"));
        a.setMobileNumber(rs.getString("mobile_number"));
        a.setBankName(rs.getString("bank_name"));
        a.setBankCode(rs.getString("bank_code"));
        a.setBranchCode(rs.getString("branch_code"));
        a.setAccountNumber(rs.getString("account_number"));
        a.setRibKey(rs.getString("rib_key"));
        return a;
    }
}

