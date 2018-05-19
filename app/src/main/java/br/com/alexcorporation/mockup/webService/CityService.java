package br.com.alexcorporation.mockup.webService;

import java.util.List;

import br.com.alexcorporation.mockup.model.City;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Alex on 17/05/2018.
 */

public interface CityService {

    //Configuração dos metodos Http

    @GET("BuscaTodasCidades")
    Call<List<City>> buscaLista();

    @POST("BuscaPontos")
    Call <Integer> buscaPontos(@Body City city);

}
