package br.com.alexcorporation.mockup.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import br.com.alexcorporation.mockup.libs.MyLib;
import br.com.alexcorporation.mockup.model.City;
import br.com.alexcorporation.mockup.R;

public class ScoreResultActivity extends AppCompatActivity {

    private City city;
    private TextView tvMsg;
    private TextView tvScore;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_result);

        MyLib myLib = MyLib.myLib;

        Toolbar toolbar = findViewById(R.id.toolbar_ScoreResult);
        setSupportActionBar(toolbar);

        //Cria o botão voltar da toolbar
        myLib.createButtonUp(getSupportActionBar());

        tvMsg = findViewById(R.id.tvMsg_ScoreResult);
        tvScore = findViewById(R.id.tvScore_ScoreResult);

        //Recebe a cidade selecionada
        Intent intent = getIntent();
        city = (City) intent.getSerializableExtra("CITY_SCORE");

        tvMsg.setText(getResources().getString(R.string.msg_result_score)+" "+city.getNome()
                +" "+ getResources().getString(R.string.msg_result_score_is));
        tvScore.setText(city.getPontos());

    }
}
