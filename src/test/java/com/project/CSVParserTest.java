package com.project;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CSVParserTest {

    @Test
    public void testParse() throws IOException {
        // Prepare test input
        String input = "Numero_Securite_Sociale,Nom,Prenom,Date_Naissance,Numero_Telephone,E_Mail,ID_Remboursement,Code_Soin,Montant_Remboursement\n"
                + "12345,John,Doe,2000-01-01,1111,john.doe@gmail.com,123,abc,200.00";
        Path tempFile = Files.createTempFile(null, null);
        Files.write(tempFile, input.getBytes());

        // Invoke the method to be tested
        CSVParser parser = new CSVParser();
        List<User> users = parser.parse(tempFile);

        // Validate the output
        assertNotNull(users, "Users list should not be null");
        assertEquals(1, users.size(), "Users list should have 1 User object");

        User user = users.get(0);
        assertEquals("12345", user.getNumeroSecuriteSociale(), "Invalid Numero_Securite_Sociale");
        assertEquals("John", user.getNom(), "Invalid Nom");
        assertEquals("Doe", user.getPrenom(), "Invalid Prenom");
        assertEquals("2000-01-01", user.getDateNaissance(), "Invalid Date_Naissance");
        assertEquals("1111", user.getNumeroTelephone(), "Invalid Numero_Telephone");
        assertEquals("john.doe@gmail.com", user.getEmail(), "Invalid E_Mail");
        assertEquals(123, user.getIdRemboursement(), "Invalid ID_Remboursement");
        assertEquals("abc", user.getCodeSoin(), "Invalid Code_Soin");
        assertEquals(200.00, user.getMontantRemboursement(), "Invalid Montant_Remboursement");
    }
}
