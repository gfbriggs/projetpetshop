package org.fieldenbriggs.model;

/**
 * Created by Geoffrey on 10/20/2016.
 */
public class Utilisateur {
    private long id;
    private String courriel;
    private String nom;
    private String motDePass;

    public long getId() {
        return id;
    }

    public String getCourriel() {
        return courriel;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePass() {
        return motDePass;
    }

    public Utilisateur(int id, String courriel, String nom, String motDePass) {
        this.id = id;
        this.courriel = courriel;
        this.nom = nom;
        this.motDePass = motDePass;
    }


}
