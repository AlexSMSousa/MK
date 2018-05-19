package br.com.alexcorporation.mockup.webService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alex on 17/05/2018.
 */

public class RetrofitConfig {

    private final Retrofit retrofit;

    //Realiza a configuração do Retrofit
    public RetrofitConfig() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://wsteste.devedp.com.br/Master/CidadeServico.svc/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public CityService getCitiesService(){
        return this.retrofit.create(CityService.class);
    }
}
