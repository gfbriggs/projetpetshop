package org.fieldenbriggs.petshop.interfaceanimalerie;

import org.fieldenbriggs.request.AddUtilisateurRequest;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.AnimalDetailResponse;
import org.fieldenbriggs.response.AnimalListResponse;
import org.fieldenbriggs.response.UtilisateurLogResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Geoffrey on 10/26/2016.
 */
public interface IWebService {


    @GET("api/flush/")
    Call<String> flush();
    //getanimalsbyid
    @GET("api/getanimals/{id}")
    Call<List<AnimalListResponse>> getAnimaux(@Path("id")long id);
    //signin
    @POST("api/signin/")
    Call<UtilisateurLogResponse> signin(@Body UtilisateurLogRequest utilisateurLogRequest);
    //signup
    @POST("api/adduser/")
    Call<UtilisateurLogResponse> adduser(@Body AddUtilisateurRequest userRequest);

    @GET("api/getanimaldetail/{id}")
    Call<AnimalDetailResponse> getAnimalDetail (@Path("id")long id);

}
