package org.fieldenbriggs.model;

import java.util.Date;

/**
 * Created by 1354177 on 2016-11-02.
 */
public class Evenement {

    private long id;
    private long animalId;
    private String typeEvenement;
    private Date dateEvenement;

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public long getAnimalId() {
        return animalId;
    }

    public long getId() {
        return id;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public Evenement (long id, long animalId, String typeEvenement , Date dateEvenement)
    {
        this.animalId = animalId;
        this.dateEvenement = dateEvenement;
        this.id = id;
        this.typeEvenement = typeEvenement;
    }

}
