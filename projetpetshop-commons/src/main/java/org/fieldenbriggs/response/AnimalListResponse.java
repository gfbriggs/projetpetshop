package org.fieldenbriggs.response;

/**
 * Created by 1354177 on 2016-10-27.
 */
public class AnimalListResponse {
    private long id;

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    private String type;
    private  String nom;


    public AnimalListResponse(long pId, String pType, String pNom)
    {
        this.id = pId;
        this.nom = pNom;
        this.type = pType;
    }
}
