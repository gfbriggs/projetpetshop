package org.fieldenbriggs.petshop.model;

import java.util.List;

/**
 * Created by Geoffrey on 8/31/2016.
 * Classe qui représente un utilisateur pour l'application de petshop.
 */
public class Utilisateur {

    /**
     * Champs
     */
    private long id;
    private String nom;
    private String courriel;
    private String motDePasse;
    /**
     * Propriétés
     */

    /**
     * Obtient le nom de l'utilisateur
     * @return le nom d'utilisateur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'utilisateur
     * @param nom le nouveau nom d'utilisateur
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient l'courriel de l'utilisateur
     * @return l'courriel courante
     */
    public String getCourriel() {
        return courriel;
    }

    /**
     * Definit l'courriel de l'utilisateur
     * @param courriel la nouvelle courriel
     */
    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    /**
     * Obtient le mot de passe de l'utilisateur
     * @return le mot de passe de l'utilisateur
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Definit le mot de passe de l'utilisateur
     * @param motDePasse le nouveau mot de passe de l'utilisateur
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Constructeurs
     */
    /**
     *  Instancie un objet de type utilisateur
     * @param pnom Le nom de l'utilisateur
     * @param pCourriel L'courriel courriel de l'utilisateur
     * @param pMotDePasse Le mot de passe de l'utilisateur
     */
    public Utilisateur(String pnom, String pCourriel, String pMotDePasse)
    {
        nom = pnom;
        courriel = pCourriel;
        motDePasse = pMotDePasse;
    }

    /**
     * Méthodes
     */
    /**
     * Méthode override de tostring qui retourne les information de l'utilisateur
     * @return les infos de l'utilisateur
     */
    @Override
    public String toString()
    {
        return "Utilisateur :"+ nom + " , Courriel : " + courriel;
    }
}
