package tn.esen.myloginapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);


        Button Profile_btn = findViewById(R.id.hometoProf_btn);
        Profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SongListActivity.this, AccountActivity.class));
                finish();
            }
        });

        mRecyclerView =(RecyclerView) findViewById(R.id.recycler_view_songs);
        new  FirebaseDatabaseBHelper().readSongs(new FirebaseDatabaseBHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<song> Songs, List<String> Keys) {
                 new RecyclerView_Config().SetConfig(mRecyclerView,SongListActivity.this,Songs,Keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.songlist_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.New_Song ){
            startActivity(new Intent (this , NewSongActivity.class));
            return true;
        }
        /*switch(item.getItemId()) {
            case R.id.New_Song:
                startActivity(new Intent (this , NewSongActivity.class));
                return true;

        }*/
        return super.onOptionsItemSelected(item);
    }
}