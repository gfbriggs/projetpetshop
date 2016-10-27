package org.fieldenbriggs.response;

/**
 * Created by 1354177 on 2016-10-20.
 */
public class UtilisateurLogResponse {
    private long id;
    private String nom;
    private String courriel;
    public String getCourriel() {
        return courriel;
    }


    public long getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }


    public UtilisateurLogResponse(long pId , String pCourriel , String pNom)
    {
        this.id = pId;
        this.nom = pNom;
        this.courriel = pCourriel;
    }


}
