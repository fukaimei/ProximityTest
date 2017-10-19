package com.fukaimei.proximitytest;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int WRITE_COARSE_LOCATION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 定位服务的常量
        String locService = Context.LOCATION_SERVICE;
        // 定位服务管理器实例
        LocationManager locationManager;
        // 通过getSystemService方法获得LocationManager实例
        locationManager = (LocationManager) getSystemService(locService);
        // 定义某地址的经纬度
        double longitude = 117.703722;
        double latitude = 39.0901;
        // 定义半径（1公里）
        float radius = 1000;
        // 定义Intent
        Intent intent = new Intent(this, ProximityAlertReciever.class);
        // 将Intent包装成PendingIntent
        PendingIntent pi = PendingIntent.getBroadcast(this, -1, intent, 0);

//        SDK在Android 6.0以上的版本需要进行运行检测的动态权限如下：
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.READ_PHONE_STATE
//
//        这里以ACCESS_FINE_LOCATION与ACCESS_COARSE_LOCATION为例
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
        } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
        }
        // 添加临近警告
        locationManager.addProximityAlert(latitude, longitude, radius, -1, pi);
    }
}
















