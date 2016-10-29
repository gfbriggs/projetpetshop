package org.fieldenbriggs.request;

import org.joda.time.LocalDate;

/**
 * Created by Geoffrey on 10/28/2016.
 */
public class AddAnimalRequest {

    private long userId;
    private String nom;
    private String type;
    private String race;
    private LocalDate dateDeNaissance;
    public String getNom() {
        return nom;
    }

    public long getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public String getRace() {
        return race;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }




    public AddAnimalRequest(long pUserID,String pNom, String pType, String pRace, LocalDate pDateDeNaissance)
    {
        this.userId = pUserID;
        this.nom = pNom;
        this.type = pType;
        this.race = pRace;
        this.dateDeNaissance = pDateDeNaissance;
    }
}
