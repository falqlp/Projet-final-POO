package com.project;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.mockito.Mockito.*;

public class DatabaseManagerTest {

    @Test
    public void testSaveUser() throws SQLException {
        DatabaseManager dbManager = new DatabaseManager();
        DatabaseManager spyDBManager = spy(dbManager);
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockStatement = mock(PreparedStatement.class);

        User testUser = new User("12345678901234", "Doe", "John", "2000-01-01", "1234567890", "john.doe@example.com", 1, "100", 100.00);
        String timestamp = "20240607123000";

        String expectedQuery = "INSERT INTO users (Numero_Securite_Sociale, Nom, Prenom, Date_Naissance, Numero_Telephone, E_Mail, ID_Remboursement, Code_Soin, Montant_Remboursement, Timestamp_fichier) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        // Stubbing the methods
        when(mockConnection.prepareStatement(expectedQuery)).thenReturn(mockStatement);
        doReturn(mockConnection).when(spyDBManager).getDBConnection();

        spyDBManager.saveUser(testUser, timestamp);
        
        // verify if the prepared statement is correctly invoked
        verify(mockStatement).setString(1, testUser.getNumeroSecuriteSociale());
        verify(mockStatement).setString(2, testUser.getNom());
        verify(mockStatement).setString(3, testUser.getPrenom());
        verify(mockStatement).setDate(4, Date.valueOf(testUser.getDateNaissance()));
        verify(mockStatement).setString(5, testUser.getNumeroTelephone());
        verify(mockStatement).setString(6, testUser.getEmail());
        verify(mockStatement).setInt(7, testUser.getIdRemboursement());
        verify(mockStatement).setString(8, testUser.getCodeSoin());
        verify(mockStatement).setDouble(9, testUser.getMontantRemboursement());
        verify(mockStatement).executeUpdate();
    }
}