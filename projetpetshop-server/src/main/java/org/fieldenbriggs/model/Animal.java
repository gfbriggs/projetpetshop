package org.fieldenbriggs.model;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import java.util.Date;

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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    private String nom;
    private LocalDate dateNaissance;

    public Animal(long pId, long pUserId, String pType, String pRace, String pNom , LocalDate pDatenaissance)
    {
        this.id = pId;
        this.utilisateurId = pUserId;
        this.typeAnimal = pType;
        this.race = pRace;
        this.nom = pNom;
        this.dateNaissance = pDatenaissance;
    }



}
