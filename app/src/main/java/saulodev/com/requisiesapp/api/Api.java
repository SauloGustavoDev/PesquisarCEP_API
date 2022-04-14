package saulodev.com.requisiesapp.api;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import saulodev.com.requisiesapp.interfaces.DataService;
import saulodev.com.requisiesapp.model.CEP;
import saulodev.com.requisiesapp.model.Photo;
import saulodev.com.requisiesapp.model.Postagem;

public class Api {

    private Retrofit retrofit;
    private List<Photo> ListaFotos = new ArrayList<>();
    private DataService service;

    public Api() {
         retrofit = new Retrofit.Builder()
                //.baseUrl("https://viacep.com.br/ws/")
                 .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                service = retrofit.create(DataService.class);
    }

    public void recuperarCEPretrofit(TextView textoRecuperado, EditText edtDigitado){
        Call<CEP> call = service.recuperarCEP(edtDigitado.getText().toString());
        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body(); 
                    String textoFormatado = ("CEP: " + cep.getCep()+"\nLocalidade: "+
                            cep.getLocalidade() + "\nBairro: " + cep.getBairro() +
                            "\nEndereço: " + cep.getLogradouro() +
                            "\nUF: "+ cep.getUf() );
                    textoRecuperado.setText(textoFormatado);
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }

    public void recuperarListaPhoto(){
        Call<List<Photo>> call = service.recuperarPhoto();

        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    ListaFotos = response.body();
                    for(int i=0; i<ListaFotos.size(); i++){
                        Photo foto = ListaFotos.get(i);
                        Log.d("Resultado do console:", "Resultado" + foto.getAlbumId() + "/" + foto.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });

    }
    public void salvarPostagem(TextView textoRecuperado, int userId, String title, String corpo){

        //Configura objeto postagem
        Postagem postagem = new Postagem(userId, title, corpo);

        //recupera o serviço e salva postagem
        Call<Postagem> call = service.salvarPostagem(postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if(response.isSuccessful()){
                    Postagem postagemResposta = response.body();
                    textoRecuperado.setText(
                                            "Código: " + response.code() +
                                            "\nid: " + postagemResposta.getId() +
                                            "\nTitulo: " + postagemResposta.getTitle()
                                            );

                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    public void atualizarPostagem(TextView textoRecuperado){
        Postagem postagem = new Postagem(12, null, "corpo");
        Call<Postagem> call = service.atualizarPostagem(1, postagem);
        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()){
                    Postagem postagemResposta = response.body();
                    textoRecuperado.setText(
                            "Código: " + response.code() +
                                    "\nid: " + postagemResposta.getId() +
                                    "\nuserId: " + postagemResposta.getUserId() +
                                    "\nTitulo: " + postagemResposta.getTitle()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
}
public void removerPostagem(TextView textoRecuperado){
        Call<Void> call = service.removerPostagem(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    textoRecuperado.setText("Objeto Deletado com sucesso! \nStatus: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
}
}
