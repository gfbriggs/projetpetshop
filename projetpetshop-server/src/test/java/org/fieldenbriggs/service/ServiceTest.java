package org.fieldenbriggs.service;

import com.google.gson.Gson;
import org.fieldenbriggs.exception.AnimalNonDisponibleException;
import org.fieldenbriggs.exception.AuthentificationErrorException;
import org.fieldenbriggs.exception.ErrorAjoutUtilisateurException;
import org.fieldenbriggs.model.Data;
import org.fieldenbriggs.model.Utilisateur;
import org.fieldenbriggs.request.AddAnimalRequest;
import org.fieldenbriggs.request.AddUtilisateurRequest;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.AnimalDetailResponse;
import org.fieldenbriggs.response.AnimalListResponse;
import org.fieldenbriggs.response.GetEvenementResponse;
import org.fieldenbriggs.response.UtilisateurLogResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Geoffrey on 10/21/2016.
 * Classe de test pour le webService de l'animalerie
 */
public class ServiceTest {
    private WebService webService;
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
     * Ce test va vérifier si la vérification d'utilisateur pour un ajout utilisateur fonctionne
     * Il renvoie une exception lorsqu'il trouve que l'utilisateur existe.
     */
    //==============================================================================================================================================================================
    @Test(expected = ErrorAjoutUtilisateurException.class)
    public void  verifyUserMauvais() throws ErrorAjoutUtilisateurException
    {
        webService.verifyUtilisateurExiste("test@gmail.com");
    }
    //==============================================================================================================================================================================
    /**
     * Ce test va vérifie si l'ajout d'utilisateur fonctionne
     */
    //==============================================================================================================================================================================
    @Test
    public void ajoutUtilisateurBon() throws ErrorAjoutUtilisateurException, AuthentificationErrorException
    {
        // On flush le data d'abord pour être sur que le tout fonctionne
        webService.flush();
        // On va crée un package d'ajout pour ajouter un utilisateur
        AddUtilisateurRequest userAdd = new AddUtilisateurRequest("fielden.geoffrey@gmail.com","Geoffrey","Passw0rd");
        // On fait l'ajout
        UtilisateurLogResponse reponseLog = webService.ajouterUnUtilisateur(userAdd);
        //  On regarde si notre add a reussi et si il nous renvoie la bonne réponse

        Assert.assertEquals(userAdd.getCourriel(),reponseLog.getCourriel());
        Assert.assertEquals(userAdd.getNom(),reponseLog.getNom());

        // On va récuperer notre utilisateur pour voir si le id renvoyé est le bon

        Utilisateur user = webService.getUser(userAdd.getCourriel());

        Assert.assertEquals(user.getId(),reponseLog.getId());
    }
    //==============================================================================================================================================================================
    /**
     * Ce test va regarder si les bons animaux sont retourné lors du get
     */
    //==============================================================================================================================================================================
    @Test
    public void getAnimaux()
    {
        // On flush pour que le jeux de données soit exact
        webService.flush();

        // On va tester la liste du premier utilisateur avec les paramêtres pour être sur qu'il va
        // Chercher les bonnes entrés

        List<AnimalListResponse> lstAnimaux = webService.getAnimals(1);

        Assert.assertEquals(lstAnimaux.size(),2);
        Assert.assertEquals(lstAnimaux.get(0).getNom(),"Fluffy");
        Assert.assertEquals(lstAnimaux.get(1).getNom(),"Sparky");
    }
    //==============================================================================================================================================================================
    /**
     * Ce test va verifier si les bon datas son récupérés pour un id animal
     */
    //==============================================================================================================================================================================
    @Test
    public  void getAnimalDetailBon() throws AnimalNonDisponibleException
    {
        // On récupère l'obje à l'aide de l'appel
        AnimalDetailResponse response = webService.getAnimalDetail(1);

        // On vérifie si les détails récupéré sont bon
        Assert.assertEquals(response.getNom(),"Fluffy");
        Assert.assertEquals(response.getType(),"Chat");
        Assert.assertEquals(response.getRace(),"Abyssin");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2016);
        cal.set(Calendar.MONTH, Calendar.APRIL);
        cal.set(Calendar.DAY_OF_MONTH, 5);
        Assert.assertEquals(response.getDateDeNaissance().getDate(),cal.getTime().getDate());

    }
    //==============================================================================================================================================================================
    /**
     * Ce test va s'assurer que si on appel un animal qui n'existe pas l'exception est lancé
     * @throws AnimalNonDisponibleException
     */
    //==============================================================================================================================================================================
    @Test(expected = AnimalNonDisponibleException.class)
    public void  getAnimalDetailMauvais() throws AnimalNonDisponibleException
    {
        // On récupère l'objet à l'aide de l'appel avec un id qui n'éxiste pas et il devrait nous lancer l'exception
        AnimalDetailResponse response = webService.getAnimalDetail(0);
    }
    //==============================================================================================================================================================================
    /**
     * Ce test va vérifer si l'ajout d'animal se fait comme il faut dans la liste.
     */
    //==============================================================================================================================================================================
    @Test
    public void addAnimalSuccess()
    {
        // On flush le data au cas ou
        webService.flush();
        // On construit une requête d'ajout d'animal
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2015);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 16);

        AddAnimalRequest request = new AddAnimalRequest(1,"karka","Chat","Bengal",cal.getTime());
        // On flush le data pour être sur de pouvoir refaire se test à répétition
        webService.flush();
        // On apelle la requete
        AnimalListResponse response = webService.ajouterUnAnimal(request);

        // On verifie les données de la réponse

        Assert.assertEquals(response.getId(),7);
        Assert.assertEquals(response.getNom(),"karka");
        Assert.assertEquals(response.getType(),"Chat");

        // On regarde si l'entrée est dans la liste

        Assert.assertEquals(Data.getInstance().getLstAnimaux().get(6).getId(),7);
    }

    /**
     * Test si la liste d'evenements retourné est la bonne
     */
    @Test
    public void getEventsBon() throws AnimalNonDisponibleException
    {
        // On flush pour que le test fonctionne
        webService.flush();
        // On va chercher une liste
        List<GetEvenementResponse> lstevenement = webService.getEvents(1);
        // Sa grossseur devrait être 2
        Assert.assertEquals(lstevenement.size(),2);
        // Le premier element devrait être bon
        Assert.assertEquals(lstevenement.get(0).getTypeEvenement(),"Vaccin Rage");
    }

    /**
     * Test si quand on cherche une liste d'evenements avec un id animal invalide l'exception est lancée
     * @throws AnimalNonDisponibleException
     */
    @Test (expected = AnimalNonDisponibleException.class)
    public void getEventsMauvais() throws AnimalNonDisponibleException
    {
        webService.getEvents(0);
    }
}