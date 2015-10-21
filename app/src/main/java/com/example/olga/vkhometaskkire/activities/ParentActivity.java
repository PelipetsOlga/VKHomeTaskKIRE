package com.example.olga.vkhometaskkire.activities;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.olga.vkhometaskkire.service.VKService;

/**
 * Created by Olga on 21.10.2015.
 */
public abstract class ParentActivity extends AppCompatActivity {
    private final String LOG_TAG = ParentActivity.class.getSimpleName();
    ServiceConnection sConn;
    Intent intentService;
    boolean bound = false;
    VKService vkService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intentService = new Intent(this, VKService.class);

        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "MainActivity onServiceConnected");
                vkService = ((VKService.VKBinder) binder).getVKService();
                bound = true;
                onServiceReady();
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "MainActivity onServiceDisconnected");
                vkService = null;
                bound = false;
            }
        };

        bindService(intentService, sConn, BIND_AUTO_CREATE);


    }

    abstract void onServiceReady();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!bound) return;
        unbindService(sConn);
        bound = false;
    }
}
