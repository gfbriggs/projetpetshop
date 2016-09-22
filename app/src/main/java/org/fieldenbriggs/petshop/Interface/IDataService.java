package org.fieldenbriggs.petshop.Interface;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Utilisateur;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Geoffrey on 9/22/2016.
 */
public interface IDataService {

    @GET("/")
    Call<String> racine();

    @GET("Data/Animals/")
    Call<List<Animal>> animals();

    @GET("Data/Users")
    Call<List<Utilisateur>> users();
}
