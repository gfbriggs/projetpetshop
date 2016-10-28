package org.fieldenbriggs.model;


import org.joda.time.DateTime;

/**
 * Created by Geoffrey on 10/20/2016.
 */
public class Animal {
    private long utilisateurId;
    private long id;
    private String typeAnimal;
    private String race;

    public long getUtilisateurId() {
        return utilisateurId;
    }

    public long getId() {
        return id;
    }

    public String getTypeAnimal() {
        return typeAnimal;
    }

    public String getRace() {
        return race;
    }

    public String getNom() {
        return nom;
    }

    public DateTime getDateNaissance() {
        return dateNaissance;
    }

    private String nom;
    private DateTime dateNaissance;

    public Animal(long pId, long pUserId, String pType, String pRace, String pNom , DateTime pDatenaissance)
    {
        this.id = pId;
        this.utilisateurId = pUserId;
        this.typeAnimal = pType;
        this.race = pRace;
        this.nom = pNom;
        this.dateNaissance = pDatenaissance;
    }



}
