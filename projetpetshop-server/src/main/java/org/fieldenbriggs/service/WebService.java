package org.fieldenbriggs.service;

import com.google.gson.Gson;
import org.fieldenbriggs.exception.AuthentificationErrorException;
import org.fieldenbriggs.model.Data;
import org.fieldenbriggs.model.Utilisateur;
import org.fieldenbriggs.receive.UtilisateurReceive;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;
import org.fieldenbriggs.send.UtilisateurSend;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by 1354177 on 2016-10-20.
 * Contients les méthodes de services côté serveur pour l'application petshot
 */
@Path("/")
public class WebService {
    /*
    @GET @Path("hello")
            public String hello()
    {
        return "Hello";
    } */
    private Data data;
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
    public UtilisateurLogResponse authentifierUtilisateur(UtilisateurLogRequest pUserRequest) throws  AuthentificationErrorException
    {
        // On trouve l'utilisateur et on valide le mot de passe
        Utilisateur utilisateurRechercher = getUser(pUserRequest.getAuthentifiant().toLowerCase());
        verifyPassword(utilisateurRechercher.getMotDePass(),pUserRequest.getMotDePasse());


        // Si le tout passe on construit le package de retour
        UtilisateurLogResponse util = new UtilisateurLogResponse();
        util.setCourriel(utilisateurRechercher.getCourriel());
        util.setId(utilisateurRechercher.getId());
        util.setNom(utilisateurRechercher.getNom());
        // On revoit le package au serveur
        return util;
    }

    /*
    Méthodes utilitaires
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
        //
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


}
