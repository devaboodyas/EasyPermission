# EasyPermission
EasyPermission Library is a library that will make android run-time permissions much easier .

EasyPermission allows you to request a specific permission without adding any validation code .

- Features 



1 - run-time permission dialog will be shown only for Marshmello sdk and above.


2- Request permission result ( grant , denied or Don't Ask again ) will be handled easily and good for dynamic programming .

* Usage:


       PermissionManager permissionManager = PermissionManager.initilize(activity);
       
       String targetPermission=Manifest.permission.CAMERA;
       
       permissionManager.requestPermission(targetPermission, new OnRequestPermissionResultListener() {
          @Override
        public void onGranted() {
        
        // here code  if request got granted ..
        
         Toast.makeText(getApplicationContext(), "REQUEST : GRANTED", Toast.LENGTH_LONG).show();
         
               }

          @Override
          
        public void onDenied() {
        
        // here code  if request got denied ..
        
          Toast.makeText(getApplicationContext(), "REQUEST: DENIED", Toast.LENGTH_LONG).show();
          
               }
               
           @Override
           
          public void onDontAskAgain() {
          
          // here code  if user selected 'Don't ask again' ..
          
           Toast.makeText(getApplicationContext(), "REQUEST: DON NOT ASK AGAIN", Toast.LENGTH_LONG).show();
           
                   }
                });


* Note: You will need to override the onRequestPermissionsResult function 



        @Override
      public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        permissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        
        }

* if there is any bug please email me with it and screenshot .Thanks
 
  My Email : devaboodyas@gmail.com 

