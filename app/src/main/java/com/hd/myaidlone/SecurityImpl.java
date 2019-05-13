package com.hd.myaidlone;

import android.os.RemoteException;

public class SecurityImpl extends ISecurityCenter.Stub {
    public static final char SECRET_CODE = '^';

    @Override
    public String encypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] ^= SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encypt(password);
    }
}
