package org.fieldenbriggs.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.xml.internal.bind.v2.TODO;
import org.fieldenbriggs.exception.AuthentificationErrorException;
import org.fieldenbriggs.model.Utilisateur;
import org.fieldenbriggs.receive.UtilisateurReceive;
import org.fieldenbriggs.send.UtilisateurSend;

/**
 * Created by 1354177 on 2016-10-20.
 * Contients les méthodes de services côté serveur pour l'application petshot
 */
public class Service {


public void verifyPassword(Utilisateur user , String password) throws AuthentificationErrorException
{
    // TODO: 10/21/2016 Finish this
}

public String authentifierUtilisateur(String pUserRequest)
{
    Gson gson = new Gson();
    UtilisateurSend userRequest = gson.fromJson(pUserRequest, UtilisateurSend.class);
    // TODO: 10/21/2016 Trouver l'utilisateur par son id

    // TODO: 10/21/2016 Valider son mot de passe

    return gson.toJson(new UtilisateurReceive());
}


}
