package com.designmaster.sukar.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.designmaster.sukar.R
import com.designmaster.sukar.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var bottomNavigation: BottomNavigationView? = null
    private var btnBack: Button? = null
    var lblTitle: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        idMappings()
        setOnClickListeners()
    }

    private fun idMappings() {

        btnBack = findViewById<View>(R.id.btnBack) as Button
        lblTitle = findViewById<View>(R.id.lblTitle) as TextView

        bottomNavigation = findViewById(R.id.bottom_navigation)


        bottomNavigation!!.setOnNavigationItemSelectedListener(navigationItemSelectedListener)
        bottomNavigation!!.setOnNavigationItemReselectedListener(navigationItemReselectedListener)

        bottomNavigation!!.menu.getItem(0).isChecked = true;
        changeFragment(HomeFragment())

    }

    private fun setOnClickListeners() {

        btnBack!!.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnBack -> {

                super.onBackPressed()

            }


        }
    }

    private var navigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
//                    bottomNavigation!!.itemIconTintList = ContextCompat.getColorStateList(this,R.color.tab_1_color)
                        changeFragment(HomeFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_wishlist -> {
//                    bottomNavigation!!.itemIconTintList = ContextCompat.getColorStateList(this, R.color.tab_1_color)
                        changeFragment(WishListFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_feed -> {
//                    bottomNavigation!!.itemIconTintList = ContextCompat.getColorStateList(this, R.color.tab_1_color)
                        changeFragment(FeedFragment())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_cart -> {
//                    bottomNavigation!!.itemIconTintList = ContextCompat.getColorStateList(this, R.color.tab_1_color)
//                        changeFragment(CartdetailsFragment())
                        changeFragment(CartFragmentNew())
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_my_account -> {
//                    bottomNavigation!!.itemIconTintList = ContextCompat.getColorStateList(this, R.color.tab_1_color)
                        changeFragment(AccountFragment())


                        return@OnNavigationItemSelectedListener true
                    }

                }
                false
            }


    private var navigationItemReselectedListener =
            BottomNavigationView.OnNavigationItemReselectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
//                        changeFragment(HomeFragment())
                    }
                    R.id.navigation_wishlist -> {
//                        changeFragment(WhishlistFragment())
                    }
                    R.id.navigation_feed -> {
//                        changeFragment(WhishlistFragment())
                    }
                    R.id.navigation_cart -> {
//                        changeFragment(CartdetailsFragment())
                    }
                    R.id.navigation_my_account -> {
//                        changeFragment(AccountFragment())
                    }

                }
            }


    private fun changeFragment(targetFragment: Fragment) {

        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, targetFragment, "fragment")
            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN) // .setCustomAnimations(R.anim.right_to_left, R.anim.fadeout_2,0, R.anim.left_to_right)
//                .addToBackStack(null)
            .commitAllowingStateLoss()

    }


    fun setHeaders(
        title: String,
        backBtn: Boolean

    ) {

        lblTitle!!.text = title

        if (backBtn) {
            btnBack!!.visibility = View.VISIBLE
        } else {
            btnBack!!.visibility = View.GONE
        }


    }

//    override fun onBackPressed() {
//        // Simply Do noting!
//    }


//    fun toNavigateToMyAccountFragment() {
//
//        profileScreen = true
//        bottomNavigation!!.selectedItemId = R.id.navigation_my_account
//
//    }
//
//
//    private fun getCurrentFragment(): String? {
//        return supportFragmentManager.findFragmentById(R.id.fragment_container)!!.javaClass.simpleName
//    }

}