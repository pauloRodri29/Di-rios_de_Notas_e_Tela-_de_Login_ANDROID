package com.example.dm_atividades_n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tela_login1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login1);
        Button but_entrar = findViewById(R.id.but_entrar);
        but_entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent tela_L = new Intent(tela_login1.this, MainActivity.class);
                startActivity(tela_L);
                //but_Tela_Lo
            }
        });
    }
}