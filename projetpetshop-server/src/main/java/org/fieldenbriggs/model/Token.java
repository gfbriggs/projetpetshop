package org.fieldenbriggs.model;

import java.util.Date;

/**
 * Classe de token pour l'authentification d'un utilisateur.
 * Created by 1354177 on 2016-11-17.
 */
public class Token {
    String id;
    Date dateExp;
    long userID;

    public Token(String id, Date dateExp, long userID)
    {
        this.id = id;
        this.dateExp = dateExp;
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public Date getDateExp() {
        return dateExp;
    }

    public long getUserID() {
        return userID;
    }
}
