package org.fieldenbriggs.petshop.Interface;

import org.fieldenbriggs.petshop.model.Animalerie;
import org.fieldenbriggs.petshop.model.Utilisateur;

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


}
