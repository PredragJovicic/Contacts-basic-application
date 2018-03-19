package brzocitanje.aplikacija.moji.contactme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class NewContact extends AppCompatActivity {

    private EditText namet,surnamet,telt;
    private Button addt;
    private Database database;
    private ArrayList<Dataitems> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mew_contact);


        namet = (EditText)findViewById(R.id.newname);
        surnamet = (EditText)findViewById(R.id.newsurname);
        telt = (EditText)findViewById(R.id.newtel);
        addt = (Button) findViewById(R.id.addnew);

        database = new Database();

        items = database.getItems();;

        Log.i("Items"," "+items.size());
        
        addt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                String namev = namet.getText().toString().trim();
                String surnamev = surnamet.getText().toString().trim();
                String telv = telt.getText().toString().trim();


                if(namev.length() >0 && surnamev.length() >0 && telv.length() >0 ){


                    items.add(new Dataitems("user_avatar1",namev, surnamev,telv,false));

                    database.setItems(items);

                    Log.i("Items-after"," "+items.size());

                    namet.setText("");
                    surnamet.setText("");
                    telt.setText("");
                    Toast.makeText(NewContact.this, "Success adding contact", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(NewContact.this, "You have empty fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(NewContact.this,MainActivity.class));
        finish();
    }

}
