package com.coderefer.rovcharacter;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.EasyEditSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.coderefer.rovcharacter.db.CharacterDbHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 7/12/2560.
 */

public class CharacterActivity extends AppCompatActivity  {
    String imgGame;
    private CharacterDbHelper dbGame;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_detail);

        dbGame = new CharacterDbHelper(getApplicationContext());
        db = dbGame.getWritableDatabase();

        Intent intent = getIntent();
        imgGame = intent.getStringExtra("position");
        System.out.print(imgGame);
        String imgFileName =imgGame.substring(0, imgGame.indexOf("."));
        ImageView imgView = (ImageView) findViewById(R.id.game_img);
        System.out.println(imgFileName);
        int imgId = getApplicationContext().getResources().getIdentifier("drawable/" + imgFileName, null, getApplicationContext().getPackageName());
        imgView.setImageResource(imgId);

    }

}
