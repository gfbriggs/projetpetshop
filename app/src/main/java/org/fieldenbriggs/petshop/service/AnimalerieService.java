package org.fieldenbriggs.petshop.service;


import android.view.View;

import org.fieldenbriggs.petshop.Interface.IService;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Animalerie;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public class AnimalerieService implements IService {

    private static AnimalerieService instance;

    public static AnimalerieService getInstance() {
        if(instance == null) {
            instance = new AnimalerieService();
        }
        return instance;
    }


    @Override
    public Boolean verifierUtilisateur(String pCourriel, String pMotDePasse, Animalerie pAnimalerie) {
        return null;
    }

    @Override
    public String getNomUtilisateurCourant(Animalerie pAnimalerie) {
        return null;
    }

    /**
     * Méthode qui va permetre d'afficher les details d'un animal dans la fiche detail d'un animal courant.
     * @param pAnimal l'animal courant
     */
    @Override
    public void voirDetailsAnimal(Animal pAnimal, View v)
    {

    }



}
