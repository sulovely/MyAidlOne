package com.hd.myaidlone;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author: dingwanshun
 * Time: 2019/1/22
 * Description:
 */
public class AIDLService extends Service {
    private static final String TAG = "AIDLService";
    private IBinderPool.Stub mBinderPool = new BinderPoolImpl();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
