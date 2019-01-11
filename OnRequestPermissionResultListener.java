package EasyPermission;

public interface OnRequestPermissionResultListener {


     void onGranted();
    void onDenied();

    void onDontAskAgain();
}
