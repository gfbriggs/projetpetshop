package org.fieldenbriggs.request;

/**
 * Created by 1354177 on 2016-10-20.
 *
 */
public class UtilisateurLogRequest {
    private String authentifiant;
    private String motDePasse;

    public String getMotDePasse() {
        return motDePasse;
    }


    public String getAuthentifiant() {
        return authentifiant;
    }


    public UtilisateurLogRequest(String pAuthentifiant , String pMotDePasse)
    {
            motDePasse = pMotDePasse;
            authentifiant = pAuthentifiant;
    }
}
