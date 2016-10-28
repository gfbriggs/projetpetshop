package org.fieldenbriggs.model;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
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
    private List<Evenement> lstEvenement;

 public List<Animal> getLstAnimeux() {
  return lstAnimaux;
 }

    public List<Evenement> getLstEvenement() {
        return lstEvenement;
    }

    public List<Animal> getLstAnimaux() {
        return lstAnimaux;
    }


 public List<Utilisateur> getLstUtilisateurs() {
  return lstUtilisateurs;
 }


 /**
  * Constructeur et données de départ
  */
 public Data()
 {
     this.lstUtilisateurs = new ArrayList<Utilisateur>();
     this.lstAnimaux =  new ArrayList<Animal>();
     this.lstEvenement = new ArrayList<Evenement>();
     Utilisateur user1 =  new Utilisateur(1,"test@gmail.com","admin","admin");
     getLstUtilisateurs().add(user1);
     user1 = new Utilisateur(2,"test2@gmail.com","admin2","admin2");
     getLstUtilisateurs().add(user1);
     user1 = new Utilisateur(3,"test3@gmail.com","admin3","admin3");
     getLstUtilisateurs().add(user1);
     getLstAnimaux().add(new Animal(1,1,"Chat","Abyssin","Fluffy", new LocalDate(2016,4,5)));
     getLstAnimaux().add(new Animal(2,1,"Chien","Colley","Sparky",new LocalDate(2013,4,10)));
     getLstAnimaux().add(new Animal(3,2,"Chat","Espagnol","Meow",new LocalDate(2015,4,6)));
     getLstAnimaux().add(new Animal(4,2,"Chat","Bengal","sirLancelot",new LocalDate(2014,3,5)));
     getLstAnimaux().add(new Animal(5,3,"Chien","Bulldog","Popotte",new LocalDate(2016,6,13)));
     getLstAnimaux().add(new Animal(6,3,"Chien","Teckel","saussice",new LocalDate(2016,6,13)));


 }





}
