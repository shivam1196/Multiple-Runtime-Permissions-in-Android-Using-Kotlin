package com.keepimproving.shivam.permissioninfragment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class PermissionFragment:Fragment(){




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_permission_layout,container,false)
    }







    fun checkPermissionForWifi(){
        if(ActivityCompat.checkSelfPermission(context!!,Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED||ActivityCompat.checkSelfPermission(context!!,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            requestWifiPermission()
        }
        else{
            Toast.makeText(context,"Wifi Permission",Toast.LENGTH_SHORT).show()
            Log.v("PERMISSION","Wifi Permission Check")
        }
    }


    fun requestWifiPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(activity as MainActivity,Manifest.permission.CAMERA)||ActivityCompat.shouldShowRequestPermissionRationale(activity as MainActivity,Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(context,"Permission is required",Toast.LENGTH_SHORT).show()
        }
        else{

            Log.v("Permissions","Request")
            requestPermissions(arrayOf(Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION),101)
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            101 ->{
                if (verifyPermission(grantResults)) {
                    // Camera permission has been granted, preview can be displayed
                    Toast.makeText(context,"Camera Permission Granted",Toast.LENGTH_SHORT).show()
                  //  checkForLocationPermission()
                    Log.v("FRAGMENTPERMISSION","All Done")


                } else {
                   fragmentManager!!.popBackStack()

                }
            }

            else ->{
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            checkPermissionForWifi()
         //   checkForLocationPermission()
    }


  fun verifyPermission(grantResults: IntArray):Boolean{
      if (grantResults.size < 1) {
          return false
      }

      // Verify that each required permission has been granted, otherwise return false.
      for (result in grantResults) {
          if (result != PackageManager.PERMISSION_GRANTED) {
              return false
          }
      }
      return true
  }
}