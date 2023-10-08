package com.hfad.stopwatch.chapter_8_CatChat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.hfad.stopwatch.R

class MainActivityCatChat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_chat_activity_main)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)    //добавление свое панели инструментов

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)  //макет с выдвижной панелью
        val builder = AppBarConfiguration.Builder(navController.graph)  //панель инструментов
        builder.setOpenableLayout(drawer)  //кнопка доступа к выдвижной панели
        val appBarConfiguration = builder.build()
        toolbar.setupWithNavController(navController, appBarConfiguration)  //связывание панели инструментов с навигацией

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)  //получение ссылки на нижнюю панель
        bottomNavView.setupWithNavController(navController)  // связь с навигацией

        val navView = findViewById<NavigationView>(R.id.nav_view)  //выдвижная панель
        NavigationUI.setupWithNavController(navView, navController)  //связь с навигацией
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    companion object {
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, MainActivityCatChat::class.java)
        }
    }
}