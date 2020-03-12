package com.example.itlavision;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerQR extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView escanerZXing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        escanerZXing = new ZXingScannerView(this);
        //hacer que el contenido de la actividad sea el escaner
        setContentView(escanerZXing);
    }

    @Override
    public void onResume(){
        super.onResume();
        //El manejador de los resultados es la clase misma
        escanerZXing.setResultHandler(this);
        escanerZXing.startCamera(); //Comenzar la camara al reanudar la app
    }

    @Override
    public void onPause(){
        super.onPause();
        escanerZXing.stopCamera(); //pausar en onPause
    }



    @Override
    public void handleResult(Result result) {
        setContentView(R.layout.activity_main);

        String texto = result.getText();

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);

        alerta.setTitle("Resultado");

        alerta.setMessage("Resultado: " + texto);

        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int id){

                }
        });

        alerta.show();

        escanerZXing.resumeCameraPreview(this);

    }
}
