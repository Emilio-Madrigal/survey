package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class preguntas extends AppCompatActivity {
    TextView preg1,preg2,preg3,preg4,preg5;
TextView preguntas;

String[] preguntitas={
        "1",
        "2",
        "3",
        "4",
        "5"
};

    ArrayList<Integer> respuestas = new ArrayList<>();
    int indicePregunta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        EdgeToEdge.enable (this);
        setContentView (R.layout.activity_preguntas);

        preg1 = findViewById(R.id.boton1);
        preg2 = findViewById(R.id.boton2);
        preg3 = findViewById(R.id.boton3);
        preg4 = findViewById(R.id.boton4);
        preg5 = findViewById(R.id.boton5);
        preguntas=findViewById (R.id.pregunta);

        preguntas.setText (preguntitas[indicePregunta]);
        preg1.setOnClickListener(v -> guardarRespuesta(1));
        preg2.setOnClickListener(v -> guardarRespuesta(2));
        preg3.setOnClickListener(v -> guardarRespuesta(3));
        preg4.setOnClickListener(v -> guardarRespuesta(4));
        preg5.setOnClickListener(v -> guardarRespuesta(5));

    }


    private void guardarRespuesta(int valor) {
        respuestas.add(valor);
        indicePregunta++;

        if (indicePregunta < preguntitas.length) {
            preguntas.setText(preguntitas[indicePregunta]);
        } else {
            // Encuesta terminada
            Toast.makeText(this, "Gracias por responder", Toast.LENGTH_SHORT).show();

            // Ejemplo: imprimir respuestas en consola
            for (int i = 0; i < respuestas.size(); i++) {
                System.out.println("Pregunta " + (i + 1) + ": " + respuestas.get(i));
            }

            Intent intent = new Intent (com.example.survey.preguntas.this,Enviar_datos.class);
            startActivity (intent);
            finish ();

            // Aquí puedes cambiar de pantalla o guardar datos
        }
    }
}