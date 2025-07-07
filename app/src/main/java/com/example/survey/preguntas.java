package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;

public class preguntas extends AppCompatActivity {
    TextView R1, R2, R3, R4, R5;
    TextView preguntas;

    String[] preguntitas = {
            "¿Cómo fue la calidad de los alimentos?",
            "¿Qué tan satisfecho estás con el sabor de tu platillo?",
            "¿Cómo calificarías el tamaño de la porción que recibiste?",
            "¿Qué tan satisfecho estás con la atención del personal?",
            "¿Te atendieron con rapidez?",
            "¿Cómo calificarías la amabilidad del personal?",
            "¿Cómo calificarías la higiene de los baños?",
            "¿Te sentiste cómodo durante tu visita?",
            "¿Cómo calificarías la limpieza y presentación del restaurante?",
            "¿Consideras que lo que pagaste valió la pena?",
            "¿Qué tan probable es que recomiendes este lugar a alguien más?",
            "¿Qué tan probable es que regreses a este restaurante?"
    };

    LinkedHashMap<String, Integer> respuestas = new LinkedHashMap<>();
    int indicePregunta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preguntas);

        R1 = findViewById(R.id.boton1);
        R2 = findViewById(R.id.boton2);
        R3 = findViewById(R.id.boton3);
        R4 = findViewById(R.id.boton4);
        R5 = findViewById(R.id.boton5);
        preguntas = findViewById(R.id.pregunta);

        preguntas.setText(preguntitas[indicePregunta]);

        R1.setOnClickListener(v -> guardarRespuesta(1));
        R2.setOnClickListener(v -> guardarRespuesta(2));
        R3.setOnClickListener(v -> guardarRespuesta(3));
        R4.setOnClickListener(v -> guardarRespuesta(4));
        R5.setOnClickListener(v -> guardarRespuesta(5));
    }

    private void guardarRespuesta(int valor) {
        respuestas.put(preguntitas[indicePregunta], valor);
        indicePregunta++;

        if (indicePregunta < preguntitas.length) {
            preguntas.setText(preguntitas[indicePregunta]);
        } else {
            Toast.makeText(this, "Gracias por responder", Toast.LENGTH_SHORT).show();

            JSONArray arrayRespuestas = new JSONArray();
            for (String pregunta : preguntitas) {
                arrayRespuestas.put(respuestas.get(pregunta));
            }

            JSONObject datos = new JSONObject();
            try {
                datos.put("respuestas", arrayRespuestas);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            enviarDatos(datos);
        }
    }
    private void enviarDatos(JSONObject datos) {
        String url = "https://script.google.com/macros/s/AKfycbxtbC00dOdL6oVJpDfh44OLuRYlDE6KVvnBhGuPKa868_qxxZShAcIJQTTCIXvpLGsl/exec?key=EmilioEsELAmo126."; // Reemplaza por tu URL real

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, datos,
                response -> {
                    Toast.makeText(this, "Datos enviados correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(preguntas.this, Enviar_datos.class);
                    startActivity(intent);
                    finish();
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Error al enviar datos", Toast.LENGTH_LONG).show();
                }
        );

        queue.add(request);
    }
}
