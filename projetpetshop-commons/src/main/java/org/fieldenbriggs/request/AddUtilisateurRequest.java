package org.fieldenbriggs.request;

/**
 * Created by Geoffrey on 10/26/2016.
 */
public class AddUtilisateurRequest {
    private String courriel;
    private String nom;
    private String motDePasse;
    public String getCourriel() {
        return courriel;
    }

    public String getNom() {
        return nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }




    public AddUtilisateurRequest(String pCourriel, String pNom , String pMotDePasse){
        this.courriel = pCourriel;
        this.nom = pNom;
        this.motDePasse = pMotDePasse;
    }

}
