package org.fieldenbriggs.petshop.model;



import org.joda.time.LocalDate;
import org.joda.time.Years;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geoffrey on 8/31/2016.
 * Represente une classe de type animal pour une application d'animalerie.
 */
public class Animal {


    /*
        Champs
         */
    private long utilisateurId;


    private long id;
    private String typeAnimal;
    private String race;
    private String nom;
    private LocalDate dateNaissance;
    private List<Evenement> lstEvenements;

    /*
    Propriétés
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(long utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    /**
     * Obtient l'age de l'animal
     * @return l'age
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Obtient le nom de l'animal
     * @return Le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Definit le nom de l'animal
     * @param nom le nouveau nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la race de l'animal
     * @return la race
     */
    public String getRace() {
        return race;
    }

    /**
     * Obtient le type de l'animal
     * @return le type
     */
    public String getTypeAnimal() {
        return typeAnimal;
    }

    /**
     * Propriété calculée qui retourne l'age de l'animal selon sa date de naissance
     * @return L'age de l'animal
     */
    public int getAge(){
        LocalDate aujourdhui = LocalDate.now();
        return Years.yearsBetween(dateNaissance,aujourdhui).getYears() ;
    }

    public List<Evenement> getLstEvenements() {
        return lstEvenements;
    }

    public void setLstEvenements(List<Evenement> lstEvenements) {
        this.lstEvenements = lstEvenements;
    }

    /*
    Constructeurs
     */

    /**
     * Instancie un objet de type animal pour une application d'animalerie.
     * @param pNom Le nom de l'animal
     * @param pType Le type de l'animal
     * @param pRace La race de l'animal
     * @param pDateNaissance La date de naissance de l'animal
     */
    public  Animal(String pNom, String pType, String pRace, LocalDate pDateNaissance)
    {
        nom = pNom;
        typeAnimal = pType;
        race = pRace;
        dateNaissance = pDateNaissance;
        lstEvenements = new ArrayList<>();  
    }
    /*
    Méthodes
     */

    @Override
    public String toString()
    {
        return "Un " + typeAnimal + " de la race " + race +" qui a " + getAge() + " an" + (getAge() > 1 ?"s" :"");
    }

}