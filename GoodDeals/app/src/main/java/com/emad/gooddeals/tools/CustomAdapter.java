package com.emad.gooddeals.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

import com.emad.gooddeals.DetailOffer;
import com.emad.gooddeals.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import data.stevo.SQlite.Offres;

/**
 * Created by stevo on 07/03/16.
 * classe permettant l'affichage de nos items dans une listView
 */
public class CustomAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    ArrayList<Offres> myList = new ArrayList<Offres>();
    Context context;
    Activity activity;
    private String[] bgColors;

    // on passe le context afin d'obtenir un LayoutInflater pour utiliser notre
    // row_layout.xml
    // on passe les valeurs de notre à l'adapter
    public CustomAdapter(Context context, ArrayList<Offres> myList,Activity activity) {
        this.myList = myList;
        this.context = context;
        this.activity=activity;
        bgColors = activity.getBaseContext().getResources().getStringArray(R.array.offerscolor);
    }

    // retourne le nombre d'objet présent dans notre liste
    @Override
    public int getCount() {
        return myList.size();
    }

    // retourne un élément de notre liste en fonction de sa position
    @Override
    public Offres getItem(int position) {
        return myList.get(position);
    }

    // retourne l'id d'un élément de notre liste en fonction de sa position
    @Override
    public long getItemId(int position) {
        return myList.indexOf(getItem(position));
    }

    // retourne la vue d'un élément de la liste
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder = null;

        // au premier appel ConvertView est null, on inflate notre layout
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.affichageitem, parent, false);

            // nous plaçons dans notre MyViewHolder les vues de notre layout
            mViewHolder = new MyViewHolder();

            mViewHolder.textViewTitre = (TextView) convertView
                    .findViewById(R.id.textViewTitre);
            mViewHolder.textViewDesc = (TextView) convertView
                    .findViewById(R.id.textViewDesc);
            mViewHolder.textViewDate = (TextView) convertView
                    .findViewById(R.id.textViewDate);
            mViewHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.imageViewItem);

            // nous attribuons comme tag notre MyViewHolder à convertView
            convertView.setTag(mViewHolder);
        } else {
            // convertView n'est pas null, nous récupérons notre objet MyViewHolder
            // et évitons ainsi de devoir retrouver les vues à chaque appel de getView
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        // nous récupérons l'item de la liste demandé par getView
        Offres offre = (Offres) getItem(position);

        // nous pouvons attribuer à nos vues les valeurs de l'élément de la liste
        mViewHolder.textViewTitre.setText(offre.getTitre());
        mViewHolder.textViewDesc.setText(offre.getDescription());
        mViewHolder.imageView.setImageBitmap(offre.getBipmapImage());
        SimpleDateFormat sd = new SimpleDateFormat();

        if(offre.getDateFin()!=null) {
            mViewHolder.textViewDate.setText(offre.getDateFin().toString());
        }
        // nous retournos la vue de l'item demandé
        String color = bgColors[position % bgColors.length];
        mViewHolder.textViewDesc.setBackgroundColor(Color.parseColor(color));
        return convertView;
    }

    // MyViewHolder va nous permettre de ne pas devoir rechercher
    // les vues à chaque appel de getView, nous gagnons ainsi en performance
    private class MyViewHolder {
        TextView textViewTitre, textViewDesc,textViewDate;
        ImageView imageView;


    }

    // nous affichons un Toast à chaque clic sur un item de la liste
    // nous récupérons l'objet grâce à sa position
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        /**
         *
         * CODE AJOUTER ......................................................
         * */
        Intent intent=new Intent(activity,DetailOffer.class);
        intent.putExtra("image",getItem(position).getBipmapImage());
        intent.putExtra("titre", getItem(position).getTitre());
        intent.putExtra("description",getItem(position).getDescription());
        intent.putExtra("categorie",getItem(position).getCategorie());
        intent.putExtra("magasin",getItem(position).getMagasin());
        Log.v("custommmm",getItem(position).getMagasin());
        intent.putExtra("date",getItem(position).getDateFin().toString());
        intent.putExtra("latitude",getItem(position).getLatitude());
        intent.putExtra("longitude",getItem(position).getLongitude());
        activity.startActivity(intent);

    }

    // Clean all elements of the recycler
    public void clear() {
        myList.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(ArrayList<Offres> List ) {
        myList.addAll(List);
        notifyDataSetChanged();
    }

}
