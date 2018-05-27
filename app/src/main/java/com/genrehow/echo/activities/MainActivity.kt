package com.genrehow.echo.activities

import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import com.genrehow.echo.R
import com.genrehow.echo.fragments.MainScreenFragment

class MainActivity : AppCompatActivity() {

    var navigationDrawerIconsList:ArrayList<String> = arrayListOf()
    var drawerLayout: DrawerLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout =findViewById(R.id.drawer_layout)

        navigationDrawerIconsList.add("All Songs")
        navigationDrawerIconsList.add("Favorites")
        navigationDrawerIconsList.add("settings")
        navigationDrawerIconsList.add("About Us")

        val toggle= ActionBarDrawerToggle(this@MainActivity,drawerLayout,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout?.setDrawerListener(toggle)
            toggle.syncState()

        val mainScreenFragment = MainScreenFragment()
        this.supportFragmentManager
                .beginTransaction()
                .add(R.id.details_fragment,mainScreenFragment,"MainScreenFragment")
                .commit()
        var navigation_recycler_view = findViewById<RecyclerView>(R.id.navigation_recycler_view)
        navigation_recycler_view.layoutManager= LinearLayoutManager(this)
        navigation_recycler_view.itemAnimator= DefaultItemAnimator()

    }

    override fun onStart() {
        super.onStart()
    }
}
