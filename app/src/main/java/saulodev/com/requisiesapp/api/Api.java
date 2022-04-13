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

public class Api {

    private Retrofit retrofit;
    private List<Photo> ListaFotos = new ArrayList<>();

    public Api() {
         retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                 // .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void recuperarCEPretrofit(TextView textoRecuperado, EditText edtDigitado){
        DataService dataService = retrofit.create(DataService.class);
        Call<CEP> call = dataService.recuperarCEP(edtDigitado.getText().toString());
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
        DataService service = retrofit.create(DataService.class);
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
    public void recuperarPostagensRetrofit(){

    }
}
