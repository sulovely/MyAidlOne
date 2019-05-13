package com.hd.myaidlone;

import android.os.IBinder;
import android.os.RemoteException;

public  class BinderPoolImpl extends IBinderPool.Stub {
        public static final int BINDER_COMPUTE = 0;
        public static final int BINDER_SECURITY = 1;
        public static final int BINDER_DEMAND = 2;
        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode) {
                case BINDER_COMPUTE:
                    binder = new ComputeImpl();
                    break;
                case BINDER_SECURITY:
                    binder = new SecurityImpl();
                    break;

                case BINDER_DEMAND:
                    binder = new DemandManagerImpl();
                default:
                    break;
            }
            return binder;
        }
    }