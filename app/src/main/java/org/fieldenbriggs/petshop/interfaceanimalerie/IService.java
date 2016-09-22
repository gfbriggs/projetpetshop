package org.fieldenbriggs.petshop.interfaceanimalerie;

import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Utilisateur;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public interface IService {


/**
 * Méthode qui permet de vérifier si un utilisateur est valide pour pouvoir entrer dans l'application
 * @return Si l'utilisateur est valide ou non.
 */
    public Utilisateur getUtilisateur(String pCourriel);

    public void ajouterAnimal(Animal animal);

}
