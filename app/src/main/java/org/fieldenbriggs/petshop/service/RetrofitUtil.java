package org.fieldenbriggs.petshop.service;

import org.fieldenbriggs.petshop.interfaceanimalerie.IDataService;
import org.fieldenbriggs.petshop.mock.DataServiceMock;

import java.util.concurrent.TimeUnit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

/**
 * Created by Administrateur on 22/09/2016.
 */

public class RetrofitUtil {



    static DataServiceMock dataMock;

    public static DataServiceMock getMock()
    {

        if(dataMock != null)
        {
            return dataMock;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://allo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        NetworkBehavior networkBehavior = NetworkBehavior.create();
        networkBehavior.setDelay(1000, TimeUnit.MILLISECONDS);
        networkBehavior.setVariancePercent(90);

        MockRetrofit mock = new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();

        BehaviorDelegate<IDataService> delegate = mock.create(IDataService.class);


        IDataService service = retrofit.create(IDataService.class);
        dataMock = new DataServiceMock(delegate);
        return dataMock;
    }

}
