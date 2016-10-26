package org.fieldenbriggs.petshop;

import org.fieldenbriggs.petshop.interfaceanimalerie.IWebService;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        UtilisateurLogResponse reponse = new UtilisateurLogResponse();
        reponse.setId(1);
        reponse.setNom("admin");
        reponse.setCourriel("test@gmail.com");
        UtilisateurLogResponse userToGet = service.signin(ur).execute().body();

        // On test si les paramêtres récupéré sont ceux attendu.
        Assert.assertEquals(reponse.getId(), userToGet.getId());
        Assert.assertEquals(reponse.getNom(), userToGet.getNom());
        Assert.assertEquals(reponse.getCourriel(), userToGet.getCourriel());
    }



}