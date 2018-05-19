package br.com.alexcorporation.mockup.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import br.com.alexcorporation.mockup.R;
import br.com.alexcorporation.mockup.model.City;
import br.com.alexcorporation.mockup.view.ScoreResultActivity;
import br.com.alexcorporation.mockup.webService.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alex on 17/05/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HolderCity> {

    private final List<City> cityList;
    protected final Activity activity;

    public RecyclerAdapter(List<City> cityList, Activity activity) {
        this.cityList = cityList != null ? cityList : new ArrayList<>();
        this.activity = activity;
    }

    @NonNull
    @Override
    public HolderCity onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderCity(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_result, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCity holder, int position) {
       City city = cityList.get(position);
       holder.tvCity.setText(city.getNome());
       holder.tvState.setText(city.getEstado());
    }


    @Override
    public int getItemCount() {
        return  cityList.size();
    }


    protected class HolderCity extends RecyclerView.ViewHolder{

        protected TextView tvCity;
        protected TextView tvState;
        protected LinearLayout linearLayout;
        private City city;


        private HolderCity(final View itemView) {
            super(itemView);

            tvCity = itemView.findViewById(R.id.tvCity_Result);
            tvState = itemView.findViewById(R.id.tvState_Result);
            linearLayout = itemView.findViewById(R.id.ll_Result);

            //Pega o click do card selecionado
            linearLayout.setOnClickListener(v -> {
                    searchScore();
            });
        }

        //Busca no servidor a pontuação referente àquela cidade
        private void searchScore() {

            city = new City(tvCity.getText().toString(), tvState.getText().toString());

            Call<Integer> call = new RetrofitConfig().getCitiesService().buscaPontos(city);
            call.enqueue(new Callback<Integer>() {

                @Override
                public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                    if(response.isSuccessful()){

                        int score = response.body();

                        city.setPontos(String.valueOf(score));

                        Intent intent = new Intent(activity, ScoreResultActivity.class);
                        intent.putExtra("CITY_SCORE", city);
                        activity.startActivity(intent);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                    Log.e("Erro: ", t.getMessage());
                }
            });
        }
    }
}
