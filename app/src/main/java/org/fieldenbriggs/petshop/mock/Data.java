package org.fieldenbriggs.petshop.mock;

import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Evenement;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 1354177 on 2016-09-08.
 */
public class Data {
    private static Data instance;

    public static Data getInstance() {
        if(instance == null) {
            instance = new Data();
        }
        return instance;
    }


    /**
     * Méthodes
     */
    /***
     * Méthode qui retourne une list d'animaux pré-conçu pour tester l'application d'animaleri
     * @return
     */
    public List<Animal> ListeAnimaux()
    {
        List<Animal> lstAnimaux = new ArrayList<>();
        // Variable pour la date de naissance.
        lstAnimaux.add(new Animal("Sparky","Chien","Colley",new LocalDate(2013,4,10)));
        lstAnimaux.add(new Animal("Meow","Chat","Espagnol",new LocalDate(2015,4,6)));
        lstAnimaux.add(new Animal("sirLancelot","Chat","Bengal",new LocalDate(2014,3,5)));
        for (Animal item :lstAnimaux) {
            item.setLstEvenements(listeEvenements());
        }
        return lstAnimaux;
    }

    /**
     * Méthode qui retourne une liste d'
     * @return
     */
    public List<Evenement> listeEvenements()
    {
        ///// TODO: 2016-09-15 Implementer cette méthode
        List<Evenement> lstEvenements =  new ArrayList<>();
        lstEvenements.add(new Evenement("À mangé du poisson","Repas du midi"));
        lstEvenements.add(new Evenement("À mangé des croquettes","Repas du soir"));
        return  lstEvenements;
    }
}
