package org.fieldenbriggs.petshop.service;

/**
 * Created by Geoffrey on 8/31/2016.
 */
public class Service {

    private static Service instance;

    public static Service getInstance() {
        if(instance == null) {
            instance = new Service();
        }
        return instance;
    }

}
