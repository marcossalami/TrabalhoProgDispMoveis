package com.salami.projetobolos;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextView txtreceita;
    private GridView gridView;
    private Button bt_deslogar;
    private TextView text_tela_cadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        bt_deslogar=findViewById(R.id.bt_deslogar);
        txtreceita=findViewById(R.id.receita);
        gridView=findViewById(R.id.GridView);
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        text_tela_cadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {

                Intent intent = new Intent(MainActivity.this,TelaPrincipal.class);
                startActivity(intent);
            }
        });

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,FormLogin.class);
                startActivity(intent);
                finish();
            }
        });
        int [] list = new int[]{
            R.drawable.cake1,R.drawable.cake2,R.drawable.cake3,
                R.drawable.cake4,R.drawable.cake5,R.drawable.cake6
        };
        gridView.setAdapter(new adapter(MainActivity.this,list));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        txtreceita.setText("Receita = farinha de trigo, leite, ovos e achocolatado em pó");
                    case 1:
                        txtreceita.setText("Receita = farinha de trigo, leite, ovos e morango");
                    case 2:
                        txtreceita.setText("Receita = farinha de trigo, leite, ovos e fuba");
                    case 3:
                        txtreceita.setText("Receita = farinha de trigo, leite, ovos e cenoura");
                    case 4:
                        txtreceita.setText("Receita = farinha de trigo, leite, ovos e chantilly");
                    case 5:
                        txtreceita.setText("Receita = farinha de trigo, leite, ovos e aveia");
                }
            }
        });


    }
}