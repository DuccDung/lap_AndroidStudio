package com.example.app_call_api_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_call_api_1.api.ApiService;
import com.example.app_call_api_1.model.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView txtSuccess;
    private TextView txtTimestamp;
    private TextView txtUSD;

    private Button btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSuccess = findViewById(R.id.txtSuccess);
        txtTimestamp = findViewById(R.id.txtTimestamp);
        txtUSD = findViewById(R.id.txtUsd);
        btnCall = findViewById(R.id.btnCallApi);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallApi();
            }
//https://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
            private void clickCallApi() {
                ApiService.apiService.convertUsdToVnd("843d4d34ae72b3882e3db642c51e28e6" ,
                        "VND" ,
                        "USD" ,
                        1).enqueue(new Callback<Currency>() {
                    @Override
                    public void onResponse(Call<Currency> call, Response<Currency> response) {
                        Toast.makeText(MainActivity.this, "Call Api Success", Toast.LENGTH_SHORT).show();
                        Currency currency = response.body();
                        if(currency != null && currency.isSuccess()){
                           txtSuccess.setText(currency.getTerms());
                           txtTimestamp.setText(currency.getTimestamp() + "");
                           txtUSD.setText(currency.getQuotes().getUsdVnd() + "");
                        }
                    }

                    @Override
                    public void onFailure(Call<Currency> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Call Api Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}