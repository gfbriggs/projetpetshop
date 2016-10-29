package org.fieldenbriggs.petshop;

import android.support.v4.media.MediaMetadataCompat;

import org.fieldenbriggs.petshop.interfaceanimalerie.IWebService;
import org.fieldenbriggs.request.AddUtilisateurRequest;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.AnimalListResponse;
import org.fieldenbriggs.response.UtilisateurLogResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ServiceTest {
    IWebService service;

    @Before
    public void setUpRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(IWebService.class);
    }

    /**
     * Cette méthode test si il est possible de faire le signin avec succés
     *
     * @throws Exception
     */
    @Test
    public void callSignin() throws Exception {

        // On monte un package de requete sign in
        UtilisateurLogRequest ur = new UtilisateurLogRequest("test@gmail.com", "admin");
        // On fait la reponser attendue pour comparer
        UtilisateurLogResponse reponse = new UtilisateurLogResponse(1,"test@gmail.com", "admin");
        // On log et recoit la répoonse du serveur
        UtilisateurLogResponse userToGet = service.signin(ur).execute().body();

        // On test si les paramêtres récupéré sont ceux attendu.
        Assert.assertEquals(reponse.getId(), userToGet.getId());
        Assert.assertEquals(reponse.getNom(), userToGet.getNom());
        Assert.assertEquals(reponse.getCourriel(), userToGet.getCourriel());
    }

    @Test
    public void callAddUser() throws Exception{
        // On monte un package de requete
        AddUtilisateurRequest requeteAdd = new AddUtilisateurRequest("fielden.geoffrey@gmail.com","geoffrey","passw0rd");
        UtilisateurLogResponse reponse = new UtilisateurLogResponse(4,"fielden.geoffrey@gmail.com","geoffrey");
        // On flush le data du serveur pour ne pas faire d'interference utilisateur
        service.flush().execute();
        // On envoie le package pour le add
        UtilisateurLogResponse userLogged = service.adduser(requeteAdd).execute().body();

        Assert.assertEquals(reponse.getId(), userLogged.getId());
        Assert.assertEquals(reponse.getNom(), userLogged.getNom());
        Assert.assertEquals(reponse.getCourriel(), userLogged.getCourriel());
    }


    @Test
    public void callGetAnimaux() throws Exception
    {
        // On récupère la liste des animaux avec le call au serveur
        List<AnimalListResponse> animalListResponses = service.getAnimaux(1).execute().body();
        // On verifie si la liste est bien retournée avec tous ses élements
        Assert.assertEquals(animalListResponses.size(),2);

        // On s'assure que les objets sont complet et bien retournés

        Assert.assertEquals(animalListResponses.get(0).getNom(),"Fluffy");
        Assert.assertEquals(animalListResponses.get(1).getNom(),"Sparky");
    }



}