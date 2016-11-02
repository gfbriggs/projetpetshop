package org.fieldenbriggs.model;
import java.util.ArrayList;
import java.util.Calendar;
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
    private List<Evenement> lstEvenements;


    public List<Animal> getLstAnimeux() {
  return lstAnimaux;
 }

    public List<Animal> getLstAnimaux() {
        return lstAnimaux;
    }

    public List<Evenement> getLstEvenements() {
        return lstEvenements;
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
     this.lstEvenements = new ArrayList<Evenement>();
     Utilisateur user1 =  new Utilisateur(1,"test@gmail.com","admin","admin");
     getLstUtilisateurs().add(user1);
     user1 = new Utilisateur(2,"test2@gmail.com","admin2","admin2");
     getLstUtilisateurs().add(user1);
     user1 = new Utilisateur(3,"test3@gmail.com","admin3","admin3");
     getLstUtilisateurs().add(user1);
     Calendar cal = Calendar.getInstance();
     cal.set(Calendar.YEAR, 2016);
     cal.set(Calendar.MONTH, Calendar.APRIL);
     cal.set(Calendar.DAY_OF_MONTH, 5);
     getLstAnimaux().add(new Animal(1,1,"Chat","Abyssin","Fluffy", cal.getTime()));
     cal.set(Calendar.YEAR, 2013);
     cal.set(Calendar.MONTH, Calendar.APRIL);
     cal.set(Calendar.DAY_OF_MONTH, 10);
     getLstAnimaux().add(new Animal(2,1,"Chien","Colley","Sparky",cal.getTime()));
     cal.set(Calendar.YEAR, 2015);
     cal.set(Calendar.MONTH, Calendar.APRIL);
     cal.set(Calendar.DAY_OF_MONTH, 6);
     getLstAnimaux().add(new Animal(3,2,"Chat","Espagnol","Meow", cal.getTime()));
     cal.set(Calendar.YEAR, 2014);
     cal.set(Calendar.MONTH, Calendar.MARCH);
     cal.set(Calendar.DAY_OF_MONTH, 5);
     getLstAnimaux().add(new Animal(4,2,"Chat","Bengal","sirLancelot",cal.getTime()));
     cal.set(Calendar.YEAR, 2016);
     cal.set(Calendar.MONTH, Calendar.JUNE);
     cal.set(Calendar.DAY_OF_MONTH, 13);
     getLstAnimaux().add(new Animal(5,3,"Chien","Bulldog","Popotte",cal.getTime()));
     cal.set(Calendar.YEAR, 2016);
     cal.set(Calendar.MONTH, Calendar.JUNE);
     cal.set(Calendar.DAY_OF_MONTH, 15);
     getLstAnimaux().add(new Animal(6,3,"Chien","Teckel","saussice", cal.getTime()));


     // On ajoute des evenements pour les tests
     // On doit faire la date en premier
     cal.set(Calendar.YEAR, 2016);
     cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
     cal.set(Calendar.DAY_OF_MONTH, 15);
     getLstEvenements().add(new Evenement(1,1,"Vaccin Rage",cal.getTime()));
     getLstEvenements().add(new Evenement(2,1,"Vermifuge",cal.getTime()));
     cal.set(Calendar.YEAR, 2013);
     cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
     cal.set(Calendar.DAY_OF_MONTH, 15);
     getLstEvenements().add(new Evenement(3,2,"Vaccin Parvot",cal.getTime()));




 }

 public void flush ()
 {
     this.lstUtilisateurs = new ArrayList<Utilisateur>();
     this.lstAnimaux =  new ArrayList<Animal>();
     this.lstEvenements = new ArrayList<Evenement>();
     Utilisateur user1 =  new Utilisateur(1,"test@gmail.com","admin","admin");
     getLstUtilisateurs().add(user1);
     user1 = new Utilisateur(2,"test2@gmail.com","admin2","admin2");
     getLstUtilisateurs().add(user1);
     user1 = new Utilisateur(3,"test3@gmail.com","admin3","admin3");
     getLstUtilisateurs().add(user1);
     Calendar cal = Calendar.getInstance();
     cal.set(Calendar.YEAR, 2016);
     cal.set(Calendar.MONTH, Calendar.APRIL);
     cal.set(Calendar.DAY_OF_MONTH, 5);
     getLstAnimaux().add(new Animal(1,1,"Chat","Abyssin","Fluffy", cal.getTime()));
     cal.set(Calendar.YEAR, 2013);
     cal.set(Calendar.MONTH, Calendar.APRIL);
     cal.set(Calendar.DAY_OF_MONTH, 10);
     getLstAnimaux().add(new Animal(2,1,"Chien","Colley","Sparky",cal.getTime()));
     cal.set(Calendar.YEAR, 2015);
     cal.set(Calendar.MONTH, Calendar.APRIL);
     cal.set(Calendar.DAY_OF_MONTH, 6);
     getLstAnimaux().add(new Animal(3,2,"Chat","Espagnol","Meow", cal.getTime()));
     cal.set(Calendar.YEAR, 2014);
     cal.set(Calendar.MONTH, Calendar.MARCH);
     cal.set(Calendar.DAY_OF_MONTH, 5);
     getLstAnimaux().add(new Animal(4,2,"Chat","Bengal","sirLancelot",cal.getTime()));
     cal.set(Calendar.YEAR, 2016);
     cal.set(Calendar.MONTH, Calendar.JUNE);
     cal.set(Calendar.DAY_OF_MONTH, 13);
     getLstAnimaux().add(new Animal(5,3,"Chien","Bulldog","Popotte",cal.getTime()));
     cal.set(Calendar.YEAR, 2016);
     cal.set(Calendar.MONTH, Calendar.JUNE);
     cal.set(Calendar.DAY_OF_MONTH, 15);
     getLstAnimaux().add(new Animal(6,3,"Chien","Teckel","saussice", cal.getTime()));


     // On ajoute des evenements pour les tests
     // On doit faire la date en premier
     cal.set(Calendar.YEAR, 2016);
     cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
     cal.set(Calendar.DAY_OF_MONTH, 15);
     getLstEvenements().add(new Evenement(1,1,"Vaccin Rage",cal.getTime()));
     getLstEvenements().add(new Evenement(2,1,"Vermifuge",cal.getTime()));
     cal.set(Calendar.YEAR, 2013);
     cal.set(Calendar.MONTH, Calendar.SEPTEMBER);
     cal.set(Calendar.DAY_OF_MONTH, 15);
     getLstEvenements().add(new Evenement(3,2,"Vaccin Parvot",cal.getTime()));
 }

}
