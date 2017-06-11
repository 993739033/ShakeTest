package com.scode.shaketest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by 知らないのセカイ on 2017/6/11.
 */

public class SensorManagerHelper implements SensorEventListener {
    private Context context;
    private SensorManager sensorManager;
    private Sensor sensor;
    public OnShakeListener onshakelistener;
    private long lastUpdateTime = 0;//记录最后的更新时间
    private float lastX, lastY, lastZ;//记录上一次比较的坐标

    private static final int UPDATE_INTERVAL_TIME = 50;//毫秒为单位
    private static final int SPEED_SHRESHOLD = 3000;//单位为毫米/毫秒？

    public SensorManagerHelper(Context context) {
        this.context = context;
        start();
    }

    private void start() {
        sensorManager = (SensorManager) this.context.getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//获取重力传感器
        }
        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);//绑定事件
        }
    }

    public void stop() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);//取消绑定事件
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        //当xyz发生变化时

        long currentUpdateTime = System.currentTimeMillis();//回掉方法内不能用private修饰符
        long timeInterval = currentUpdateTime - lastUpdateTime;
        if (timeInterval <UPDATE_INTERVAL_TIME){
            return;
        }
        lastUpdateTime=currentUpdateTime;
        //获取xyz的值
        float x = event.values[0];
        float y= event.values[1];
        float z = event.values[2];

        //计算xyz的变化值
        float deltaX = x - lastX;
        float deltaY = y - lastY;
        float deltaZ = z - lastZ;


        //将当前的值设置为lastx，lasty，lastz
        lastX = x;
        lastY = y;
        lastZ = z;

        double speed=Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ)/timeInterval*10000;//计算速度值
        if (speed > SPEED_SHRESHOLD) {
            if (onshakelistener != null) {
                this.onshakelistener.onShake();//速度大于值时回调接口方法
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //此方法在测量精度发生改变时调用
    }

    public interface OnShakeListener {
        void onShake();
    }

    public void setOnShakeListener(OnShakeListener onShakeListener) {
        onshakelistener = onShakeListener;//接口回调
    }

}
