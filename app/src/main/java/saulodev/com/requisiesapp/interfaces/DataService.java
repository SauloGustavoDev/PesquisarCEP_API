package saulodev.com.requisiesapp.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import saulodev.com.requisiesapp.model.CEP;
import saulodev.com.requisiesapp.model.Photo;
import saulodev.com.requisiesapp.model.Postagem;

public interface DataService {

    @GET("{cep}/json/")
    Call<CEP> recuperarCEP(@Path("cep") String cep);

    @GET("/photos")
    Call<List<Photo>> recuperarPhoto();

    @POST("/posts")
    Call<Postagem> salvarPostagem(@Body Postagem postagem);
    /*
    @FormUrlEncoded
    @POST("/posts")
    Call<Postagem> salvarPostagem(
            @Field("userId")String userId,
            @Field("title")String title,
            @Field("body")String body
    );
     */
    //o PUT Basicamente sobrescreve a postagem 1 pela postagem 2, subtitui um objeto por outro
    @PUT("/posts/{id}")
    Call<Postagem> atualizarPostagem(@Path("id")int id, @Body Postagem postagem);
    //o PATCH Ele atualiza os campos que forem enviados, sem trocar um objeto por outro
    @PATCH("/posts/{id}")
    Call<Postagem> atualizarPostagemPatch(@Path("id")int id, @Body Postagem postagem);

    @DELETE("/posts/{id}")
    Call<Void> removerPostagem(@Path("id")int id);
}
