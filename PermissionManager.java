package EasyPermission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;


public class PermissionManager extends Activity{


    private Activity context;


    private OnRequestPermissionResultListener onRequestPermissionResultListener;
    private  OnCheckPermissionListenerListener onCheckPermissionListenerListener;

    private String permission;
    private static final int REQUEST_CODE=54626;//random number;
    private static PermissionManager permissionManager=new PermissionManager();
    private PermissionManager(){

    }


    public Activity getContext() {
        return context;
    }

    private void setContext(Activity context) {
        this.context = context;
    }



    public static PermissionManager initilize(Activity context){

        permissionManager.setContext(context);



        return permissionManager;
    }


    public String checkPermissionStatus(String permission, OnCheckPermissionListenerListener onCheckPermissionListenerListener) {

        this.permission=permission;
        this.onCheckPermissionListenerListener=onCheckPermissionListenerListener;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int permissionResult = this.context.checkSelfPermission(this.permission);


            if (permissionResult == PackageManager.PERMISSION_GRANTED) {



                this.onCheckPermissionListenerListener.onGranted();


                return PermissionMessages.GRANTED;

            } else if (permissionResult == PackageManager.PERMISSION_DENIED) {


                if (context.shouldShowRequestPermissionRationale(permission)) {
                    //Show permission explanation dialog...
                    //checkPermissionResult.onDenied();

                    this.onCheckPermissionListenerListener.onDenied();

                    return PermissionMessages.DENIED;
                } else {
                    //Never ask again selected, or device policy prohibits the app from having that permission.
                    // So, disable that feature, or fall back to another situation...

                    this.onCheckPermissionListenerListener.onDontAskAgain();

                    return PermissionMessages.DO_NOT_ASK_AGAIN;
                }


            } else {


                return null;
            }


        }





        return null;
    }


    public String checkPermissionStatus(String permission) {

        this.permission=permission;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int permissionResult = this.context.checkSelfPermission(this.permission);


            if (permissionResult == PackageManager.PERMISSION_GRANTED) {

                //checkPermissionResult.onGranted();



                return PermissionMessages.GRANTED;

            } else if (permissionResult == PackageManager.PERMISSION_DENIED) {


                if (context.shouldShowRequestPermissionRationale(permission)) {
                    //Show permission explanation dialog...
                    //checkPermissionResult.onDenied();



                    return PermissionMessages.DENIED;
                } else {
                    //Never ask again selected, or device policy prohibits the app from having that permission.
                    // So, disable that feature, or fall back to another situation...


                    return PermissionMessages.DO_NOT_ASK_AGAIN;
                }


            } else {


                return null;
            }


        }





        return null;
    }




    public void requestPermission(String permissionName, OnRequestPermissionResultListener onRequestPermissionResultListener){

        this.permission=permissionName;
this.onRequestPermissionResultListener = onRequestPermissionResultListener;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            //context.requestPermissions(new String[]{permissionName},REQUEST_CODE);

            // check if permission is granted or not


            this.context.requestPermissions(new String[]{permission}, REQUEST_CODE);




        }else{

            /**  Run-time permissions request is not available for versions that are below Marshmello*/
        }





    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_CODE){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.

                //this.OnCheckPermissionListenerListener.onGranted();
                this.onRequestPermissionResultListener.onGranted();



            } else if(grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_DENIED){
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                //this.OnCheckPermissionListenerListener.onDenied();


                if (context.shouldShowRequestPermissionRationale(permission)) {
                    //Show permission explanation dialog...
                    //checkPermissionResult.onDenied();




                    this.onRequestPermissionResultListener.onDenied();
                } else {
                    //Never ask again selected, or device policy prohibits the app from having that permission.
                    // So, disable that feature, or fall back to another situation...

                    this.onRequestPermissionResultListener.onDontAskAgain();
                }

            }



/**
            String permissionMessage=checkPermissionGrant(this.permission);
            if(permissionMessage.equals(PermissionMessages.GRANTED)){


                this.onRequestPermissionResultListener.onGranted();

            }else if(permissionMessage.equals(PermissionMessages.DENIED)){

                this.onRequestPermissionResultListener.onDenied();
            }else{

                this.onRequestPermissionResultListener.onDontAskAgain();
            }

***/


        }else{

            /** was not this application ***/
        }
    }
}

