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
    private String nom;
    private String adresse;
    private String motDePasse;
    private List<Animal> lstAnimaux;
    /**
     * Propriétés
     */

    /**
     * Obtient le nom de l'utilisateur
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'utilisateur
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient l'adresse de l'utilisateur
     * @return
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Definit l'adresse de l'utilisateur
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Obtient le mot de passe de l'utilisateur
     * @return
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Definit le mot de passe de l'utilisateur
     * @param motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Obtient la liste courante des animaux
     * @return
     */
    public List<Animal> getLstAnimaux() {
        return lstAnimaux;
    }

    /**
     * Constructeurs
     */
    /**
     *  Instancie un objet de type utilisateur
     * @param pnom Le nom de l'utilisateur
     * @param pCourriel L'adresse courriel de l'utilisateur
     * @param pMotDePasse Le mot de passe de l'utilisateur
     */
    public Utilisateur(String pnom, String pCourriel, String pMotDePasse)
    {
        nom = pnom;
        adresse = pCourriel;
        motDePasse = pMotDePasse;
    }

    /**
     * Méthodes
     */
    /**
     * Méthode override de tostring qui retourne les information de l'utilisateur
     * @return
     */
    @Override
    public String toString()
    {
        return "Utilisateur :"+ nom + " , Courriel : " + adresse;
    }
}
