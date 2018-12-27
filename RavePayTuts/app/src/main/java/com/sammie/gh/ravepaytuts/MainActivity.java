package com.sammie.gh.ravepaytuts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.flutterwave.raveandroid.RaveConstants;
import com.flutterwave.raveandroid.RavePayActivity;
import com.flutterwave.raveandroid.RavePayManager;

import static com.sammie.gh.ravepaytuts.Generals.EMAIL_KEY;
import static com.sammie.gh.ravepaytuts.Generals.ENCRY_KEY;
import static com.sammie.gh.ravepaytuts.Generals.PUBLIC_KEY;
import static com.sammie.gh.ravepaytuts.Generals.USERNAME_KEY;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void payNow(View view) {

        new RavePayManager(this)
                .setAmount(1)
                .setCountry("GH")
                .setCurrency("GHS")
                .setEmail(EMAIL_KEY)
                .setfName(USERNAME_KEY)
                .setlName(USERNAME_KEY)
                .setNarration("Testing Payment Tuts")
                .setPublicKey(PUBLIC_KEY)
                .setEncryptionKey(ENCRY_KEY)
                .setTxRef("sexy code")
                .acceptAccountPayments(true)
                .acceptCardPayments(true)
                .acceptGHMobileMoneyPayments(true)
                .onStagingEnv(false)
                .allowSaveCardFeature(true)
//                .setMeta(List < Meta >)
//                .withTheme(R.style.AppTheme)
                .isPreAuth(true)
                .initialize();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            String message = data.getStringExtra("response");
            if (resultCode == RavePayActivity.RESULT_SUCCESS) {
                Toast.makeText(this, "SUCCESS " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_ERROR) {
                Toast.makeText(this, "ERROR " + message, Toast.LENGTH_SHORT).show();
            }
            else if (resultCode == RavePayActivity.RESULT_CANCELLED) {
                Toast.makeText(this, "CANCELLED " + message, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
