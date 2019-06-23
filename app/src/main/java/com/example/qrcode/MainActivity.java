package com.example.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    Button btnScanCode;
    TextView txtCodeLido;
    ImageView imgCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Activity activity = this;

        btnScanCode = (Button) findViewById(R.id.btnScanCode);
        txtCodeLido = (TextView) findViewById(R.id.txtCodeLido);
        imgCode = (ImageView) findViewById(R.id.imgCode);

        btnScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES); // escolhe os tipos de codigos de barras
                intentIntegrator.setPrompt("Camera Scan Code");
                intentIntegrator.setCameraId(0);
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult retorno = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (retorno != null) {
            if (retorno.getContents() != null) {
                txtCodeLido.setText(retorno.getContents());
                try {
                    GeradorQRCode();
                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void GeradorQRCode() throws WriterException {

        String texto = txtCodeLido.getText().toString();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(texto, BarcodeFormat.QR_CODE, 300, 300); // escolho o qrCode
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap imagem = barcodeEncoder.createBitmap(bitMatrix);
            imgCode.setImageBitmap(imagem);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}
