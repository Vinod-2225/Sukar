package com.designmaster.sukar.util

import android.app.Activity

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rawcode.joesfam.util.ProgressDialog
import com.designmaster.sukar.R
import com.designmaster.sukar.util.MyApplication.getInstance

import java.util.regex.Pattern

open class BaseActivity : AppCompatActivity() {

    private var mShouldExitApp: Boolean = false
    protected var pd: ProgressDialog? = null
    protected var mToolbar: Toolbar? = null

//    init {
//        LocaleUtils.updateConfig(this)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

/*    override fun onBackPressed() {
        if (isTaskRoot) {
            if (!mShouldExitApp) {
                showToast(getString(R.string.press_back_again_to_exit))
                mShouldExitApp = true
                Handler().postDelayed({ mShouldExitApp = false }, 2000)
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }*/

    protected fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    private fun hideKeyBoard() {
        try {
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        } catch (e: Exception) {
        }
    }

    protected fun capitalizeItem(capString: String): String {
        try {
            val capBuffer = StringBuffer()
            val capMatcher =
                Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString)
            while (capMatcher.find()) {
                capMatcher.appendReplacement(
                    capBuffer,
                    capMatcher.group(1)!!.toUpperCase() + capMatcher.group(2)!!.toLowerCase()
                )
            }
            return capMatcher.appendTail(capBuffer).toString()
        } catch (e: Exception) {
            return ""
        }
    }
    protected fun isValidPassword(pass: String?): Boolean {
        return if (pass != null && pass.length > 0) {
            true
        } else false
    }
    protected fun isValidPasswordlegnthmin(pass: String?): Boolean {
        return if (pass!!.isNotEmpty() && pass.length > 5) {
            true
        } else false
    }
   /* protected fun isValidPasswordlegnthmax(pass: String?): Boolean {
        return if (pass!!.isNotEmpty() && pass.length < 14) {
            true
        } else false
    }*/
    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidMobile1(phone: String): Boolean {
        var check = false
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length < 8 || phone.length > 8) {
                if(phone.length!= 8) {

                    check = false
                }
                //txtPhone.setError("Not Valid Number")
            } else {
                check = true
            }
        } else {
            check = false
        }
        return check
    }

    protected fun showProgress() {
        hideKeyBoard()
        hideProgress()
        pd = ProgressDialog.getInstance()
        pd?.show(supportFragmentManager, "showProgressDialog")
    }

    protected fun hideProgress() {
        if (pd != null) {
            pd!!.dismiss()
        }
    }


    fun addFragment(fragment: Fragment, container: Int) {
        val mFragmentTransaction: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        mFragmentTransaction.add(container, fragment, "tag")
        mFragmentTransaction.commitAllowingStateLoss()
    }

    fun replaceFragment(fragment: Fragment, container: Int) {
        val mFragmentTransaction: FragmentTransaction =
            supportFragmentManager.beginTransaction()
        mFragmentTransaction.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        mFragmentTransaction.replace(container, fragment, "tag")
        mFragmentTransaction.commitAllowingStateLoss()
    }

    companion object {

        private var solidColors = arrayOf("#D4D3CE", "#7C8B8B", "#AEA67C")
        private var transColors = arrayOf("#80D47FA6", "#808A56AC", "#80241332", "#809599B3")

        fun getHalfScreenWidth(activity: Activity): Int {
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.x / 2
        }

        fun getScreenWidth(activity: Activity): Int {
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.x
        }

//        fun getHomeImageHeight(activity: Activity): Int {
//            val display = activity.windowManager.defaultDisplay
//            val size = Point()
//            display.getSize(size)
//            val margin = activity.resources.getDimensionPixelSize(R.dimen._15sdp)
//            return size.x - margin
//        }

        fun getOneThirdScreenWidth(activity: Activity): Int {
            val display = activity.windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.x / 3
        }

        fun getSolidColors(): Array<String> {
            return solidColors
        }

        fun getTransColors(): Array<String> {
            return transColors
        }

        fun capitalizeItem(capString: String): String {
            try {
                val capBuffer = StringBuffer()
                val capMatcher =
                    Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString)
                while (capMatcher.find()) {
                    capMatcher.appendReplacement(
                        capBuffer,
                        capMatcher.group(1)!!.toUpperCase() + capMatcher.group(2)!!.toLowerCase()
                    )
                }
                return capMatcher.appendTail(capBuffer).toString()
            } catch (e: Exception) {
                return capString
            }
        }
    }

//    fun setUpToolBarListeners(search: View, notification: View, cart: View) {
//        search.setOnClickListener {
//            startActivity(Intent(this, SearchActivity::class.java))
//        }
//        cart.setOnClickListener {
//            startActivity(ShoppingBagActivity.getIntent(this))
//        }
//        notification.setOnClickListener {
//            startActivity(NotificationActivity.getIntent(this))
//        }
//    }
//
//    fun setCartCount(cartCount: AdvanceTextView) {
//        if (AppPrefs.getCartCount(this) > 0) {
//            cartCount.text = AppPrefs.getCartCount(this).toString()
//            cartCount.visibility = View.VISIBLE
//        } else {
//            cartCount.visibility = View.INVISIBLE
//        }
//    }

/*    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }*/


   /* fun getCurrencyCode(): String {
        return AppPrefs.getCurrency(this)
    }*/

//    fun showMessageDialog(msg: String) {
//        var dialog = OneButtonDialog(this, msg, getString(R.string.ok))
//        dialog.setDialogClickListener(object : OneButtonDialog.DialogClickListener {
//            override fun onPositiveClick() {
//            }
//        })
//        dialog.showDialog()
//    }

}
