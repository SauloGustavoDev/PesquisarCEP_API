package saulodev.com.requisiesapp.api;

import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import saulodev.com.requisiesapp.interfaces.CEPService;
import saulodev.com.requisiesapp.model.CEP;

public class Api {

    private Retrofit retrofit;

    public Api() {
         retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void recuperarCEPretrofit(TextView textoRecuperado, EditText edtDigitado){
        CEPService cepService = retrofit.create(CEPService.class);
        Call<CEP> call = cepService.recuperarCEP(edtDigitado.getText().toString());
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
}
