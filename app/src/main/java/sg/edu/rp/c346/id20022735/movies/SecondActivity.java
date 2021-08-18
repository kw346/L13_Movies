package sg.edu.rp.c346.id20022735.movies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText etName, etDesc;
    Button btnIn, btnShowList;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setTitle(getResources().getText(R.string.title_activity_second));

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        btnIn = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        rg = findViewById(R.id.rgStars);

        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etName.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                if (name.length() == 0 || desc.length() == 0){
                    Toast.makeText(SecondActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                int stars = getStars();

                DBHelper dbh = new DBHelper(SecondActivity.this);
                long result = dbh.insertMovie(name, desc, stars);

                if (result != -1) {
                    Toast.makeText(SecondActivity.this, "Movie inserted", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etDesc.setText("");
                } else {
                    Toast.makeText(SecondActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                }


            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }


    private int getStars() {
        int stars = 1;
        switch (rg.getCheckedRadioButtonId()) {
            case R.id.radio1:
                stars = 1;
                break;
            case R.id.radio2:
                stars = 2;
                break;
            case R.id.radio3:
                stars = 3;
                break;
            case R.id.radio4:
                stars = 4;
                break;
            case R.id.radio5:
                stars = 5;
                break;
        }
        return stars;
    }

}
