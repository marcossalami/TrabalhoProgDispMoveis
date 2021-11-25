package com.salami.projetobolos;

import static com.google.android.material.snackbar.Snackbar.LENGTH_SHORT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormCadastro extends AppCompatActivity {
    private EditText textnome, textemail, textsenha;
    private Button bt_cadastrar;
    String[] mensagens = {"preencha todos os campos","Cadastro Realizado"};
    String UsuarioID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);

        getSupportActionBar().hide();
        iniciarcomponentes();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = textnome.getText().toString();
                String email = textemail.getText().toString();
                String senha = textsenha.getText().toString();
                if( nome.isEmpty() || email.isEmpty()|| senha.isEmpty() ) {
                    Snackbar snackbar = Snackbar.make(v,mensagens[0], LENGTH_SHORT );
                    snackbar.setBackgroundTint(Color.BLACK);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();
                }
                else{
                    CadastrarUsuario(v);

               }


            }
        });

    }
    private void SalvarDadosUsuario(){
        String nome = textnome.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);

        UsuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference DocumentReference = db.collection("Usuarios").document(UsuarioID);
        DocumentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db","sucesso ao salvar");

            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_error","erro   ao salvar"+e.toString());

            }
        });

    }
    private void CadastrarUsuario(View v){
         String email = textemail.getText().toString();
         String senha = textsenha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if (task.isSuccessful()) {
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLACK);
                    snackbar.setTextColor(Color.WHITE);
                    snackbar.show();

                } else {
                    String erro;
                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = "digite uma senha com no minimo 6 caracteres";

                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Conta ja cadastrada";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "Email escrito incorreto";

                    } catch (Exception e) {

                    }
                }


            }
        });


    }
    private void iniciarcomponentes() {
        textnome = findViewById(R.id.receita);
        textemail = findViewById(R.id.textemail);
        textsenha = findViewById(R.id.textsenha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);
    }
}