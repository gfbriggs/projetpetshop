package org.fieldenbriggs.petshop.adapteur;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.response.GetEvenementResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by 1354177 on 2016-09-01.
 */
public class EvenementAdapter extends ArrayAdapter<GetEvenementResponse> {

    /**
     * Constructeur de l'adapteur
     * @param context le context de l'app
     * @param evenements La liste sur l'adapteur
     */
    public EvenementAdapter(Context context, List<GetEvenementResponse> evenements)
    {
        super(context, R.layout.evenement_list_item,evenements);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // On inflate
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.evenement_list_item, parent, false);
        GetEvenementResponse item = getItem(position);
        // remplir le layout avec les bonnes valeurs
        // Type
        TextView typeEvenement = (TextView) row.findViewById(R.id.evenement_type);
        typeEvenement.setText(item.getTypeEvenement());
        //Date
        TextView dateEvenement = (TextView) row.findViewById(R.id.evnement_date);
        String dateDeEvenement = new SimpleDateFormat("yyyy-MM-dd").format(item.getDateEvenement());
        dateEvenement.setText(dateDeEvenement);
        return row;
    }
}
