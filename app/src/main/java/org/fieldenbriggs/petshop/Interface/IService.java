package org.fieldenbriggs.petshop.Interface;

import android.view.View;

import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Animalerie;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public interface IService {


/**
 * Méthode qui permet de vérifier si un utilisateur est valide pour pouvoir entrer dans l'application
 * @return Si l'utilisateur est valide ou non.
 */
    public Boolean verifierUtilisateur(String pCourriel , String pMotDePasse , Animalerie pAnimalerie);

    public String getNomUtilisateurCourant (Animalerie pAnimalerie);

    public void voirDetailsAnimal(Animal animal, View view);


}
