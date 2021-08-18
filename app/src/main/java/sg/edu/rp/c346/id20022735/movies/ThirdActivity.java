package sg.edu.rp.c346.id20022735.movies;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etDesc;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getResources().getText(R.string.title_activity_third));

        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
        rb4 = (RadioButton) findViewById(R.id.radio4);
        rb5 = (RadioButton) findViewById(R.id.radio5);
        rg = (RadioGroup) findViewById(R.id.rgStars);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.etName);
        etDesc = (EditText) findViewById(R.id.etDesc);

        Intent i = getIntent();
        final Movies currentMovie = (Movies) i.getSerializableExtra("m");

        etID.setText(currentMovie.getId()+"");
        etName.setText(currentMovie.getName());
        etDesc.setText(currentMovie.getDescription());

        switch (currentMovie.getStars()){
            case 5: rb5.setChecked(true);
                    break;
            case 4: rb4.setChecked(true);
                    break;
            case 3: rb3.setChecked(true);
                    break;
            case 2: rb2.setChecked(true);
                    break;
            case 1: rb1.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentMovie.setName(etName.getText().toString().trim());
                currentMovie.setDescription(etDesc.getText().toString().trim());

                int selectedRB = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(selectedRB);
                if (rb.getText().toString().equals("Awesome")){
                    currentMovie.setStars(5);
                }
                else if (rb.getText().toString().equals("Good")){
                    currentMovie.setStars(4);
                }
                else if (rb.getText().toString().equals("So-So")){
                    currentMovie.setStars(3);
                }
                else if (rb.getText().toString().equals("Bad")){
                    currentMovie.setStars(2);
                }
                else {
                    currentMovie.setStars(1);
                }

                int result = dbh.updateMovie(currentMovie);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Movie updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                int result = dbh.deleteMovie(currentMovie.getId());

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);


                if (result>0){
                    String resname = currentMovie.getName();
                    myBuilder.setTitle("Danger");
                    myBuilder.setMessage("Are you sure you want to delete "+resname);
                    myBuilder.setCancelable(false);
                    myBuilder.setPositiveButton("Cancel", null);
                    myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ThirdActivity.this, "Movie deleted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent();
                            setResult(RESULT_OK);
                            finish();
                        }
                    });
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                } else {
                    Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Do Not Discard", null);
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }


}