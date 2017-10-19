package com.fukaimei.proximitytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

/**
 * Created by 傅开煤 on 2017/10/5.
 */

public class ProximityAlertReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 获取是否进入指定区域
        boolean isEnter = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
        if (isEnter) {
            // 显示提示信息
            Toast.makeText(context, "您已经进入天津科技大学学生第十四公寓的附近", Toast.LENGTH_LONG).show();
        } else {
            // 显示提示信息
            Toast.makeText(context, "您已经离开天津科技大学学生第十四公寓的附近", Toast.LENGTH_LONG).show();
        }
    }
}













