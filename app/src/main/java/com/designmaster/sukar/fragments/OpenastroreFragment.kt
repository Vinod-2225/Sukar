package com.designmaster.sukar.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.util.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OpenastroreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpenastroreFragment : BaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var btnBack: ImageView? = null
    lateinit var nextbtn:TextView
    lateinit var cookies:TextView
    lateinit var chocolate:TextView
    lateinit var scooped:TextView
    lateinit var sweet:TextView
    lateinit var savories:TextView
    lateinit var pies:TextView
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
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_openastrore,
            container, false

        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        btnBack = view.findViewById(R.id.btnback)
        btnBack!!.setOnClickListener(this)
        nextbtn = view.findViewById(R.id.nextbtn)
        nextbtn!!.setOnClickListener(this)
        cookies = view.findViewById(R.id.cook)
        cookies!!.setOnClickListener(this)
        chocolate = view.findViewById(R.id.choco)
        chocolate!!.setOnClickListener(this)
        scooped = view.findViewById(R.id.scco)
        scooped!!.setOnClickListener(this)

        sweet = view.findViewById(R.id.sweet)
        sweet!!.setOnClickListener(this)
        savories = view.findViewById(R.id.sav)
        savories!!.setOnClickListener(this)
        pies = view.findViewById(R.id.pies)
        pies!!.setOnClickListener(this)
//        view.setFocusableInTouchMode(true);
//        view.requestFocus();
//        view.setOnKeyListener(object : View.OnKeyListener {
//            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
//                if (event.action == KeyEvent.ACTION_DOWN) {
//                    if (keyCode == KeyEvent.KEYCODE_BACK) {
//                        HomeActivity.botttomappbar.visibility=View.VISIBLE
//                        val fragment = AccountFragment()
//                        (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                            R.id.fragment_container,
//                            fragment
//                        ).addToBackStack(null).commit()
//                        return true
//                    }
//                }
//                return false
//            }
//        })

    }

    companion object {
        @kotlin.jvm.JvmField
        var comingfromopenstore: Boolean = false
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OpenastroreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OpenastroreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnback -> {
//                (activity as HomeActivity?)!!.getSupportFragmentManager().popBackStackImmediate()
                (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()
            }
            R.id.nextbtn -> {
                comingfromopenstore=true
                val mainIntent = Intent(context, HomeActivity::class.java)
                startActivity(mainIntent)
            }
            R.id.cook -> {
              cookies.setBackgroundColor(resources.getColor(R.color.text_color))
            }
            R.id.choco -> {
                chocolate.setBackgroundColor(resources.getColor(R.color.text_color))
            }
            R.id.scco -> {
                scooped.setBackgroundColor(resources.getColor(R.color.text_color))
            }
            R.id.sweet -> {
                sweet.setBackgroundColor(resources.getColor(R.color.text_color))
            }
            R.id.sav -> {
                savories.setBackgroundColor(resources.getColor(R.color.text_color))
            }
            R.id.pies -> {
                pies.setBackgroundColor(resources.getColor(R.color.text_color))
            }
        }
    }
}