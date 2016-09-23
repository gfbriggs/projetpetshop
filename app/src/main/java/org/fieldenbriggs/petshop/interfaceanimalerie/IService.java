package org.fieldenbriggs.petshop.interfaceanimalerie;

import org.fieldenbriggs.petshop.model.Utilisateur;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public interface IService {


    public Utilisateur getUtilisateur(String pCourriel);

    public void ajouterAnimal(String pNom, String pType, String Race, String pDate);

    public void ajouterUtilisateur(String pNom, String pCourriel, String pMotDePasse);

}
