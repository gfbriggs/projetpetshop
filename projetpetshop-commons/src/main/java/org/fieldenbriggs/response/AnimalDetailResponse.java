package org.fieldenbriggs.response;

import org.joda.time.LocalDate;

import java.util.Date;

/**
 * Created by Geoffrey on 10/28/2016.
 */
public class AnimalDetailResponse {

    
   private String nom;
   private String type;
   private String race;
   private Date dateDeNaissance;
    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }

    public String getRace() {
        return race;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }


    
    
    public AnimalDetailResponse(String pNom, String pType, String pRace, Date pDateDeNaissance)
    {
        this.nom = pNom;
        this.type = pType;
        this.race = pRace;
        this.dateDeNaissance = pDateDeNaissance;
    }
    
}
