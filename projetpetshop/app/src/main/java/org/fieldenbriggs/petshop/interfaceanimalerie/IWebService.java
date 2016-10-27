package org.fieldenbriggs.petshop.interfaceanimalerie;

import org.fieldenbriggs.request.AddUtilisateurRequest;
import org.fieldenbriggs.request.UtilisateurLogRequest;
import org.fieldenbriggs.response.UtilisateurLogResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Geoffrey on 10/26/2016.
 */
public interface IWebService {



    // Retrofit de serveur



    //Retrofit du local


    @GET("api/")
    Call<String> racineLocal();

    @GET("api/flush/")
    Call<String> flush();

    @POST("api/signin/")
    Call<UtilisateurLogResponse> signin(@Body UtilisateurLogRequest utilisateurLogRequest);

    @POST("api/adduser/")
    Call<UtilisateurLogResponse> adduser(@Body AddUtilisateurRequest userRequest);
}
