package org.fieldenbriggs.service;


import com.google.gson.Gson;

import org.fieldenbriggs.exception.AuthentificationErrorException;
import org.fieldenbriggs.exception.ErrorAjoutUtilisateurException;
import org.fieldenbriggs.model.Data;
import org.fieldenbriggs.model.Utilisateur;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
     * Test qui va nous sortir un bon utilisateur des données, le test devrait nous retourner le bon utilisateur.
     */
    //==============================================================================================================================================================================
    @Test
    public  void getUserBon() throws AuthentificationErrorException
    {
        // On essaie avec le premier utilisateur
        String email = "test@gmail.com";
        Utilisateur user = webService.getUser(email);
        Assert.assertEquals(user.getCourriel() , email);

        // On en fait un deuxième pour être sur
        email = "test2@gmail.com";
        user = webService.getUser(email);
        Assert.assertEquals(user.getCourriel(),email);
    }

    /**
     * Ce test va passer un mauvais courriel utilisateur et il est supposer attraper une authentification exception.
     * @throws AuthentificationErrorException
     */
    @Test(expected = AuthentificationErrorException.class)
    public void getUserFail() throws  AuthentificationErrorException
    {
        // On essaie avec le premier utilisateur avec un mauvais courriel
        String email = "tes24t@gmail.com";
        Utilisateur user = webService.getUser(email);

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
    //==============================================================================================================================================================================
    /**
     * Test qui va voir si la réponse du serveur pour une requete utilisateur, le package
     * retourné doit être le bon du  bon utilisateur.
     * @throws AuthentificationErrorException
     */
    //==============================================================================================================================================================================
    @Test
    public void authentifierUtilisateurBon() throws  AuthentificationErrorException
    {
        // On va construire un package request

        UtilisateurLogRequest ur = new UtilisateurLogRequest("test@gmail.com","admin");
        UtilisateurLogResponse ures = webService.authentifierUtilisateur(ur);

        Assert.assertEquals(ures.getCourriel() ,"test@gmail.com");
        Assert.assertEquals(ures.getNom(),"admin");
        Assert.assertEquals(ures.getId(),1);

        // On va refaire le test avec des caps dans l'adresse pour être sur que cela aussi marche

         ur = new UtilisateurLogRequest("TEST@gmail.com","admin");
         ures = webService.authentifierUtilisateur(ur);

        Assert.assertEquals(ures.getCourriel() ,"test@gmail.com");
        Assert.assertEquals(ures.getNom(),"admin");
        Assert.assertEquals(ures.getId(),1);

    }
    //==============================================================================================================================================================================
    /**
     * Ce test va passer  un mauvais utilisateur et il doit retourner une erreur d'authentification
     * @throws AuthentificationErrorException
     */
    //==============================================================================================================================================================================
    @Test(expected = AuthentificationErrorException.class)
    public void authentifierUserMauvais() throws  AuthentificationErrorException
    {
        UtilisateurLogRequest ur = new UtilisateurLogRequest("merde@gmail.com","admin");
        UtilisateurLogResponse ures = webService.authentifierUtilisateur(ur);

    }
    //==============================================================================================================================================================================
    /**
     * Ce test va vérifier si la vérification de courriel pour un ajout utilisateur fonctionne
     * Il renvoie une exception lorsqu'il ne trouve rien.
     */
    //==============================================================================================================================================================================
    @Test(expected = ErrorAjoutUtilisateurException.class)
    public void  verifyCourrielMauvais()
    {
        
    }

}