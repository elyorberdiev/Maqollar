package com.example.maqollar

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.maqollar.db.AppDatabase
import com.example.maqollar.sharedpreferences.MySharedPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        MySharedPreferences.init(this)
        if (MySharedPreferences.getTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            MySharedPreferences.setTheme(true)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            MySharedPreferences.setTheme(false)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
//                R.id.nav_home,
                R.id.nav_favorite, R.id.nav_settings, R.id.nav_info, R.id.nav_share
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(object :NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {

                    R.id.nav_favorite -> {
                        navController.navigate(R.id.nav_favorite)
                    }
                    R.id.nav_settings -> {
                        navController.navigate(R.id.nav_settings)
                    }
                    R.id.nav_info -> {
                        val alertDialog = AlertDialog.Builder(this@MainActivity).create()
                        val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.my_dialog, null, false)
                        alertDialog.setView(view)
                        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                        alertDialog.window?.setGravity(Gravity.CENTER)
                        alertDialog.show()
                    }
                    R.id.nav_share -> {
                        val sharing = Intent(Intent.ACTION_SEND)
                        sharing.type = "text/plain"
                        val shareBody = "${R.string.app_name}\nhttps://play.google.com/store/apps/details?id=uz.mobiler.maqollar"
                        sharing.putExtra(Intent.EXTRA_TEXT,shareBody)
                        startActivity(sharing)
                    }
                }
                drawerLayout.closeDrawer(GravityCompat.START,true)
                return true
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}