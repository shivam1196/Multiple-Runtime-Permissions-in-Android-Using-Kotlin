package com.keepimproving.shivam.permissioninfragment

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var permissionFragment = PermissionFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container,permissionFragment).addToBackStack("").commit()
    }


    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount >0){
            finish()
        }
    }
}
