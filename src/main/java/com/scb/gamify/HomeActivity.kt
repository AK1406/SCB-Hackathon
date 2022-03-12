package com.scb.gamify


import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : AppCompatActivity() {


    private var navigationPosition: Int = 0
    private lateinit var navView: NavigationView
    private lateinit var drawer: DrawerLayout
    private lateinit var tool: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        drawer = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        tool = findViewById(R.id.toolbar)

        initView()
    }

    private fun initView() {
        setSupportActionBar(tool)   //set toolbar
        setUpDrawerLayout()  //calling function to set drawer layout
        //Load dashboard fragment first
        navigationPosition = R.id.course
        navigateToFragment(CategoryFragment.newInstance())
        navView.setCheckedItem(navigationPosition)
        tool.title = "All Courses"
        /**Listener for Select different options from the drawer**/
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.course -> {
                    tool.title = "All Courses"
                    navigationPosition = R.id.course
                    navigateToFragment(CategoryFragment.newInstance()) //navigate to fragment
                }

                R.id.about -> {
                    tool.title = "App Info"
                    navigationPosition = R.id.about
                    navigateToFragment(AboutFragment.newInstance())
                }

                R.id.logout -> {
                    finish()
                }

            }
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            drawer.closeDrawers()
            true
        }


        drawer.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(p0: Int) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerSlide(p0: View, p1: Float) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerClosed(p0: View) {
                //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDrawerOpened(p0: View) {
                //To change body of created functions use File | Settings | File Templates.
            }
        })

    }


    //define function to set drawer layout
    private fun setUpDrawerLayout() {
        val toggle = ActionBarDrawerToggle(
            this, drawer, tool, R.string.open_drawer, R.string.close_drawer
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    //function used when there is a need to navigate to a fragment
    private fun navigateToFragment(fragmentToNavigate: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragmentToNavigate)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.LEFT)
        }
        if (count == 0) {
            super.onBackPressed()
            if (navigationPosition == R.id.course) {
                finish()
            }
        } else {
            supportFragmentManager.popBackStack()

            //Navigate to Home Fragment
            navigationPosition = R.id.course
            navigateToFragment(CategoryFragment.newInstance())
            navView.setCheckedItem(navigationPosition)
            tool.title = "All Courses"

        }
    }

}