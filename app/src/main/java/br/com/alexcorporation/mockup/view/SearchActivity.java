package br.com.alexcorporation.mockup.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.alexcorporation.mockup.model.City;
import br.com.alexcorporation.mockup.R;
import br.com.alexcorporation.mockup.webService.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private EditText etState;
    private EditText etCity;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etCity = findViewById(R.id.etCity_Search);
        etState = findViewById(R.id.etState_Search);
        cardView = findViewById(R.id.cv_Search);

    }

   //Enviar as informações de busca para a classe CityResultActivity
    public void doSearch(View view) {

        String searchCity = etCity.getText().toString().toUpperCase();
        String searchState = etState.getText().toString().toUpperCase();

        City city = new City(searchCity, searchState);

        Intent intent = new Intent(SearchActivity.this, CityResultActivity.class);
        intent.putExtra("CITY_SEARCH", (city));
        startActivity(intent);

    }

}
