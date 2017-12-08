package com.coderefer.rovcharacter;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.coderefer.rovcharacter.adapter.CharacterListAdapter;
import com.coderefer.rovcharacter.db.CharacterDbHelper;
import com.coderefer.rovcharacter.model.CharaterGame;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private CharacterDbHelper mHelper;
    private SQLiteDatabase mDb;

    private ArrayList<CharaterGame> mGameItemList = new ArrayList<>();
    private CharacterListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new CharacterDbHelper(this);
        mDb = mHelper.getReadableDatabase();

        loadDataFromDb();

        mAdapter = new CharacterListAdapter(
                this,
                R.layout.item,
                mGameItemList
        );

        ListView lv = findViewById(R.id.list_view);
        lv.setAdapter(mAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CharaterGame item = mGameItemList.get(position);
                String picture = item.picture;

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +picture));
                startActivity(intent);
            }
        });

        Button insertButon = findViewById(R.id.insert_button);
        insertButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText gameTitleEditText = findViewById(R.id.game_title_edit_text);
                EditText gamePositionEditText = findViewById(R.id.game_position_edit_text);

                // todo: เพิ่มการตรวจสอบ input

                String gameTitle = gameTitleEditText.getText().toString();
                String gamePositionx = gamePositionEditText.getText().toString();

                ContentValues cv = new ContentValues();
                cv.put(CharacterDbHelper.COL_NAME, gameTitle);
                cv.put(CharacterDbHelper.COL_POSITIONX, gamePositionx);
                cv.put(CharacterDbHelper.COL_PICTURE, "assets");

                mDb.insert(CharacterDbHelper.TABLE_NAME, null, cv);
                loadDataFromDb();
                mAdapter.notifyDataSetChanged();

                mDb.delete(
                        CharacterDbHelper.TABLE_NAME,
                        "title=? AND position=?",
                        new String[]{"TAARA", "TANK"}
                );
            }
        });

    } // ปิดเมธอด onCreate

    private void loadDataFromDb() {
        Cursor cursor = mDb.query(
                CharacterDbHelper.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mGameItemList.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(CharacterDbHelper.COL_ID));
            String title = cursor.getString(cursor.getColumnIndex(CharacterDbHelper.COL_NAME));
            String positionx = cursor.getString(cursor.getColumnIndex(CharacterDbHelper.COL_POSITIONX));
            String picture = cursor.getString(cursor.getColumnIndex(CharacterDbHelper.COL_PICTURE));

            CharaterGame item = new CharaterGame(title,positionx,picture,id);
            mGameItemList.add(item);
        }
    }
} // ปิดคลาส MainActivity
