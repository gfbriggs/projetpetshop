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
    private Animal animalCourant;
    private List<Animal> lstAnimaux;
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
     * @return L'animal courant pour voir ses details
     */
    public Animal getAnimalCourant() {
        return animalCourant;
    }

    /**
     *Obtient l'animal courant pour le detail d'un animal sur une application animalerie.
     * @return
     */
    public List<Animal> getLstAnimaux() {
        return lstAnimaux;
    }
    /**
     *
     * @param lstAnimaux
     */
    public void setLstAnimaux(List<Animal> lstAnimaux) {
        this.lstAnimaux = lstAnimaux;
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
