package org.fieldenbriggs.petshop.adapteur;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.fieldenbriggs.petshop.R;
import org.fieldenbriggs.petshop.model.Animal;
import org.fieldenbriggs.petshop.model.Evenement;

import java.util.List;

/**
 * Created by 1354177 on 2016-09-01.
 */
public class EvenementAdapter extends ArrayAdapter<Evenement> {

    /**
     * Constructeur de l'adapteur
     * @param context le context de l'app
     * @param evenements La liste sur l'adapteur
     */
    public EvenementAdapter(Context context, List<Evenement> evenements)
    {
        super(context, R.layout.evenement_list_item,evenements);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // On inflate
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.evenement_list_item, parent, false);
        Evenement item = getItem(position);
        // remplir le layout avec les bonnes valeurs
        // Type
        TextView typeEvenement = (TextView) row.findViewById(R.id.evenement_type);
        typeEvenement.setText(item.getTypeEvenement());
        //Description
        TextView descriptionEvenement = (TextView) row.findViewById(R.id.evenement_description);
        descriptionEvenement.setText(item.getDescription());
        row.setTag(item);
        //Date
        TextView dateEvenement = (TextView) row.findViewById(R.id.evnement_date);
        dateEvenement.setText(item.getDateEvenement().toString());
        return row;
    }
}
