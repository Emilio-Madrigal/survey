package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        validarAcceso();
    }

    private void validarAcceso() {
        String clave = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        String fabricante = android.os.Build.MANUFACTURER;
        String modelo = android.os.Build.MODEL;
        String nombre = android.os.Build.DEVICE;
        String version = android.os.Build.VERSION.RELEASE;

        String descripcion = fabricante + " " + modelo + " (" + nombre + ")";
        String url = "https://script.google.com/macros/s/AKfycbxf_VWkj1gdooJ4KcrxALk9UMwZKKvnBXHod3B4vSiTmq-QvM0d7WEt6IDq5CEm2MACZA/exec" +
                "?clave=" + clave + "&dispositivo=" + descripcion.replace(" ", "%20") + "&version=" + version;

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String estado = response.getString("estado");
                        if (estado.equals("activo")) {
                            // Si está activo, entrar a la app
                            Intent intent = new Intent(Splash.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Si está bloqueado
                            Toast.makeText(this, "Acceso denegado. Contacta al administrador.", Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Error al leer respuesta del servidor", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "No se pudo conectar al servidor", Toast.LENGTH_LONG).show();
                }
        );

        queue.add(request);
    }
}
