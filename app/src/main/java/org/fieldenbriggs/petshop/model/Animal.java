package org.fieldenbriggs.petshop.model;


import java.util.Date;

/**
 * Created by Geoffrey on 8/31/2016.
 * Represente une classe de type animal pour une application d'animalerie.
 */
public class Animal {

    /*
    Champs
     */
    private String typeAnimal;
    private String race;
    private String nom;
    private Date dateNaissance;

    /*
    Propriétés
     */

    /**
     * Obtient l'age de l'animal
     * @return l'age
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Obtient le nom de l'animal
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Definit le nom de l'animal
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la race de l'animal
     * @return la race
     */
    public String getRace() {
        return race;
    }

    /**
     * Obtient le type de l'animal
     * @return le type
     */
    public String getTypeAnimal() {
        return typeAnimal;
    }

    /**
     * Propriété calculée qui retourne l'age de l'animal selon sa date de naissance
     * @return L'age de l'animal
     */
    public int getAge(){


        //// TODO: 8/31/2016 Verifier si ça marche...
        return new Date().compareTo(dateNaissance) ;
    }
    /*
    Constructeurs
     */

    /**
     * Instancie un objet de type animal pour une application d'animalerie.
     * @param pNom Le nom de l'animal
     * @param pType Le type de l'animal
     * @param pRace La race de l'animal
     * @param pDateNaissance La date de naissance de l'animal
     */
    public  Animal(String pNom, String pType, String pRace, Date pDateNaissance)
    {
        nom = pNom;
        typeAnimal = pType;
        race = pRace;
        dateNaissance = pDateNaissance;
    }
    /*
    Méthodes
     */

    @Override
    public String toString()
    {
        //// TODO: 8/31/2016 Implementer ceci 
        return "";
    }

}
