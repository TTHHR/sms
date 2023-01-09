package com.yaasoosoft.turnsms;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

import java.util.ArrayList;

public class SmsReceiver extends BroadcastReceiver {
    SmsManager smsManager =null;
    public SmsReceiver() {
        super();
        smsManager = SmsManager.getDefault();
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String dString = SmsHelper.getSmsBody(intent);
        String address = SmsHelper.getSmsAddress(intent);
        Log.i("dimos", dString+","+address);
        try {
            ArrayList<String> divideMessage = smsManager.divideMessage(address+"===>"+dString);
            smsManager.sendMultipartTextMessage(SmsHelper.getPhoneNumber(), null,
                    divideMessage, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
