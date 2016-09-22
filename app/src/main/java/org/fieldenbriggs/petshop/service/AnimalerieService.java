package org.fieldenbriggs.petshop.service;


import android.util.Log;

import org.fieldenbriggs.petshop.interfaceanimalerie.IService;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Utilisateur;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public class AnimalerieService implements IService {

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

    /*
    Propriétés
     */

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

    /*
    Méthodes
     */

    /***
     * Méthode qui permet de vérifie si un utilisateur existe et est valide.
     * @param pCourriel
     * @return
     */
    @Override
    public Utilisateur getUtilisateur(String pCourriel) {
        Utilisateur userCourant = new Utilisateur("test","test","test");
        for (Utilisateur user:lstUtilisteurs) {
            if (pCourriel .equals(user.getAdresse()))
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
    @Override
    public void ajouterAnimal(Animal animal) {

    }

    public void setUtilisateurCourant(Utilisateur utilisateurCourant) {
        this.utilisateurCourant = utilisateurCourant;
    }

    public Utilisateur getUtilisateurCourant() {
        return utilisateurCourant;
    }
}
