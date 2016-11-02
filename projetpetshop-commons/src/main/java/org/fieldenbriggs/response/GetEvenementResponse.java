package org.fieldenbriggs.response;

import java.util.Date;

/**
 * Created by 1354177 on 2016-11-02.
 */
public class GetEvenementResponse {
    private String typeEvenement;
    private Date dateEvenement;
    public Date getDateEvenement() {
        return dateEvenement;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public GetEvenementResponse(String typeEvenement, Date dateEvenement)
    {
        this.dateEvenement = dateEvenement;
        this.typeEvenement = typeEvenement;
    }

}
