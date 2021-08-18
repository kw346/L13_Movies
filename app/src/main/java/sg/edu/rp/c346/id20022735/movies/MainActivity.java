package sg.edu.rp.c346.id20022735.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Movies> movieList;
    CustomAdapter adapter;
    int requestCode = 9;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getResources().getText(R.string.title_activity_main));

        lv = (ListView) this.findViewById(R.id.lv);
        btn = (Button) this.findViewById(R.id.btnadd);

        DBHelper dbh = new DBHelper(this);
        movieList = dbh.getAllMovies();
        dbh.close();

        adapter = new CustomAdapter(this, R.layout.row, movieList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, ThirdActivity.class);
                i.putExtra("m", movieList.get(position));
                startActivityForResult(i, requestCode);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == this.requestCode && resultCode == RESULT_OK){
            DBHelper dbh = new DBHelper(this);
            movieList.clear();
            movieList.addAll(dbh.getAllMovies());
            dbh.close();
            adapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}