package com.example.angendaamigos;

import androidx.annotation.Nullable;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class agregar_amigos extends Activity{



    DB usuarios;
    String accion="nuevo";
    String id="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_amigos);
        mostrardatos();


    }
    public void mostrardatos(){
        try {
            Bundle bundle = getIntent().getExtras();
            accion = bundle.getString("accion");
            if (accion.equals("modificar")) {
                id = bundle.getString("id");
                String user[] = bundle.getStringArray("user");
                TextView tempVal = (TextView) findViewById(R.id.txtnombre);
                tempVal.setText(user[0].toString());
                tempVal = (TextView) findViewById(R.id.txtdireccion);
                tempVal.setText(user[1].toString());
                tempVal = (TextView) findViewById(R.id.txtTelefono);
                tempVal.setText(user[2].toString());
            }
        }catch (Exception e){
            Toast.makeText(agregar_amigos.this, "Error: "+ e.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
        }

}
    public void guardar_amigo(View v){
        try{
            TextView tempVal = (TextView)findViewById(R.id.txtnombre);
            String nom = tempVal.getText().toString();
            tempVal = (TextView)findViewById(R.id.txtdireccion);
            String dir = tempVal.getText().toString();
            tempVal = (TextView)findViewById(R.id.txtTelefono);
            String tel = tempVal.getText().toString();
            usuarios = new DB(agregar_amigos.this, "",null,1);
            usuarios.guardarUsuario(nom, dir, tel, accion, id);
            Toast.makeText(agregar_amigos.this, "Listo, amigo registrado con exito", Toast.LENGTH_LONG).show();
                    Intent imostrar= new Intent(agregar_amigos.this,
                            MainActivity.class);
            startActivity(imostrar);
        }catch(Exception ex){
            Toast.makeText(agregar_amigos.this, "Error: "+
                    ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}