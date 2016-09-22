package org.fieldenbriggs.petshop.Interface;

import org.fieldenbriggs.petshop.model.Animal;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public interface IService {


/**
 * Méthode qui permet de vérifier si un utilisateur est valide pour pouvoir entrer dans l'application
 * @return Si l'utilisateur est valide ou non.
 */
    public Boolean estUtilisateur(String pCourriel , String pMotDePasse);

    public void ajouterAnimal(Animal animal);

    public void remplirListeUtilisateur();

    public void remplirListeAnimaux();
}
