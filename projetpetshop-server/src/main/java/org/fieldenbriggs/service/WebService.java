package org.fieldenbriggs.service;

import com.google.gson.Gson;
import org.fieldenbriggs.exception.AuthentificationErrorException;
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
    @GET @Path("hello")
            public String hello()
    {
        return "Hello";
    }
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
     * Permet d'identifier un utilisateur, si l'utilisateur est identifié avec succès ses informations
     * de compte sont retournés pour l'affichage
     * @param pUserRequest La requete utilisateur
     * @return Une string d'objet contenant les informations à afficher
     */
    //==============================================================================================================================================================================
    @POST
    @Path("signin")
    public UtilisateurLogResponse authentifierUtilisateur(UtilisateurLogRequest pUserRequest)
    {
    // TODO: 10/21/2016 Trouver l'utilisateur par son courriel

    // TODO: 10/21/2016 Valider son mot de passe
        UtilisateurLogResponse util = new UtilisateurLogResponse();
    return util;
    }

    /**
     * Méthode qui permet de trouver un utilisateur par son courriel
     * @param pCourriel
     * @return
     */
    private Utilisateur getUser(String pCourriel)
    {
        throw new NotImplementedException();
    }


}
