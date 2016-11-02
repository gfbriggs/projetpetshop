package org.fieldenbriggs.petshop.service;

import org.fieldenbriggs.petshop.interfaceanimalerie.IWebService;
import org.fieldenbriggs.response.AnimalListResponse;
import org.fieldenbriggs.response.UtilisateurLogResponse;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * Created by Geoffrey on 8/31/2016.
 */
public class AnimalerieService  {

    private static AnimalerieService instance;
    private UtilisateurLogResponse utilisateurCourant;
    private AnimalListResponse animalCourant;
    private List<AnimalListResponse> lstAnimaux;
    private IWebService server;

    public static AnimalerieService getInstance() {
        if(instance == null) {
            instance = new AnimalerieService();
        }
        return instance;
    }

    public AnimalerieService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://5a5.di.college-em.info:7022/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        server = retrofit.create(IWebService.class);
        lstAnimaux = new ArrayList<>();
    }

    /*====================================================================================================================================================================
    Propriétés
    ==================================================================================================================================================================== */

    public AnimalListResponse getAnimalCourant() {
        return animalCourant;
    }

    public List<AnimalListResponse> getLstAnimaux() {
        return lstAnimaux;
    }

    public void setAnimalCourant(AnimalListResponse animalCourant) {
        this.animalCourant = animalCourant;
    }

    public void setLstAnimaux(List<AnimalListResponse> lstAnimaux) {
        this.lstAnimaux = lstAnimaux;
    }

    /**
     * Définit l'utilisateur Courant de l'application
     * @param utilisateurCourant yep
     */
    public void setUtilisateurCourant(UtilisateurLogResponse utilisateurCourant) {
        this.utilisateurCourant = utilisateurCourant;
    }

    /**
     * Obtient l'utilisateur courant de l'application
     * @return
     */
    public UtilisateurLogResponse getUtilisateurCourant() {
        return utilisateurCourant;
    }
    /*====================================================================================================================================================================
    Méthodes
     ====================================================================================================================================================================*/

    /**
     * Méthode qui permet de faire des appels pour aller chercher le server
     * @return
     */
    public IWebService getServer()
    {
        return server;
    }







}
