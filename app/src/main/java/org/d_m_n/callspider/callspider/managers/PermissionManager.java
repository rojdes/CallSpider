package org.d_m_n.callspider.callspider.managers;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import org.d_m_n.callspider.callspider.ui.MainActivity;


/**
 * Created by d1m11n on 11/27/16.
 */

public class PermissionManager {

    private static final String [] phonePermissions = new String[]{
        Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.PROCESS_OUTGOING_CALLS
    };

    private static final String [] contactsPermissions = new String[]{Manifest.permission.READ_CONTACTS};


    public static boolean isPhonePermissionGranted(@NonNull Context context){
        for (int i= 0; i< phonePermissions.length; i++ ){
           if(ContextCompat.checkSelfPermission(context, phonePermissions[i]) != PackageManager.PERMISSION_GRANTED){
               return false;
           }
        }
        return true;
    }

    public static void askPhoneStatePermission(@NonNull Activity activity, int requestCode){
        ActivityCompat.requestPermissions(activity,phonePermissions, requestCode);
    }

    public static void askContactsPermissionGranted(@NonNull Activity activity, int requestCode) {
        ActivityCompat.requestPermissions(activity,contactsPermissions, requestCode);
    }

    public static boolean isPhonePermissionsRequestGranted(int[] grantResults) {
        for (int i =0; i < phonePermissions.length; i++){
            if ( grantResults [i] != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    public static boolean handlePermissionRequest(Activity rootActivity, String[] permissions, int[] grantResults) {
//        PermissionManager.isPhonePermissionsRequestGranted(grantResults)){
//            UserNotifyManager.showToast(this, R.string.verify_permissions_error,true);
//        }
//            return;
        return true;
    }

    public static boolean isContactsPermissionsGranted(@NonNull Context context) {
        for (int i= 0; i< contactsPermissions.length; i++ ){
            if(ContextCompat.checkSelfPermission(context, contactsPermissions[i]) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

}
