package org.fieldenbriggs.petshop.model;

import org.joda.time.LocalDate;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public class Evenement {
    /*
    Champs
     */
    private long id;
    private long animalID;
    private String  description;
    private String  typeEvenement;
    private LocalDate dateEvenement;
    /*
    Proprietés
     */

    public LocalDate getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(LocalDate dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }
    /*
    Constructeur
     */

    public Evenement(String description, String typeEvenement) {
        this.description = description;
        this.typeEvenement = typeEvenement;
        this.dateEvenement = LocalDate.now();
    }
    /*
    Méthodes
     */


    @Override
    public String toString() {
        return "Evenement{" +
                "description='" + description + '\'' +
                ", typeEvenement='" + typeEvenement + '\'' +
                ", dateEvenement=" + dateEvenement +
                '}';
    }
}
