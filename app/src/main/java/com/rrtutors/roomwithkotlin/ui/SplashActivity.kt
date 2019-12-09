package com.rrtutors.roomwithkotlin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.rrtutors.roomwithkotlin.AppPref
import com.rrtutors.roomwithkotlin.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(Runnable {
            Log.v("user id","user id "+AppPref.getSessionId(applicationContext))
            if(AppPref.getSessionId(applicationContext)>0){
                    startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }else
            {
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
            }
        },2000)

    }
}
