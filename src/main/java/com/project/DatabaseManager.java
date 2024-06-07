package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/poo-final-project";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public void saveUser(User user, String timestamp) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO users (Numero_Securite_Sociale, Nom, Prenom, Date_Naissance, Numero_Telephone, E_Mail, ID_Remboursement, Code_Soin, Montant_Remboursement, Timestamp_fichier) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getNumeroSecuriteSociale());
                statement.setString(2, user.getNom());
                statement.setString(3, user.getPrenom());
                // Convertir la date de naissance de String à java.sql.Date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(user.getDateNaissance(), formatter);
                java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                statement.setDate(4, sqlDate);
                statement.setString(5, user.getNumeroTelephone());
                statement.setString(6, user.getEmail());
                statement.setInt(7, user.getIdRemboursement());
                statement.setString(8, user.getCodeSoin());
                statement.setDouble(9, user.getMontantRemboursement());
                // Convertir le timestamp de String à java.sql.Timestamp
                DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                LocalDateTime localDateTime = LocalDateTime.parse(timestamp, timestampFormatter);
                java.sql.Timestamp sqlTimestamp = java.sql.Timestamp.valueOf(localDateTime);
                statement.setTimestamp(10, sqlTimestamp);
                statement.executeUpdate();
            }
        }
    }
}
