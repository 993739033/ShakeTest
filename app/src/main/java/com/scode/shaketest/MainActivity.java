package com.scode.shaketest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorManagerHelper.OnShakeListener {
    private SensorManagerHelper sensorManagerHelper;
    private long[] times={1000,500,500,1000,200,200,300,400};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManagerHelper = new SensorManagerHelper(this);
        sensorManagerHelper.setOnShakeListener(this);
    }

    @Override
    public void onShake() {
        ToastUtil.getToast(this, "手机被摇晃",Toast.LENGTH_SHORT).show();
        VibrateUtil.Vibrate(this,times,false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManagerHelper.stop();
    }
}
