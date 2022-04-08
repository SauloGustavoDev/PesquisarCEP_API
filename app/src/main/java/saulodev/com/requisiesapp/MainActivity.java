package saulodev.com.requisiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import saulodev.com.requisiesapp.api.Api;
import saulodev.com.requisiesapp.model.CEP;

public class MainActivity extends AppCompatActivity {
    TextView textoRecuperado;
    EditText edtDigitado;
    Button btnRecuperar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDigitado = findViewById(R.id.edtCEP);
        btnRecuperar = findViewById(R.id.btnResultado);
        textoRecuperado = findViewById(R.id.resultadoCEP);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Api api = new Api();
                api.recuperarCEPretrofit(textoRecuperado, edtDigitado);
            }
        });
    }
}