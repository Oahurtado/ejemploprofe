package com.example.oscar.firebase2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
  //  public JSONObject obj;

    FirebaseDatabase database = FirebaseDatabase.getInstance();//obtengo una instancia luego de conectarse a firebase
    //para leer y escribir en la base de datos, necesitas una instancia de Database
    DatabaseReference myRef = database.getReference();
    EditText mostrar,nombre,apellido,contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mostrar=(EditText)findViewById(R.id.edireci);
        nombre =(EditText)findViewById(R.id.edinom);
        apellido=(EditText)findViewById(R.id.ediape);
        contraseña=(EditText)findViewById(R.id.edicontra);
    }

    public void Excribir (View b){
        // Write a message to the database
        String nom = nombre.getText().toString().trim();// trae lo que hay en nombre edinomb
        String ape= apellido.getText().toString().trim();
        String contr=contraseña.getText().toString().trim();

        if(nom.matches("")||ape.matches("")||contr.matches("")){
            Toast.makeText(this,"Debe escribir algo para guardar",Toast.LENGTH_LONG).show();
        }else{
            myRef.child("Usuario").child(nom).setValue(nom);
            myRef.child("Usuario").child(ape).setValue(ape);
            myRef.child("Usuario").child(contr).setValue(contr);
         //   myRef.child("Usuario").setValue(valor);

        }
        //myRef.setValue("Hello, World!");
    }
    public void LeerFirebase(View g){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> value =(Map<String,Object>) dataSnapshot.getValue();
                mostrar.setText("  "+value.size()+" "+value.containsKey("chats"));
                mostrar.append("  "+value.values());
               /* JSONObject objAux=(JSONObject) value.values();
                obj=objAux;*/
            }

            @Override
            public void onCancelled(DatabaseError error) {
                mostrar.setText(""+error.toException());

            }
        });


    }

}
