package tn.esen.myloginapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class NewSongActivity extends AppCompatActivity {
    private EditText mArtist_edit_Txt ;
    private EditText mTitre_edit_Txt;
    private EditText mRéaliser_edit_Txt;
    private Spinner mCategorie_Spinner;
    private Button mAdd_btn;
    private Button mBack_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_song);


        mArtist_edit_Txt = (EditText) findViewById(R.id.artist_edittxt);
        mTitre_edit_Txt = (EditText) findViewById(R.id.Title_edittxt);
        mRéaliser_edit_Txt = (EditText) findViewById(R.id.Isr_edit_Txt);
        mCategorie_Spinner = (Spinner) findViewById(R.id.Song_Categories_Spinner);

        mAdd_btn = (Button) findViewById(R.id.update_song_btn);
        mBack_btn = (Button) findViewById(R.id.Back_btn);

        mAdd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song song = new song();
                song.setArtiste(mArtist_edit_Txt.getText().toString().trim());
                song.setTitre(mTitre_edit_Txt.getText().toString().trim());
                song.setRéaliser(mRéaliser_edit_Txt.getText().toString().trim());
                song.setCatégorie(mCategorie_Spinner.getSelectedItem().toString().trim());
                new FirebaseDatabaseBHelper().addSong(song, new FirebaseDatabaseBHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<tn.esen.myloginapp.song> Songs, List<String> Keys) {

                    }

                    @Override
                    public void DataIsInserted() {
                        Toast.makeText(NewSongActivity.this, "The Song record has " + "Been inserted Successfully ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        mBack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                return;
            }
        });
    }
}