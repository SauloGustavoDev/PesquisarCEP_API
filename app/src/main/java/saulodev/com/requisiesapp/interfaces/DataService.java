package saulodev.com.requisiesapp.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import saulodev.com.requisiesapp.model.CEP;
import saulodev.com.requisiesapp.model.Photo;
import saulodev.com.requisiesapp.model.Postagem;

public interface DataService {

    @GET("{cep}/json/")
    Call<CEP> recuperarCEP(@Path("cep") String cep);

    @GET("/photos")
    Call<List<Photo>> recuperarPhoto();

    @GET("/posts")
    Call<List<Postagem>> recuperarPostagem();
}
