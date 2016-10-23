package org.fieldenbriggs.service;


import com.google.gson.Gson;

import org.fieldenbriggs.exception.AuthentificationErrorException;
import org.fieldenbriggs.model.Utilisateur;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Geoffrey on 10/21/2016.
 */
public class ServiceTest {
    WebService webService;
    @Before
    public void initialise()
    {
        webService = new WebService();
    }
    //==============================================================================================================================================================================
    /**
     * Ce test va s'assurer que la méthode de vérification de mot de passe fonctionne si on lui donne
     * un mot de passe
     * @throws AuthentificationErrorException si le mot de passe retourner n'est pas le bon.
     */
    //==============================================================================================================================================================================
    @Test
    public void verifyPasswordRight() throws AuthentificationErrorException {
        // On va se créer un faux utilisateur
        Utilisateur user = new Utilisateur(0,"fielden.geoffrey@gmail.com", "nom", "password");
        // On se créée un mot de passe qui va être passé en paramêtre.

        String mdp = "password";

        //Quand on appelle la méthode il devrait nous redonner true
        Assert.assertEquals(webService.verifyPassword(user.getMotDePass(), mdp),true);

    }

    /**
     * Test qui permet de valider si l'exception passé ,au moment de valider le mot de passe, fonctionne.
     */
    @Test(expected = AuthentificationErrorException.class)
    public void verifyPasswordWrong() throws AuthentificationErrorException {
        // On va se créer un faux utilisateur
        Utilisateur user = new Utilisateur(0,"fielden.geoffrey@gmail.com", "nom", "password");
        // On se créée un mot de passe qui va être passé en paramêtre.

        String mdp = "pfpsk";
        webService.verifyPassword(user.getMotDePass(),mdp);
        //Quand on appelle la méthode il devrait nous redonner true

    }


    //==============================================================================================================================================================================
    /**
     * Ce test doit tester si la serialisation de l'utilisateur avec gson et la desérialisation fonctionne aussi bien
     * L'une que l'autre
     * @throws Exception
     */
    //==============================================================================================================================================================================
    @Test
    public void authentifierUtilisateurSerialisation() throws Exception {
        // On va créer un "faux" package utilisateur reçu

        UtilisateurLogRequest userR =  new UtilisateurLogRequest("geoffrey","admin");
        // On créer une instance de Gson pour le test
        Gson gson = new Gson();
        // On fait la serealization.
        String jsonUser = gson.toJson(userR);
        // On deserialize
        UtilisateurLogRequest userR2 = gson.fromJson(jsonUser,UtilisateurLogRequest.class);

        // On test si les valeurs sont pareilles
        Assert.assertEquals(userR.getMotDePasse(), userR2.getMotDePasse());
        Assert.assertEquals(userR.getAuthentifiant(),userR2.getAuthentifiant());
    }

}