package com.project;

public class User {
    private String numeroSecuriteSociale;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String numeroTelephone;
    private String email;
    private int idRemboursement;
    private String codeSoin;
    private double montantRemboursement;

    public User(String numeroSecuriteSociale, String nom, String prenom, String dateNaissance, String numeroTelephone, String email, int idRemboursement, String codeSoin, double montantRemboursement) {
        this.numeroSecuriteSociale = numeroSecuriteSociale;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroTelephone = numeroTelephone;
        this.email = email;
        this.idRemboursement = idRemboursement;
        this.codeSoin = codeSoin;
        this.montantRemboursement = montantRemboursement;
    }

    // Getters
    public String getNumeroSecuriteSociale() {
        return numeroSecuriteSociale;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public String getEmail() {
        return email;
    }

    public int getIdRemboursement() {
        return idRemboursement;
    }

    public String getCodeSoin() {
        return codeSoin;
    }

    public double getMontantRemboursement() {
        return montantRemboursement;
    }

    @Override
    public String toString() {
        return "com.project.User{" +
                "numeroSecuriteSociale='" + numeroSecuriteSociale + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", numeroTelephone='" + numeroTelephone + '\'' +
                ", email='" + email + '\'' +
                ", idRemboursement=" + idRemboursement +
                ", codeSoin='" + codeSoin + '\'' +
                ", montantRemboursement=" + montantRemboursement +
                '}';
    }
}
