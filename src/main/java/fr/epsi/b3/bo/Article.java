package fr.epsi.b3.bo;

public class Article {
    private int id;
    private String nom;
    private Double prix;
    private Fournisseur fournisseur;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getnom() {
        return nom;
    }
    public void setnom(String nom) {
        this.nom = nom;
    }
    public Double getprix() {
        return prix;
    }
    public void setprix(Double prix) {
        this.prix = prix;
    }
    public Fournisseur getFournisseur() {
        return fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    public Article(int id, String nom, Double prix, Fournisseur fournisseur) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.fournisseur = fournisseur;
    }

    public Article(String nom, Double prix, Fournisseur fournisseur) {
        this.nom = nom;
        this.prix = prix;
        this.fournisseur = fournisseur;
    }
}
