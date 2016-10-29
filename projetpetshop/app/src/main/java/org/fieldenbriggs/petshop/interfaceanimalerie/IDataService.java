package org.fieldenbriggs.petshop.interfaceanimalerie;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Utilisateur;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Geoffrey on 9/22/2016.
 */
public interface IDataService {
    // Temp
    @GET("/")
    Call<String> racine();

    @GET("/animaux/")
    Call<List<Animal>> animals();
    /*
    @GET("/utilisateurs/")
    Call<List<Utilisateur>> users();
    */

    @POST("/animaux/")
    Call<Animal> addAnimals(Animal animal);
    /*
    @POST("/utilisateurs/")
    Call<Utilisateur> addUsers(Utilisateur utilisateur);

    */



}
