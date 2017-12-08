package com.coderefer.rovcharacter.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderefer.rovcharacter.R;
import com.coderefer.rovcharacter.model.CharaterGame;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by User on 7/12/2560.
 */

public class CharacterListAdapter extends ArrayAdapter<CharaterGame> {

    private Context context;
    private int itemLayoutID;
    private ArrayList<CharaterGame> games;

    public CharacterListAdapter(Context context, int itemLayoutID, ArrayList<CharaterGame> game) {
        super(context, itemLayoutID, game);

        this.itemLayoutID = itemLayoutID;
        this.games = game;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemLayout = inflater.inflate(itemLayoutID, null);

        CharaterGame item = games.get(position);

        ImageView phoneImageView = itemLayout.findViewById(R.id.imageView);
        TextView phoneTitleTextView = itemLayout.findViewById(R.id.name_character_text_view);
        TextView phoneNumberTextView = itemLayout.findViewById(R.id.position_text_view);

        phoneTitleTextView.setText(item.name);
        phoneNumberTextView.setText(item.positionx);

        String pictureFileName = item.picture;

        AssetManager am = context.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            phoneImageView.setImageDrawable(drawable);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemLayout;
    }
}
