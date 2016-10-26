package org.fieldenbriggs.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1354177 on 2016-10-20.
 * Cette classe contient des données pour le serveur
 */

public class Data {
    private static Data instance;

    public static Data getInstance() {
        if (instance == null)
        {
            instance = new Data();
        }

        return instance;
    }

    private List<Animal> lstAnimaux;
 private List<Utilisateur> lstUtilisateurs;

 public List<Animal> getLstAnimeux() {
  return lstAnimaux;
 }

 public void setLstAnimeux(List<Animal> lstAnimeux) {
  this.lstAnimaux = lstAnimeux;
 }

 public List<Utilisateur> getLstUtilisateurs() {
  return lstUtilisateurs;
 }

 public void setLstUtilisateurs(List<Utilisateur> lstUtilisateurs) {
  this.lstUtilisateurs = lstUtilisateurs;
 }

 /**
  * Constructeur
  */
 public Data()
 {
     this.lstUtilisateurs = new ArrayList<Utilisateur>();
     this.lstAnimaux =  new ArrayList<Animal>();
     Utilisateur user1 =  new Utilisateur(1,"test@gmail.com","admin","admin");
     getLstUtilisateurs().add(user1);
     user1 = new Utilisateur(2,"test2@gmail.com","admin2","admin2");
     getLstUtilisateurs().add(user1);
     user1 = new Utilisateur(3,"test3@gmail.com","admin3","admin3");
     getLstUtilisateurs().add(user1);
 }

 /**
  * Cette méthode initialise les données de départ pour l'application du petshop
  */



}
