package com.example.survey;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Enviar_datos extends AppCompatActivity {
Button finalizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        EdgeToEdge.enable (this);
        setContentView (R.layout.activity_enviar_datos);

        finalizar =findViewById (R.id.finalizar);

        finalizar.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (Enviar_datos.this,MainActivity.class );
                startActivity (intent);
            }
        });
    }
}