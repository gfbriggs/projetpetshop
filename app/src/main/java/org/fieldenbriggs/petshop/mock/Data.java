package org.fieldenbriggs.petshop.mock;

import org.fieldenbriggs.petshop.model.Animal;

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
        lstAnimaux.add(new Animal("Sparky","Chien","Colley",new Date(20150402)));
        lstAnimaux.add(new Animal("Meow","Chat","Espagnol",new Date(20150406)));
        lstAnimaux.add(new Animal("sirLancelot","Chat","Bengal",new Date(20130305)));
        return lstAnimaux;
    }
}
