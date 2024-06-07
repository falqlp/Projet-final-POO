package com.project;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public List<User> parse(Path filePath) throws IOException {
        List<User> users = new ArrayList<>();
        try (FileReader reader = new FileReader(filePath.toFile())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);
            for (CSVRecord record : records) {
                User user = new User(
                        record.get("Numero_Securite_Sociale"),
                        record.get("Nom"),
                        record.get("Prenom"),
                        record.get("Date_Naissance"),
                        record.get("Numero_Telephone"),
                        record.get("E_Mail"),
                        Integer.parseInt(record.get("ID_Remboursement")),
                        record.get("Code_Soin"),
                        Double.parseDouble(record.get("Montant_Remboursement"))
                );
                users.add(user);
            }
        }
        return users;
    }
}
