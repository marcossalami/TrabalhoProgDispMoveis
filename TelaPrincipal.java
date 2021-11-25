package com.salami.projetobolos;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaPrincipal extends AppCompatActivity {
    private TextView nomeusuario, emailusuario;
    private Button bt_deslogar;
    private Button bt_voltar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String UsuarioID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);
        getSupportActionBar().hide();
        IniciarComponentes();
        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaPrincipal.this,FormLogin.class);
                startActivity(intent);
                finish();
            }
        });
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaPrincipal.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void IniciarComponentes(){
        nomeusuario=findViewById(R.id.iconeuser);
        emailusuario=findViewById(R.id.email);
        bt_deslogar=findViewById(R.id.bt_deslogar);
        bt_voltar=findViewById(R.id.bt_Voltar);
    }
}