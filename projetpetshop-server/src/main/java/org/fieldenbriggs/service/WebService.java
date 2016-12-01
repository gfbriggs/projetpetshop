package org.fieldenbriggs.service;


import com.google.gson.Gson;
import org.apache.commons.validator.EmailValidator;
import org.fieldenbriggs.exception.AnimalNonDisponibleException;
import org.fieldenbriggs.exception.AuthentificationErrorException;
import org.fieldenbriggs.exception.ErrorAjoutUtilisateurException;
import org.fieldenbriggs.exception.NoTokenException;
import org.fieldenbriggs.model.*;
import org.fieldenbriggs.request.AddAnimalRequest;
import org.fieldenbriggs.request.AddUtilisateurRequest;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.AnimalDetailResponse;
import org.fieldenbriggs.response.AnimalListResponse;
import org.fieldenbriggs.response.GetEvenementResponse;
import org.fieldenbriggs.response.UtilisateurLogResponse;



import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by 1354177 on 2016-10-20.
 * Contient les méthodes de service côté serveur pour l'application petshop
 */
@Path("/")
public class WebService {

    @GET @Path("flush")
    public String flush()
    {
        data = new Data();
        return "flush success!";
    }
    private Data data;

    private static final String COOKIE = "cookieWeb";
    private static final String TOKEN_ERROR_MESSAGE = "Pas de token valide!";
     /*
     Méthodes de service
      */
    //==============================================================================================================================================================================
    /**
     * Permet d'identifier un utilisateur, si l'utilisateur est identifié avec succès ses informations
     * de compte sont retournés pour l'affichage
     * @param pUserRequest La requete utilisateur
     * @return Une string d'objet contenant les informations à afficher
     */
    //==============================================================================================================================================================================
    @POST
    @Path("signin")
    public Response authentifierUtilisateur(UtilisateurLogRequest pUserRequest) throws AuthentificationErrorException, InterruptedException {
        Thread.sleep(2000);
        System.out.println("La requete est passé!");
        // On trouve l'utilisateur et on valide le mot de passe
        Utilisateur utilisateurRechercher = getUser(pUserRequest.getAuthentifiant().toLowerCase());
        verifyPassword(utilisateurRechercher.getMotDePass(),pUserRequest.getMotDePasse());
        // Si le tout passe on construit le package de retour
        // On fait le token
        // On fait la date d'expiration pour le token, 1 jour.
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        System.out.println(cal.getTime().toString());
        Token token = new Token(UUID.randomUUID().toString(),cal.getTime(),utilisateurRechercher.getId());
        data.getLsttokens().add(token);
        // On fait le Cookie
        NewCookie cookie = new NewCookie(COOKIE,token.getId(),"/","","Token ID",80400,true);
        // Et on revoit le package au serveur

        UtilisateurLogResponse user = new UtilisateurLogResponse(utilisateurRechercher.getId(), utilisateurRechercher.getCourriel(), utilisateurRechercher.getNom());
        return  Response.ok(new Gson().toJson(user)).cookie(cookie).build();

    }
    @POST
    @Path("signout")
    public Response deconnecterUtilisateur(@CookieParam(COOKIE)Cookie cookie) throws NoTokenException, InterruptedException {
        Thread.sleep(2000);
        // On va chercher le token de l'utilisateur qui est log
        Token token = getToken(cookie.getValue());
        // On detruit le token courant
        data.getLsttokens().remove(token);
        // On fait un nouveau cookie qui expire et pas de token
        NewCookie newCookie = new NewCookie(COOKIE,null,"/","","Token ID",0,true);
        return  Response.ok(true).cookie(newCookie).build();
    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui va ajouter un utilisateur à l'animalerie.
     * @param pAddUserRequest
     * @return
     * @throws ErrorAjoutUtilisateurException
     */
    //==============================================================================================================================================================================
    @POST @Path("adduser")
    public UtilisateurLogResponse ajouterUnUtilisateur(AddUtilisateurRequest pAddUserRequest) throws ErrorAjoutUtilisateurException, InterruptedException {
        Thread.sleep(2000);
        System.out.println("Rêquete envoyé avec : "+ pAddUserRequest.getNom() +", "+ pAddUserRequest.getCourriel() +", "+ pAddUserRequest.getMotDePasse());
        // Vérifier si il y a déjà un utilisateur avec le même courriel
        verifyUtilisateurExiste(pAddUserRequest.getCourriel());

        // Si non on vérifie que le courriel est un couriel valide
        if(EmailValidator.getInstance().isValid(pAddUserRequest.getCourriel()))
        {
            // On ajoute l'utilisateur au data en lui attribuant un nouveau ID
            Utilisateur newUser = new Utilisateur(data.getLstUtilisateurs().size()+1,pAddUserRequest.getCourriel().toLowerCase(), pAddUserRequest.getNom(), pAddUserRequest.getMotDePasse());
            data.getLstUtilisateurs().add(newUser);

            // On crée et retourne la response pour logger l'utilisateur au besoin
            return new UtilisateurLogResponse(newUser.getId(),newUser.getCourriel(),newUser.getNom());
        }
        else
        {
            throw  new ErrorAjoutUtilisateurException();
        }


    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui va chercher les animaux d'un utilisateur identifié pour afficher
     * la liste sur l'application
     * @param id Le ID de l'utilisateur
     * @return La liste des animaux à afficher
     */
    //==============================================================================================================================================================================
    @GET @Path("getanimals/{id}")
     public List<AnimalListResponse> getAnimals(@PathParam("id") long id, @CookieParam(COOKIE) Cookie cookie) throws InterruptedException, NoTokenException {
        Thread.sleep(2000);
        Utilisateur user = authenticate(cookie.getValue());
        System.out.println("Requete passée pour les animaux de l'utilisateur n." + user.getId());
        // On revoie la liste qu'on a crée au préalable avec la méthode.
        return createAnimalListFromID(user.getId());
    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui retourne les evenements d'un animals a partir du id d'un animal
     * @param id le id de l'animal
     * @return la liste des evenements
     */
    //==============================================================================================================================================================================
    @GET @Path("getevents/{id}")
    public List<GetEvenementResponse> getEvents(@PathParam("id") long id, @CookieParam(COOKIE) Cookie cookie) throws AnimalNonDisponibleException, InterruptedException, NoTokenException {
        Thread.sleep(2000);
        // On authentifie avec le cookie
        Utilisateur user = authenticate(cookie.getValue());
        // Il faut instancier le data
        data = Data.getInstance();
        // On va verifier si l'animal exitste <-- oui oui
        if(!animalExists(id))
            throw new AnimalNonDisponibleException();

        // On contruit la liste d'evenements

        List<GetEvenementResponse> lstevenement = new ArrayList<GetEvenementResponse>();
        for (Evenement event: data.getLstEvenements()
             ) {
            if(event.getAnimalId() == id)
            {
                lstevenement.add(new GetEvenementResponse(event.getTypeEvenement(),event.getDateEvenement()));
            }
        }
        // On renvoit la liste
        return lstevenement;
    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui permet d'afficher les detail d'un animal sur une liste selon son ID
     * @param id
     * @return
     */
    //==============================================================================================================================================================================
    @GET @Path("getanimaldetail/{id}")
     public AnimalDetailResponse getAnimalDetail(@PathParam("id") long id, @CookieParam(COOKIE) Cookie cookie) throws AnimalNonDisponibleException, InterruptedException, NoTokenException {
        Thread.sleep(2000);
        // On authentifie avec le cookie
        Utilisateur user = authenticate(cookie.getValue());
        // On s'assure que le data de base est crée et instancié
        data = Data.getInstance();
        // On va chercher un bon animal par son id
        for (Animal animal: data.getLstAnimeux()) {
            if(animal.getId() == id)
            {
                return new AnimalDetailResponse(animal.getNom(),animal.getTypeAnimal(),animal.getRace(),animal.getDateNaissance());
            }
        }
        throw  new AnimalNonDisponibleException();
    }
    //==============================================================================================================================================================================
    /**
     * Cette méthode permet à l'utilisateur d'ajouter un animal
     * @param pAddAnimalRequest La rquête pour l'ajout d'un animal
     * @return Le message de succès.
     */
    //==============================================================================================================================================================================
    @POST
    @Path("addanimal")
    public AnimalListResponse ajouterUnAnimal(@CookieParam(COOKIE) Cookie cookie, AddAnimalRequest pAddAnimalRequest) throws Exception
    {
        Thread.sleep(2000);
        // On fait l'authentifiaction utilisteur
        Utilisateur user = authenticate(cookie.getValue());
        Calendar cal = Calendar.getInstance();

        if(pAddAnimalRequest.getNom().isEmpty() || pAddAnimalRequest.getRace().isEmpty() || pAddAnimalRequest.getType().isEmpty() || pAddAnimalRequest.getDateDeNaissance().compareTo(cal.getTime()) > 0)
        {
            throw new Exception();
        }
        // Il y a moins de validation à faire sur un animal que sur un utilisateur
        // Un utilisateur peux avoir 2 animaux du même nom
        // On s'assure que le data est instancié
        data = Data.getInstance();
        // On contruit l'objet
        Animal animalAAjouter = new Animal(data.getLstAnimaux().size() + 1,pAddAnimalRequest.getUserId(),pAddAnimalRequest.getType(),pAddAnimalRequest.getRace(),pAddAnimalRequest.getNom(),pAddAnimalRequest.getDateDeNaissance());
        data.getLstAnimeux().add(animalAAjouter);

        // On fait la réponse pour la rajouter à la liste
        return new AnimalListResponse(animalAAjouter.getId(),animalAAjouter.getTypeAnimal(),animalAAjouter.getNom()) ;
    }
    /*
    méthodes utilitaires
     */
    //==============================================================================================================================================================================
    /**
     * Verifie si le mot de passe de l'utilisateur est bon
     * @param userPass Le mot de passe de l'utilisateur recherché
     * @param enteredPass Le mot de passe entré par le client
     * @return Vrai si le mot de passe est valide
     * @throws AuthentificationErrorException Si le mot de passe entré n'est pas le bon
     */
    //==============================================================================================================================================================================
     boolean verifyPassword(String userPass , String enteredPass) throws AuthentificationErrorException
    {
    if(userPass.equals(enteredPass))
    {

      return true;
    }
    else
    {
        throw new AuthentificationErrorException();
    }

    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui permet de trouver un utilisateur par son courriel
     * @param pCourriel  Le courriel de l'utilisateur
     * @return l'utilisateur
     */
    //==============================================================================================================================================================================
    Utilisateur getUser(String pCourriel) throws AuthentificationErrorException
    {

        data = Data.getInstance();
        Utilisateur userRecherche = new Utilisateur(0,"","","");

        for (Utilisateur user: data.getLstUtilisateurs()) {
            if (user.getCourriel().equals(pCourriel))
            {
                userRecherche = user;
            }
        }
        if(userRecherche.getId() == 0)
        {
            throw new AuthentificationErrorException();
        }
        return userRecherche;

    }
    //==============================================================================================================================================================================
    /**
     *Méthode qui vérifie si un utilisateur existe selon le courriel.
     * @param pCourriel Le courriel entré
     * @throws ErrorAjoutUtilisateurException
     */
    //==============================================================================================================================================================================
    void verifyUtilisateurExiste(String pCourriel) throws ErrorAjoutUtilisateurException
    {
        //
        data = Data.getInstance();
        Utilisateur userRecherche = new Utilisateur(0,"","","");

        for (Utilisateur user: data.getLstUtilisateurs()) {
            if (user.getCourriel().equals(pCourriel))
            {
                userRecherche = user;
            }
        }
        if(userRecherche.getId() != 0)
        {
            throw new ErrorAjoutUtilisateurException();
        }

    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui va contruire une liste d'animaux pour afficher
     * @param userID Le ID de l'utilisateur pour qui on créé la liste
     * @return la liste des items animaux pour afficher dans l'application
     */
    //==============================================================================================================================================================================
    private List<AnimalListResponse> createAnimalListFromID(long userID)
    {
        data = Data.getInstance();
        List<AnimalListResponse> lstAnimaux =  new ArrayList<AnimalListResponse>();

        for ( Animal animal: data.getLstAnimaux()) {
            if(userID == animal.getUtilisateurId())
            {
                AnimalListResponse animalAAjouter = new AnimalListResponse(animal.getId(),animal.getTypeAnimal(),animal.getNom());
                lstAnimaux.add(animalAAjouter);
            }

        }
        return lstAnimaux;
    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui cherche un animal et qui voit si un animal existe
     * @param animalID
     * @return
     */
    //==============================================================================================================================================================================
    private boolean animalExists(long animalID)
    {

        for (Animal animal:data.getLstAnimaux()
             ) {
            if(animal.getId() == animalID)
            {
                return true;
            }
        }
        return false;
    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui permet de trouver le token de l'utilisateur
     * @return
     */
     //==============================================================================================================================================================================
    private Token getToken(String tokenID) throws NoTokenException {


        Token tokenFind;
        for (Token token: Data.getInstance().getLsttokens()) {
            if(tokenID.equals(token.getId()))
            {
                tokenFind = token;
                return  tokenFind;
            }
        }

        throw  new NoTokenException(TOKEN_ERROR_MESSAGE);
    }
    //==============================================================================================================================================================================
    /**
     * Méthode qui permet de valider le token d'un utilisateur pour le log in
     * @param tokenID
     * @return
     * @throws AuthentificationErrorException
     */
    //==============================================================================================================================================================================
    private Utilisateur authenticate(String tokenID) throws NoTokenException
    {

        // On commence par trouver le token
        Token token = getToken(tokenID);
        //On regarde si la date d'expiration est bonne
        Calendar cal = new GregorianCalendar();

        if(token.getDateExp().before(cal.getTime()))
        {
            // Si elle n'est pas bonne on lance une exception et on supprime le token
            Data.getInstance().getLsttokens().remove(token);
            throw  new NoTokenException(TOKEN_ERROR_MESSAGE);
        }

        // Si tout passe on cherche le bon utilisateur
        for (Utilisateur user:Data.getInstance().getLstUtilisateurs()
             ) {
            if(user.getId() == token.getUserID())
            {
                return user;
            }
        }

        throw new NoTokenException(TOKEN_ERROR_MESSAGE);
    }

}
