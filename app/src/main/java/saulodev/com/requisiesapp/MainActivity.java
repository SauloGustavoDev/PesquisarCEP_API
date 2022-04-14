package saulodev.com.requisiesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import saulodev.com.requisiesapp.api.Api;


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
                //Metodo DELETE
                //api.removerPostagem(textoRecuperado);
                //Metodo PUT ("Atualizar")
                //api.atualizarPostagem(textoRecuperado);
                //Método Post
                //api.salvarPostagem(textoRecuperado, 123, "Resultado", "resultado");
                //Metodo GET api correio
                api.recuperarCEPretrofit(textoRecuperado, edtDigitado);
                //Para utilizar o método abaixo é necessário alterar o baseUrl
                //api.recuperarListaPhoto();
            }
        });
    }
}