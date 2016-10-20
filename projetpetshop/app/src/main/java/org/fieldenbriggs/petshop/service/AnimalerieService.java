package org.fieldenbriggs.petshop.service;


import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Geoffrey on 8/31/2016.
 */
public class AnimalerieService  {

    private static AnimalerieService instance;
    private Utilisateur utilisateurCourant;
    private Animal animalCourant;
    private List<Animal> lstAnimaux;
    private List<Utilisateur> lstUtilisteurs;

    public static AnimalerieService getInstance() {
        if(instance == null) {
            instance = new AnimalerieService();
        }
        return instance;
    }

    public AnimalerieService()
    {
        lstAnimaux = new ArrayList<>();
    }

    /*====================================================================================================================================================================
    Propriétés
    ==================================================================================================================================================================== */

    public Animal getAnimalCourant() {
        return animalCourant;
    }

    public List<Animal> getLstAnimaux() {
        return lstAnimaux;
    }

    public void setAnimalCourant(Animal animalCourant) {
        this.animalCourant = animalCourant;
    }

    public void setLstAnimaux(List<Animal> lstAnimaux) {
        this.lstAnimaux = lstAnimaux;
    }

    public List<Utilisateur> getLstUtilisteurs() {
        return lstUtilisteurs;
    }

    public void setLstUtilisteurs(List<Utilisateur> lstUtilisteurs) {
        this.lstUtilisteurs = lstUtilisteurs;
    }

    /**
     * Définit l'utilisateur Courant de l'application
     * @param utilisateurCourant
     */
    public void setUtilisateurCourant(Utilisateur utilisateurCourant) {
        this.utilisateurCourant = utilisateurCourant;
    }

    /**
     * Obtient l'utilisateur courant de l'application
     * @return
     */
    public Utilisateur getUtilisateurCourant() {
        return utilisateurCourant;
    }
    /*====================================================================================================================================================================
    Méthodes
     ====================================================================================================================================================================*/
    /***
     * Méthode qui permet de vérifie si un utilisateur existe et est valide.
     * @param pCourriel
     * @return
     */
    public Utilisateur getUtilisateur(String pCourriel) {
        Utilisateur userCourant = new Utilisateur("test","test","test");
        for (Utilisateur user:lstUtilisteurs) {
            if (pCourriel.equals(user.getCourriel()))
            {
                userCourant = user;
            }
        }
        return userCourant;
    }







    /**
     * Verifie le mot de passe de l'utilisateur pour authentifier la connexion.
     * @param pMotdePasse
     * @param pUtilisateur
     * @return
     */
    public Boolean motDePasseValide(String pMotdePasse , Utilisateur pUtilisateur)
    {
        if(pMotdePasse.equals(pUtilisateur.getMotDePasse()))
        {
            setUtilisateurCourant(pUtilisateur);
            return true;
        }
        return false;
    }


}
