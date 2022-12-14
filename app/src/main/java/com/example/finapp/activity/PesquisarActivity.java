package com.example.finapp.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finapp.R;
import com.example.finapp.Utils;
import com.example.finapp.helper.CategoriaDAO;
import com.example.finapp.helper.OperacaoDAO;
import com.example.finapp.model.Operacao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PesquisarActivity extends AppCompatActivity {

    Spinner spinner;
    TextView textViewData1, textViewData2, textViewSpinner;
    Button botao1, botao2;
    OperacaoDAO operacaoDAO;
    String data1, data2, categoria;
    public static List<Operacao> operacoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);
        spinner = findViewById(R.id.spinner);
        textViewData1 = findViewById(R.id.textView10);
        textViewData2 = findViewById(R.id.textView9);
        botao1 = findViewById(R.id.button2);
        botao2 = findViewById(R.id.button4);

        CategoriaDAO categoriaDAO = new CategoriaDAO(getApplicationContext());
        operacaoDAO = new OperacaoDAO(getApplicationContext());

        List<String> categorias = new ArrayList<>();
        categorias.add("Todos");
        categorias.add("Débito");
        categorias.add("Crédito");

        List<Operacao> operacoes = operacaoDAO.getAllOperacoes();

        ArrayAdapter categoriaAdapter = new ArrayAdapter(this, R.layout.spinner,categorias);
        spinner.setAdapter(categoriaAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                categoria = (String) adapterView.getSelectedItem();
                textViewSpinner = findViewById(R.id.textViewSpinner);
                if (categoria.equals("Débito")) {
                    textViewSpinner.setTextColor(Color.parseColor("#ff0000"));
                } else if (categoria.equals("Crédito")) {
                    textViewSpinner.setTextColor(Color.parseColor("#00ff00"));
                }
                Toast.makeText(PesquisarActivity.this, "Selecionado: " + categoria, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        View.OnClickListener showDatePicker = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final View vv = view;

                DatePickerDialog datePickerDialog = new DatePickerDialog(PesquisarActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        if(vv.getId() == R.id.button2){
                            data1 = day+"/"+(month+1)+"/"+year;
                            textViewData1.setText(data1);
                        }else{
                            data2 = day+"/"+(month+1)+"/"+year;
                            textViewData2.setText(data2);
                        }
                    }
                },Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        };
        botao1.setOnClickListener(showDatePicker);
        botao2.setOnClickListener(showDatePicker);
    }
    public void pesquisar(View view) throws ParseException {
        if(data1==null){
            Toast.makeText(PesquisarActivity.this,"Selecione a data de início. ",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(data2==null){
            Toast.makeText(PesquisarActivity.this,"Selecione a data de fim. ",Toast.LENGTH_SHORT).show();
            return ;
        }
        if(categoria==null){
            Toast.makeText(PesquisarActivity.this,"Selecione uma categoria. ",Toast.LENGTH_SHORT).show();
            return ;
        }
        Date date1, date2;
        try{
            date1 = Utils.stringToDate(data1);
            date2 = Utils.stringToDate(data2);
            operacoes = operacaoDAO.pesquisar(date1, date2, categoria);
            Intent i = new Intent(this, DadosPesquisaActivity.class);
            startActivity(i);

        }catch (Exception e){
            Toast.makeText(PesquisarActivity.this,"Selecione uma data válida. ",Toast.LENGTH_SHORT).show();
            return ;
        }

    }

    public void voltar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}