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
import org.fieldenbriggs.response.AnimalListResponse;

import java.util.List;

/**
 * Created by 1354177 on 2016-09-01.
 */
public class AnimalAdapter extends ArrayAdapter<AnimalListResponse> {
    public AnimalAdapter(Context context, List<AnimalListResponse> animaux)
    {
        super(context, R.layout.animal_list_item,animaux);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // On inflate
        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        View row = inflater.inflate(R.layout.animal_list_item, parent, false);
        AnimalListResponse item = getItem(position);
        // On set les items
        row.findViewById(R.id.detailsButton).setTag(item);
        // remplir le layout avec les bonnes valeurs
        // NOM
        TextView nomAnimal = (TextView) row.findViewById(R.id.animal_nom);
        nomAnimal.setText(item.getNom());
        //TYPE
        TextView typeAnimal = (TextView) row.findViewById(R.id.animal_type);
        typeAnimal.setText(item.getType());
        row.setTag(item);
        return row;
    }
}
