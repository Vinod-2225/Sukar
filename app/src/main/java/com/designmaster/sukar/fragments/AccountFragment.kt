package com.designmaster.sukar.fragments

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.LoginActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.util.AppPrefs
import com.designmaster.sukar.util.BaseFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountFragment : BaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var name:TextView
    lateinit var editprrl:RelativeLayout
    lateinit var logoutrl:RelativeLayout
    lateinit var couponsrl:RelativeLayout
    lateinit var notificationrl:RelativeLayout
    lateinit var orderrl:RelativeLayout
    lateinit var addressrl:RelativeLayout
    lateinit var aboutrl:RelativeLayout
    lateinit var contactrl:RelativeLayout
    lateinit var farl:RelativeLayout
    lateinit var openrl:RelativeLayout
    lateinit var languagerl:RelativeLayout
    lateinit var profilerl:RelativeLayout
    var selectedLanguage = ""
    var btnBack: ImageView? = null
    var logintxt:TextView?=null
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity?)!!.setHeaders(requireActivity().resources.getString(R.string.account),false)

        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_account,
            container, false

        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(object : View.OnKeyListener {
//            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
//                if (event.action == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        val mainIntent = Intent(context, HomeActivity::class.java)
//                        startActivity(mainIntent)
//                        return true
//                    }
//                }
//                return false
//            }
//        })
        name = view.findViewById(R.id.name) as TextView

        name.text = AppPrefs.getUserName(requireActivity()).toString()

        editprrl = view.findViewById(R.id.editbtnrl) as RelativeLayout
        logoutrl = view.findViewById(R.id.logoutrl) as RelativeLayout
        profilerl = view.findViewById(R.id.profilell) as RelativeLayout
        couponsrl = view.findViewById(R.id.coupns) as RelativeLayout
        logintxt = view.findViewById(R.id.logout)
        notificationrl = view.findViewById(R.id.notificationrl) as RelativeLayout
        orderrl = view.findViewById(R.id.orderll) as RelativeLayout
        addressrl = view.findViewById(R.id.address) as RelativeLayout
        aboutrl = view.findViewById(R.id.about) as RelativeLayout
        contactrl = view.findViewById(R.id.contact) as RelativeLayout
        farl = view.findViewById(R.id.fa) as RelativeLayout
        openrl = view.findViewById(R.id.open) as RelativeLayout
        languagerl = view.findViewById(R.id.lang) as RelativeLayout
        btnBack = view.findViewById(R.id.btnback)

//        sharedPreferences = requireContext().getSharedPreferences(
//            sharedPrefFile,
//            Context.MODE_PRIVATE)

//        if (!MyApplication.userid.equals("0")){
//            if (MyApplication.LANGUAGE_SELECTION=="English"){
//                logintxt!!.text = "Logout"
//            }else{
//                logintxt!!.text = "تسجيل خروج"
//            }
//
//
//        }else{
//
//
//            if (MyApplication.LANGUAGE_SELECTION=="English"){
//                logintxt!!.text = "Login"
//
//            }else{
//                logintxt!!.text = "تسجيل دخول"
//
//            }
//
//        }

        if (AppPrefs.isLoggedIn(requireActivity())) {

            if (AppPrefs.isLocaleEnglish(requireActivity())) {
                logintxt!!.text = "Logout"
            }else{
                logintxt!!.text = "تسجيل خروج"
            }

        }else{
            if (AppPrefs.isLocaleEnglish(requireActivity())) {
                logintxt!!.text = "Login"
            }else{
                logintxt!!.text = "تسجيل دخول"
            }
        }

        btnBack!!.setOnClickListener(this)
        logoutrl.setOnClickListener(this)
        couponsrl.setOnClickListener(this)
        notificationrl.setOnClickListener(this)
        orderrl.setOnClickListener(this)
        aboutrl.setOnClickListener(this)
        farl.setOnClickListener(this)
        openrl.setOnClickListener(this)
        languagerl.setOnClickListener(this)
        editprrl.setOnClickListener(this)
        addressrl.setOnClickListener(this)

        aboutrl.setOnClickListener(this)
        contactrl.setOnClickListener(this)
        farl.setOnClickListener(this)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.coupns -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = CouponsFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = CouponsFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.open -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = OpenastroreFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()


                val fragment = OpenAStoreFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.notificationrl -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = NotificationFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = NotificationFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()



            }
            R.id.orderll -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = OrderFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = OrderFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.editbtnrl -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = ProfileFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = ProfileFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.address -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = SavedAddressFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = SavedAddressFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.contact -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = ContactusFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = ContactusFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.about -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = AboutFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = AboutFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.fa -> {
//                botttomappbar.visibility=View.GONE
//                val fragment = FAQsFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = FAQsFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.logoutrl -> {

//                val editor = sharedPreferences.edit()
//                editor.clear()
//                editor.apply()

                val spreferences: SharedPreferences =
                    PreferenceManager.getDefaultSharedPreferences(activity)

                val editor: SharedPreferences.Editor = spreferences.edit()
                editor.remove("userLogin")
                editor.remove("userName")
                editor.remove("userId")

                editor.apply()

                startActivity(
                    Intent(
                        activity,
                        LoginActivity::class.java
                    )
                )
                activity?.finishAffinity()


//                val mainIntent = Intent(context, LoginActivity::class.java)
//                startActivity(mainIntent)
//                Activity().finish()

            }
            R.id.btnback -> {
               // Toast.makeText(context,"back clicked",Toast.LENGTH_LONG).show()
                //(activity as HomeActivity?)!!.getSupportFragmentManager().popBackStackImmediate()
                val mainIntent = Intent(context, HomeActivity::class.java)
                startActivity(mainIntent)
            }
            R.id.lang -> {
                val builder: AlertDialog.Builder =
                    AlertDialog.Builder(this@AccountFragment.activity)
                val inflate: View = this@AccountFragment.layoutInflater.inflate(
                    R.layout.choose_lang_dialog,
                    null as ViewGroup?
                )
                val radioGroup = inflate.findViewById(R.id.radioGroup) as RadioGroup
                radioGroup.clearCheck()
                radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                    val radioButton =
                        radioGroup.findViewById<View>(i) as RadioButton
                    if (radioButton != null && i != -1) {
                        this@AccountFragment.selectedLanguage = radioButton.text.toString()
                    }
                }
                builder.setView(inflate)
                builder.setCancelable(false)
                val create: AlertDialog = builder.create()
                (inflate.findViewById(R.id.dialog_positive_btn) as TextView).setOnClickListener(
                    object : View.OnClickListener {
                        override fun onClick(view: View?) {
                            create.cancel()
                        }
                    })
                (inflate.findViewById(R.id.dialog_negative_btn) as TextView).setOnClickListener(
                    object : View.OnClickListener {
                        override fun onClick(view: View?) {
                            create.cancel()
                            /*if (this@AccountFragment.selectedLanguage.equalsIgnoreCase(
                                    this@AccountFragment.context!!.resources.getString(
                                        R.string.english
                                    )
                                )
                            ) {
                                if (Prefs.getBoolean(APP_C.arabic_lan, false)) {
                                    this@AccountFragment.changeLanguageResource("en")
                                    Prefs.putBoolean(APP_C.arabic_lan, false)
                                    this@AccountFragment.serverCall("English")
                                }
                            } else if (this@AccountFragment.selectedLanguage.equalsIgnoreCase(
                                    this@AccountFragment.context!!.resources.getString(
                                        R.string.arabic
                                    )
                                ) && !Prefs.getBoolean(APP_C.arabic_lan, false)
                            ) {
                                this@AccountFragment.changeLanguageResource("ar")
                                Prefs.putBoolean(APP_C.arabic_lan, true)
                                this@AccountFragment.serverCall("Arabic")
                            }*/
                        }
                    })
                create.show()
            }

        }
    }
}