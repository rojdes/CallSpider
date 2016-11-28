package org.d_m_n.callspider.callspider.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import org.d_m_n.callspider.callspider.R;
import org.d_m_n.callspider.callspider.managers.PermissionManager;

/**
 * Created by d1m11n on 11/27/16.
 */

public class SplashScreenActivity  extends AppCompatActivity{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
