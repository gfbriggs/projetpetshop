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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return id.equals(token.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
