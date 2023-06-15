package tn.esen.myloginapp;

public class song {
    private String Artiste;
    private String Catégorie;
    private String Réaliser;
    private String Titre;

    public song() {
    }

    public song(String artiste, String catégorie, String réaliser, String titre) {
        Artiste = artiste;
        Catégorie = catégorie;
        Réaliser = réaliser;
        Titre = titre;
    }

    public String getArtiste() {
        return Artiste;
    }

    public void setArtiste(String artiste) {
        Artiste = artiste;
    }

    public String getCatégorie() {
        return Catégorie;
    }

    public void setCatégorie(String catégorie) {
        Catégorie = catégorie;
    }

    public String getRéaliser() {
        return Réaliser;
    }

    public void setRéaliser(String réaliser) {
        Réaliser = réaliser;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }
}
