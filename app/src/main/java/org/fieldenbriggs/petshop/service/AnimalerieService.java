package org.fieldenbriggs.petshop.service;


import android.util.Log;

import org.fieldenbriggs.petshop.Interface.IDataService;
import org.fieldenbriggs.petshop.Interface.IService;
import org.fieldenbriggs.petshop.mock.DataServiceMock;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Utilisateur;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public class AnimalerieService implements IService {

    private static AnimalerieService instance;
    private Utilisateur utilisateurCourant;
    private Animal animalCourant;
    private List<Animal> lstAnimaux;

    public static AnimalerieService getInstance() {
        if(instance == null) {
            instance = new AnimalerieService();
        }
        return instance;
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
    /*
    Méthodes
     */

    /***
     * Méthode qui permet de vérifie si un utilisateur existe et est valide.
     * @param pCourriel
     * @param pMotDePasse
     * @return
     */
    @Override
    public Boolean estUtilisateur(String pCourriel, String pMotDePasse) {
        return null;
    }


    @Override
    public void ajouterAnimal(Animal animal) {

    }

    /**
     * Méthode qui va aller chercher la liste des utilisateurs courant sur le serveur.
     */
    @Override
    public void remplirListeUtilisateur() {

        RetrofitUtil.getMock().users().enqueue(new Callback<List<Utilisateur>>() {
            @Override
            public void onResponse(Call<List<Utilisateur>> call, Response<List<Utilisateur>> response) {

            }

            @Override
            public void onFailure(Call<List<Utilisateur>> call, Throwable t) {
                Log.i("Test Retrofit", "onFailure: Le call au mock ne fonctionne pas!");
            }
        });

    }

    /**
     * Méthodes qui va aller chercher les animaux courant sur le serveur.
     */
    @Override
    public void remplirListeAnimaux() {

        RetrofitUtil.getMock().animals().enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
             List<Animal> lstAnimaux = response.body();
                setLstAnimaux(lstAnimaux);
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Log.e("projetpetshop", "onFailure: Error mock! " );
            }
        });
    }


    public void setUtilisateurCourant(Utilisateur utilisateurCourant) {
        this.utilisateurCourant = utilisateurCourant;
    }

    public Utilisateur getUtilisateurCourant() {
        return utilisateurCourant;
    }
}
