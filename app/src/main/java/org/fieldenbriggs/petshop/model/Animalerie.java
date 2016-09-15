package org.fieldenbriggs.petshop.model;

import java.util.List;

/**
 * Created by Geoffrey on 8/31/2016.
 * Classe qui simule une animalerie pour l'application petshop
 */
public class Animalerie {

    /*
    Champs
     */
     Animal animalCourant;
    /*
    Propriétés
     */
    /**
     * Definit l'animal courant de l'animalerie.
     * @param animalCourant
     */
    public void setAnimalCourant(Animal animalCourant) {
        this.animalCourant = animalCourant;
    }

    /**
     * Obtient l'animal courant sur une l'animalerie.
     * @return
     */
    public Animal getAnimalCourant() {
        return animalCourant;
    }
    /*
    Constructeurs
     */
    private static Animalerie instance;

    public static Animalerie getInstance() {
        if(instance == null) {
            instance = new Animalerie();
        }
        return instance;
    }

    /*
    Méthodes
     */


}
