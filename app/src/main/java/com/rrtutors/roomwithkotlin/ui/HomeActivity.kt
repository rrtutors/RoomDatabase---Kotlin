package com.rrtutors.roomwithkotlin.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.get
import com.rrtutors.roomwithkotlin.AppPref
import com.rrtutors.roomwithkotlin.R
import com.rrtutors.roomwithkotlin.database.MyDatabase
import com.rrtutors.roomwithkotlin.entities.Registration
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var registration: Registration?=null;
    var txt_user_details:TextView?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        registration=MyDatabase.getInstance(applicationContext).registrationDAO().fetchUser(AppPref.getSessionId(applicationContext))
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        txt_user_details=findViewById(R.id.txt_user_details);
        var txt_name=nav_view.getHeaderView(0).findViewById<TextView>(R.id.txt_name)
        var txt_email=nav_view.getHeaderView(0).findViewById<TextView>(R.id.txt_email)
        registration.let {
            txt_name.setText(registration?.name)
            txt_user_details?.setText(registration?.name+"\n"+registration?.email+"\n"+registration?.mobile)
            txt_email.setText(registration?.email)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_logout -> {
                AppPref.clearSeesion(applicationContext)
                var intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_delete -> {

                var delete=registration?.let { MyDatabase.getInstance(applicationContext).registrationDAO().deleteUser(it) }
                Log.v("delete","delete "+delete)
                AppPref.clearSeesion(applicationContext)
                var intent= Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }


        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
