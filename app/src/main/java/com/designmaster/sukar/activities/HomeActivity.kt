package com.designmaster.sukar.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.designmaster.sukar.R
import com.designmaster.sukar.fragments.*
import com.google.android.material.bottomappbar.BottomAppBar

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    var homell: LinearLayout? = null
    var whishll: LinearLayout? = null
    var cartll: LinearLayout? = null
    var accountll: LinearLayout? = null
    var feedll: LinearLayout? = null

    var hometv: TextView? = null
    var whishtv: TextView? = null
    var carttv: TextView? = null
    var accounttv: TextView? = null
    var feedtv: TextView? = null

    var homeimg: ImageView? = null
    var whishimg: ImageView? = null
    var cartimg: ImageView? = null
    var accountimg: ImageView? = null
    var feedimg: ImageView? = null
    var backimg: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        idMappings()
        setOnClickListeners()
        DrawableCompat.setTint(
            DrawableCompat.wrap(homeimg!!.getDrawable()),
            ContextCompat.getColor(applicationContext, R.color.white)
        );
        if (OpenastroreFragment.comingfromopenstore){
            changeFragment(HomeFragmentopenstore())
        }else {
            changeFragment(HomeFragment())
        }
    }

    private fun changeFragment(targetFragment: Fragment) {

        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, targetFragment, "fragment")
            .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            // .setCustomAnimations(R.anim.right_to_left, R.anim.fadeout_2,0, R.anim.left_to_right)
            //.addToBackStack(null)
            .commitAllowingStateLoss()


    }

    private fun setOnClickListeners() {
        homell!!.setOnClickListener(this)
        whishll!!.setOnClickListener(this)
        cartll!!.setOnClickListener(this)
        accountll!!.setOnClickListener(this)
        feedll!!.setOnClickListener(this)

    }

    private fun idMappings() {
        botttomappbar = findViewById<View>(R.id.bottom_app_bar) as BottomAppBar
        homell = findViewById<View>(R.id.homelayout) as LinearLayout
        whishll = findViewById<View>(R.id.wishlistll) as LinearLayout
        cartll = findViewById<View>(R.id.cartll) as LinearLayout
        accountll = findViewById<View>(R.id.accountll) as LinearLayout
        feedll = findViewById<View>(R.id.feedlist) as LinearLayout

        hometv = findViewById<View>(R.id.hometv) as TextView
        whishtv = findViewById<View>(R.id.wishtv) as TextView
        carttv = findViewById<View>(R.id.carttv) as TextView
        accounttv = findViewById<View>(R.id.accounttv) as TextView
        feedtv = findViewById<View>(R.id.feedtv) as TextView

        homeimg = findViewById<View>(R.id.homeimg) as ImageView
        whishimg = findViewById<View>(R.id.wishimg) as ImageView
        cartimg = findViewById<View>(R.id.cartimg) as ImageView
        accountimg = findViewById<View>(R.id.accountimg) as ImageView
        feedimg = findViewById<View>(R.id.feedimg) as ImageView

        if (OpenastroreFragment.comingfromopenstore==true){
            feedll!!.visibility=View.VISIBLE

        }else{
            feedll!!.visibility=View.GONE
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.homelayout -> {
                DrawableCompat.setTint(
                    DrawableCompat.wrap(homeimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.white)
                );
                //homeimg?.setBackgroundResource(R.drawable.homeicon);
                hometv?.setTextColor(Color.parseColor("#ffffff"));
                whishimg?.setBackgroundResource(R.drawable.wishdefault);
                whishtv?.setTextColor(Color.parseColor("#000000"));
                cartimg?.setBackgroundResource(R.drawable.cart);
                carttv?.setTextColor(Color.parseColor("#000000"));
                accountimg?.setBackgroundResource(R.drawable.account);
                accounttv?.setTextColor(Color.parseColor("#000000"));
                if (OpenastroreFragment.comingfromopenstore==true){
                    changeFragment(HomeFragmentopenstore())
                }else {
                    changeFragment(HomeFragment())
                }

            }
            R.id.wishlistll -> {
                DrawableCompat.setTint(
                    DrawableCompat.wrap(homeimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.black)
                );
                whishimg?.setBackgroundResource(R.drawable.wishselect);
                whishtv?.setTextColor(Color.parseColor("#ffffff"));
               // homeimg?.setBackgroundResource(R.drawable.homedefault);
                hometv?.setTextColor(Color.parseColor("#000000"));
                cartimg?.setBackgroundResource(R.drawable.cart);
                carttv?.setTextColor(Color.parseColor("#000000"));
                accountimg?.setBackgroundResource(R.drawable.account);
                accounttv?.setTextColor(Color.parseColor("#000000"));
                changeFragment(WhishlistFragment())
            }
            R.id.cartll -> {
                DrawableCompat.setTint(
                    DrawableCompat.wrap(homeimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.black)
                );
                cartimg?.setBackgroundResource(R.drawable.cartselect);
                carttv?.setTextColor(Color.parseColor("#ffffff"));
                accountimg?.setBackgroundResource(R.drawable.account);
                accounttv?.setTextColor(Color.parseColor("#000000"));
                whishimg?.setBackgroundResource(R.drawable.wishdefault);
                whishtv?.setTextColor(Color.parseColor("#000000"));
              //  homeimg?.setBackgroundResource(R.drawable.homedefault);
                hometv?.setTextColor(Color.parseColor("#000000"));
                changeFragment(CartdetailsFragment())
            }
            R.id.accountll -> {
                accountimg?.setBackgroundResource(R.drawable.profileselect);
                accounttv?.setTextColor(Color.parseColor("#ffffff"));
                whishimg?.setBackgroundResource(R.drawable.wishdefault);
                whishtv?.setTextColor(Color.parseColor("#000000"));
                DrawableCompat.setTint(
                    DrawableCompat.wrap(homeimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.black)
                );
                //homeimg?.setBackgroundResource(R.drawable.homedefault);
                hometv?.setTextColor(Color.parseColor("#000000"));
                cartimg?.setBackgroundResource(R.drawable.cart);
                carttv?.setTextColor(Color.parseColor("#000000"));
                changeFragment(AccountFragment())
            }
            R.id.feedlist -> {
                DrawableCompat.setTint(
                    DrawableCompat.wrap(feedimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.white)
                );
               // homeimg?.setBackgroundResource(R.drawable.homedefault);
                whishimg?.setBackgroundResource(R.drawable.wishdefault);
                accountimg?.setBackgroundResource(R.drawable.account);
                DrawableCompat.setTint(
                    DrawableCompat.wrap(homeimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.black)
                );
               /* DrawableCompat.setTint(
                    DrawableCompat.wrap(accountimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.black)
                );*/
                /*DrawableCompat.setTint(
                    DrawableCompat.wrap(whishimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.black)
                );*/
                /*DrawableCompat.setTint(
                    DrawableCompat.wrap(cartimg!!.getDrawable()),
                    ContextCompat.getColor(applicationContext, R.color.black)
                );*/
                //feedimg?.setBackgroundResource(R.drawable.feedicon);
                feedtv?.setTextColor(Color.parseColor("#ffffff"));
                accounttv?.setTextColor(Color.parseColor("#000000"));
                whishimg?.setBackgroundResource(R.drawable.wishdefault);
                whishtv?.setTextColor(Color.parseColor("#000000"));
              //  homeimg?.setBackgroundResource(R.drawable.homedefault);
                hometv?.setTextColor(Color.parseColor("#000000"));
                cartimg?.setBackgroundResource(R.drawable.cart);
                carttv?.setTextColor(Color.parseColor("#000000"));
                changeFragment(FeedFragment())
            }
        }

    }
    companion object{
        lateinit var botttomappbar:BottomAppBar
    }
}