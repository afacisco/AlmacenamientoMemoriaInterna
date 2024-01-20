package com.example.almacenamientomemoriainterna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
Autor: Juan Francisco Sánchez González
Fecha: 15/01/2024
Clase: Actividad que almacena en la memoria interna del dispositivo los datos de los campos de texto.
 Cada vez que se inicia la aplicación coloca en los controles la última informnación ingresada.
 Se dispone de un botón para almacenar los datos y finalizar el programa.
*/

public class MainActivity extends AppCompatActivity {

    // Nombre fichero
    private final String NOMBRE_FICHERO = "datos.txt";
    private Button boton;
    private EditText nombreEt;
    private EditText telefonoEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instancia componentes de la interfaz
        boton = (Button) findViewById(R.id.button);
        nombreEt = (EditText) findViewById(R.id.editTextTextPersonName);
        telefonoEt = (EditText) findViewById(R.id.editTextPhone);

        // Leemos los datos guardados en el fichero y se los asignamos a los controles
        try {
            BufferedReader fich = new BufferedReader(new InputStreamReader(openFileInput(NOMBRE_FICHERO)));
            String textoFich = fich.readLine();
            nombreEt.setText(textoFich);
            textoFich = fich.readLine();
            telefonoEt.setText(textoFich);
            fich.close();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }

        // Listener del botón
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Guardamos en la memoria interna del dispositivo y salimos del aplicativo
                try {
                    OutputStreamWriter fich = new OutputStreamWriter(openFileOutput(NOMBRE_FICHERO, Context.MODE_PRIVATE));
                    fich.write(nombreEt.getText().toString() + "\n");
                    fich.write(telefonoEt.getText().toString());
                    fich.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                finish();
            }
        });
    }
}