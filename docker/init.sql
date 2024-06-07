CREATE TABLE IF NOT EXISTS users (
    Numero_Securite_Sociale VARCHAR(255),
    Nom VARCHAR(255),
    Prenom VARCHAR(255),
    Date_Naissance DATE,
    Numero_Telephone VARCHAR(20),
    E_Mail VARCHAR(255),
    ID_Remboursement INT PRIMARY KEY,
    Code_Soin VARCHAR(50),
    Montant_Remboursement DECIMAL(10, 2),
    Timestamp_fichier TIMESTAMP
);
