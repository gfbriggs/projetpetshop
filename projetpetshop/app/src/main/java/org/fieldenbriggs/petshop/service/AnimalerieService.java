package org.fieldenbriggs.petshop.service;

import org.fieldenbriggs.petshop.interfaceanimalerie.IWebService;
import org.fieldenbriggs.response.AnimalListResponse;
import org.fieldenbriggs.response.UtilisateurLogResponse;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

import okhttp3.logging.HttpLoggingInterceptor;
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
                //http://5a5.di.college-em.info:7022/
                .baseUrl("https://10.0.2.2:8443/")
                .client(getClient())
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


    public static OkHttpClient getClient(){
        try {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            // configure the builder to accept all SSL certificates
            builder = builder.sslSocketFactory(sslSocketFactory);
            // configure the builder to accept all hostnames includint localhost
            builder = builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            // Adds logging capability to see http exchanges on Android Monitor
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder = builder.addInterceptor(interceptor);
            return builder.build();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }




}
