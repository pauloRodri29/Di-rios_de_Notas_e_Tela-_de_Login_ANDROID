package com.example.dm_atividades_n;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button but_TelaD = findViewById(R.id.but_TelaD), but_Tela_Lo = findViewById(R.id.but_Tela_Lo);

        but_TelaD.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                Intent tela_R = new Intent(MainActivity.this, Tela_Registrar_Notas.class);
                startActivity(tela_R);
                //
            }
        });
        but_Tela_Lo.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                Intent tela_L = new Intent(MainActivity.this, tela_login1.class);
                startActivity(tela_L);
                //but_Tela_Lo
            }
        });

    }
}
