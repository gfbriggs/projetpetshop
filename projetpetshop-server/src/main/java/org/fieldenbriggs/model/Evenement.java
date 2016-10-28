package org.fieldenbriggs.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 * Created by Geoffrey on 10/28/2016.
 */
public class Evenement {
    private long id;
    private long animalID;
    private String  description;
    private String  typeEvenement;
    private DateTime dateEvenement;

    public long getId() {
        return id;
    }

    public long getAnimalID() {
        return animalID;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public DateTime getDateEvenement() {
        return dateEvenement;
    }




    public Evenement(long id , long animalID, String description, String typeEvenement) {
        this.description = description;
        this.typeEvenement = typeEvenement;
        this.id =  id;
        this.animalID = animalID;
        this.dateEvenement = DateTime.now();
    }
}
