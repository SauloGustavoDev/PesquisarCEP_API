package saulodev.com.requisiesapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import saulodev.com.requisiesapp.model.CEP;

public interface CEPService {

    @GET("{cep}/json/")
    Call<CEP> recuperarCEP(@Path("cep") String cep);

}
