package org.fieldenbriggs.petshop.service;


import org.fieldenbriggs.petshop.interfaceanimalerie.IService;
import org.fieldenbriggs.petshop.mock.DataServiceMock;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Utilisateur;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


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
    @Override
    public Utilisateur getUtilisateur(String pCourriel) {
        Utilisateur userCourant = new Utilisateur("test","test","test");
        for (Utilisateur user:lstUtilisteurs) {
            if (pCourriel.equals(user.getAdresse()))
            {
                userCourant = user;
            }
        }
        return userCourant;
    }
    /**
     * Méthode qui permet d'ajouter un animal avec ses paramêtres dans la liste des animaux de l'animalerie
     * @param pNom Le nom de l'animal
     * @param pType Le type de l'animal
     * @param pDate La date de naissance de l'animal
     */
    @Override
    public void ajouterAnimal(String pNom, String pType, String pRace, String pDate) {
        Animal nouvelAnimal = new Animal(pNom,pType, pRace , new LocalDate(pDate));
        RetrofitUtil.getMock().addAnimals(nouvelAnimal).enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {

            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {

            }
        });
    }

    /**
     * Méthode qui permet d'ajouter un utilisateur à l'animalerie
     * @param pNom Le nom de l'utilisateur
     * @param pCourriel Le courriel de l'utilisateur
     * @param pMotDePasse Le mot de passe de l'utilisateur
     */
    @Override
    public void ajouterUtilisateur(String pNom, String pCourriel, String pMotDePasse) {
        Utilisateur nouvelUtilisateur = new Utilisateur(pNom,pCourriel,pMotDePasse);
        RetrofitUtil.getMock().addUsers(nouvelUtilisateur).enqueue(new Callback<Utilisateur>() {
            @Override
            public void onResponse(Call<Utilisateur> call, Response<Utilisateur> response) {

            }

            @Override
            public void onFailure(Call<Utilisateur> call, Throwable t) {

            }
        });
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
