package org.fieldenbriggs.response;

/**
 * Created by 1354177 on 2016-10-20.
 */
public class UtilisateurLogResponse {
    private long id;
    private String nom;
    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    private String courriel;

}
