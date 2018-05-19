package br.com.alexcorporation.mockup.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.alexcorporation.mockup.libs.MyLib;
import br.com.alexcorporation.mockup.model.City;
import br.com.alexcorporation.mockup.R;
import br.com.alexcorporation.mockup.view.adapter.RecyclerAdapter;
import br.com.alexcorporation.mockup.webService.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityResultActivity extends AppCompatActivity {

    private RecyclerView rvCities;
    private RecyclerAdapter recyclerAdapter;
    private List<City> cityList;

    private City citySearch;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_result);

        MyLib myLib = MyLib.myLib;

        Toolbar toolbar = findViewById(R.id.toolbar_CityResult);
        setSupportActionBar(toolbar);

        myLib.createButtonUp(getSupportActionBar());

        rvCities = findViewById(R.id.rvListResult_Result);
        progressBar = findViewById(R.id.progressBar_Result);

        //Recebe os dados de busca como um objeto City
        Intent intent = getIntent();
        citySearch = (City) intent.getSerializableExtra("CITY_SEARCH");

        //alterna entre a visibilidade do conteúdo da tela e o progressBar
        rvCities.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        }

    @Override
    protected void onResume() {
        super.onResume();

        Call<List<City>> call = new RetrofitConfig().getCitiesService().buscaLista();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(@NonNull Call<List<City>> call, @NonNull Response<List<City>> response) {
                if(response.isSuccessful()){
                    List<City> fullList = response.body();

                    //Filtra lista completa
                    cityList = filterList(fullList);

                    //Inicializa o recyclerView
                    setupRecycler();
                    //alterna entre a visibilidade do conteúdo da tela e o progressBar
                    rvCities.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(@NonNull Call<List<City>> call, @NonNull Throwable t) {
                Log.e("Erro: ", t.getMessage());
                //alterna entre a visibilidade do conteúdo da tela e o progressBar
                rvCities.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    //Método de inicialização dos procedimentos necessários para a criação do recyclerView
    private void setupRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvCities.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(cityList, this);
        rvCities.setAdapter(recyclerAdapter);
    }


    //Busca pela cidade e/ou estado específico
    protected List<City> filterList (List<City> fullList){
        String searchCity = citySearch.getNome();
        String searchState = citySearch.getEstado();

        if(searchCity.isEmpty() && searchState.isEmpty()){
            return fullList;
        }else {
            List<City> listFilterState = new ArrayList<>();
            List<City> listFilterCity = new ArrayList<>();

            for (City city : fullList) {
                if (city.getEstado().toUpperCase().contains(searchState)) {
                    listFilterState.add(city);
                }
            }
            if (listFilterState.isEmpty()) {
                for (City city : fullList) {
                    if (city.getNome().toUpperCase().contains(searchCity)) {
                        listFilterCity.add(city);
                    }
                }
            } else {
                for (City city : listFilterState) {
                    if (city.getNome().toUpperCase().contains(searchCity)) {
                        listFilterCity.add(city);
                    }
                }
            }
            return listFilterCity;
        }
    }

}
