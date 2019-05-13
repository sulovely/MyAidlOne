package com.hd.myaidlone;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class DemandManagerImpl extends IDemandManager.Stub {
    private static final String TAG = "dingwanshun";
    public static final int WHAT_MSG = 0x0010;
    private RemoteCallbackList<IDemandListener> demandList = new RemoteCallbackList<>();
    private int count;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (demandList != null) {
                int nums = demandList.beginBroadcast();
                for (int i = 0; i < nums; i++) {
                    MessageBean messageBean = new MessageBean();
                    messageBean.setContent("我丢");
                    messageBean.setLevel(count);
                    count++;
                    try {
                        demandList.getBroadcastItem(i).onDemandReceiver(messageBean);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                demandList.finishBroadcast();
            }

//            mHandler.sendEmptyMessageDelayed(WHAT_MSG, 3000);//每3s推一次消息
        }
    };

    @Override
    public MessageBean getDemand() throws RemoteException {
        MessageBean messageBean = new MessageBean();
        messageBean.setContent("首先，看到我要敬礼");

        return messageBean;
    }

    ////客户端数据流向服务端
    @Override
    public void setDemandIn(MessageBean msg) throws RemoteException {
        Log.i(TAG, "程序员:" + msg.toString());
    }

    //服务端数据流向客户端
    @Override
    public void setDemandOut(MessageBean msg) throws RemoteException {
        Log.i(TAG, "程序员:" + msg.toString());//msg内容一定为空

        msg.setContent("我不想听解释，下班前把所有工作都搞好！");
        msg.setLevel(5);
    }

    @Override
    public void setDemandInOut(MessageBean msg) throws RemoteException {
        Log.i(TAG, "程序员:" + msg.toString());

        msg.setContent("把用户交互颜色都改成粉色");
        msg.setLevel(3);
    }

    @Override
    public void registerListener(IDemandListener listener) throws RemoteException {
        if (demandList.register(listener)) {
            mHandler.sendEmptyMessage(WHAT_MSG);
        }
    }

    @Override
    public void unregisterListener(IDemandListener listener) throws RemoteException {
        if (demandList.unregister(listener)) {
            mHandler.removeMessages(WHAT_MSG);
        }
    }
}
