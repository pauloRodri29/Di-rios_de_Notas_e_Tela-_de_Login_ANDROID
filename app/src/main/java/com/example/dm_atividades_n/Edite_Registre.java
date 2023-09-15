package com.example.dm_atividades_n;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.content.Intent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Edite_Registre extends AppCompatActivity {
    private String no_aluno = "";
    private Boolean validator = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_registre);
        Button button_voltar = findViewById(R.id.button_voltar), button_salvar = findViewById(R.id.button_salvar), button_excluir = findViewById(R.id.button_excluir);
        EditText search_aluno = findViewById(R.id.search_aluno), editText_N1 = findViewById(R.id.editText_N1), editText_N2 = findViewById(R.id.editText_N2), editText_N3 = findViewById(R.id.editText_N3);
        Button button_buscar = findViewById(R.id.button_buscar);

        button_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltar = new Intent(Edite_Registre.this, Tela_de_Registros.class);
                startActivity(voltar);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("all_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Map<String, ?> all_dados = sharedPreferences.getAll();

        button_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_aluno = search_aluno.getText().toString().trim();
                for (Map.Entry<String, ?> dado : all_dados.entrySet()) {
                    String chave = dado.getKey();
                    if (chave.equals(no_aluno)) {
                        Toast.makeText(Edite_Registre.this, "Registro encontrado", Toast.LENGTH_SHORT).show();

                        String valor = dado.getValue().toString();
                        try {
                            JSONObject alunoJson = new JSONObject(valor);
                            String nota1 = alunoJson.getString("nota1");
                            String nota2 = alunoJson.getString("nota2");
                            String nota3 = alunoJson.getString("nota3");

                            editText_N1.setText(nota1);
                            editText_N2.setText(nota2);
                            editText_N3.setText(nota3);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Edite_Registre.this, "Erro ao acessar as notas", Toast.LENGTH_SHORT).show();
                        }
                        validator = true;
                    }else{
                        editText_N1.setText("");
                        editText_N2.setText("");
                        editText_N3.setText("");
                        validator = false;

                    }
                }
            }
        });

        button_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Map.Entry<String, ?> dado : all_dados.entrySet()) {
                    String chave = dado.getKey();
                    if (chave.equals(no_aluno)) {
                        editor.remove(chave);
                        String nome = search_aluno.getText().toString();
                        String nota1AR = editText_N1.getText().toString();
                        String nota2AR = editText_N2.getText().toString();
                        String nota3AR = editText_N3.getText().toString();

                        JSONObject notas = new JSONObject();
                        try {
                            notas.put("nota1", String.valueOf(nota1AR));
                            notas.put("nota2", String.valueOf(nota2AR));
                            notas.put("nota3", String.valueOf(nota3AR));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Edite_Registre.this, "Erro ao editar as notas", Toast.LENGTH_SHORT).show();
                        }
                        editor.putString(nome, notas.toString());
                        editor.apply();
                        search_aluno.setText("");
                        editText_N1.setText("");
                        editText_N2.setText("");
                        editText_N3.setText("");
                        Toast.makeText(Edite_Registre.this, "Salvo", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Edite_Registre.this, "Nada para ser Salvo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        button_excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aluno = search_aluno.getText().toString();
                if(validator == true){
                    View anchorView = findViewById(R.id.button_excluir);
                    View popupView = getLayoutInflater().inflate(R.layout.activity_poup_up, null);
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    PopupWindow popupWindow = new PopupWindow(
                            popupView,
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                    );
                    popupWindow.showAsDropDown(anchorView, 0, 0);
                    Button but_nao = popupView.findViewById(R.id.but_nao), but_sim = popupView.findViewById(R.id.but_sim);

                    but_nao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });

                    but_sim.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editor.remove(aluno);
                            editor.apply();
                            editText_N1.setText("");
                            editText_N2.setText("");
                            editText_N3.setText("");
                            search_aluno.setText("");
                            Toast.makeText(Edite_Registre.this, "Apagado com sucesso", Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();
                            validator = false;
                        }

                    });
                }if(aluno.isEmpty() ||validator == false){
                    Toast.makeText(Edite_Registre.this, "Sem registro do aluno para ser apagado!", Toast.LENGTH_SHORT).show();

                }
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

            }


        });
    }
}

