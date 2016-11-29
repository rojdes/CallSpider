package org.d_m_n.callspider.callspider.controllers;

import android.content.Context;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.telephony.TelephonyManager;

import com.android.internal.telephony.ITelephony;

import org.d_m_n.callspider.callspider.app.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by d1m11n on 11/12/16.
 */

public final class CallController {

    private static final String TAG =  CallController.class.getSimpleName();
    private static final long MIN_SEC_DELAY = 4000L ;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static interface CallEndCallback{

        public void  onEndCall(boolean isSuccessfull);
    }

    private static final void endCall(Context context) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        Class clazz = Class.forName(telephonyManager.getClass().getName());
        Method method = clazz.getDeclaredMethod("getITelephony");
        method.setAccessible(true);
        ITelephony telephonyService = (ITelephony) method.invoke(telephonyManager);
        telephonyService.endCall();
    }


    public static final void endCall(long delay, final CallEndCallback callback){
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    CallController.disconnectCall("");
                    if (callback != null){
                        callback.onEndCall(true);
                    }
                } catch (Exception e) {
                    Logger.e(TAG, "endCall error = " + e.toString());
                    if (callback != null){
                        callback.onEndCall(false);
                    }
                }
            }
        }, delay < MIN_SEC_DELAY ? MIN_SEC_DELAY : delay);
    }

    public static boolean endCallWrapException(Context context){
        try {
            endCall(context);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Logger.e(TAG, "endCall ClassNotFound",e);
        } catch (NoSuchMethodException e) {
            Logger.e(TAG, "endCall NoSuchMethod",e);
        } catch (InvocationTargetException e) {
            Logger.e(TAG, "endCall InvocationTarget",e);
        } catch (IllegalAccessException e) {
            Logger.e(TAG, "endCall IllegalAccess",e);
        }
        return false;
    }

    public static void declinePhone(Context context) throws Exception {

        try {

            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";
            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;
            Method telephonyEndCall;
            Object telephonyObject;
            Object serviceManagerObject;
            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
            telephonyObject = serviceMethod.invoke(null, retbinder);
            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);

        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("unable", "msg cant dissconect call...." + e.toString());

        }
    }

    public static boolean killCall(Context context) {
        try {
            // Get the boring old TelephonyManager
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            // Get the getITelephony() method
            Class classTelephony = Class.forName(telephonyManager.getClass().getName());
            Method methodGetITelephony = classTelephony.getDeclaredMethod("getITelephony");

            // Ignore that the method is supposed to be private
            methodGetITelephony.setAccessible(true);

            // Invoke getITelephony() to get the ITelephony interface
            Object telephonyInterface = methodGetITelephony.invoke(telephonyManager);

            // Get the endCall method from ITelephony
            Class telephonyInterfaceClass = Class.forName(telephonyInterface.getClass().getName());
            Method methodEndCall = telephonyInterfaceClass.getDeclaredMethod("endCall");

            // Invoke endCall()
            methodEndCall.invoke(telephonyInterface);

        } catch (Exception ex) { // Many things can go wrong with reflection calls
            Logger.e(TAG, "kill call e= " + ex.toString());
            return false;
        }
        return true;
    }

    /**
     * work with end call
     * @param type
     */
    public static void disconnectCall(String type){
        try {
            String serviceManagerName = "android.os.ServiceManager";
            String serviceManagerNativeName = "android.os.ServiceManagerNative";
            String telephonyName = "com.android.internal.telephony.ITelephony";
            Class<?> telephonyClass;
            Class<?> telephonyStubClass;
            Class<?> serviceManagerClass;
            Class<?> serviceManagerNativeClass;
            Method telephonyEndCall;
            Object telephonyObject;
            Object serviceManagerObject;
            telephonyClass = Class.forName(telephonyName);
            telephonyStubClass = telephonyClass.getClasses()[0];
            serviceManagerClass = Class.forName(serviceManagerName);
            serviceManagerNativeClass = Class.forName(serviceManagerNativeName);
            Method getService = // getDefaults[29];
                    serviceManagerClass.getMethod("getService", String.class);
            Method tempInterfaceMethod = serviceManagerNativeClass.getMethod("asInterface", IBinder.class);
            Binder tmpBinder = new Binder();
            tmpBinder.attachInterface(null, "fake");
            serviceManagerObject = tempInterfaceMethod.invoke(null, tmpBinder);
            IBinder retbinder = (IBinder) getService.invoke(serviceManagerObject, "phone");
            Method serviceMethod = telephonyStubClass.getMethod("asInterface", IBinder.class);
            telephonyObject = serviceMethod.invoke(null, retbinder);
            telephonyEndCall = telephonyClass.getMethod("endCall");
            telephonyEndCall.invoke(telephonyObject);

//            // Reject call and send SMS
//            if (type.equalsIgnoreCase("reject")) {
//                SmsManager smsManager = SmsManager.getDefault();
//                smsManager.sendTextMessage(_incomingNumber, null, "Hey! I am driving right now. Please call me back after some time", null, null);
//                Toast.makeText(context, "CALL REJECTED", Toast.LENGTH_SHORT).show();
//            }

        } catch (Exception e) {
            Logger.e(TAG, "disconnect call e= " + e.toString());
            e.printStackTrace();
        }
    }
}
