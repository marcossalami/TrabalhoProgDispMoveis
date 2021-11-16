package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class formlogin extends AppCompatActivity {
    private TextView text_tela_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formlogin);


        iniciarComponentes();
        text_tela_cadastro.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {

                Intent Intent = new Intent(formlogin.this,formcadastro.class);
                startActivity(Intent);
            }
        });

    }

    private void iniciarComponentes() {
        text_tela_cadastro=findViewById(R.id.text_tela_cadastro);
    }


}