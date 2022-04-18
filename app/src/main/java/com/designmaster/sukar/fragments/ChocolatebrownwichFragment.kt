package com.designmaster.sukar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.ZoomtestActivity
import com.designmaster.sukar.util.BaseFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChocolatebrownwichFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChocolatebrownwichFragment : BaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var backicon: ImageView
    lateinit var addbtnrl: RelativeLayout
    lateinit var enlargerl: RelativeLayout
    lateinit var enlargeiconrl:RelativeLayout
    lateinit var enlargeimg:ImageView
    lateinit var chocoimg:ImageView

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
            R.layout.fragment_chocolatebrownwich,
            container, false


        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        enlargeimg=view.findViewById(R.id.enlarge) as ImageView
        chocoimg=view.findViewById(R.id.chocoimg) as ImageView
        backicon=view.findViewById(R.id.backicon) as ImageView
        backicon.setOnClickListener(this)

        enlargerl=view.findViewById(R.id.enlargeiconrl) as RelativeLayout
        enlargerl.setOnClickListener(this)

        addbtnrl=view.findViewById(R.id.addbtn) as RelativeLayout
        addbtnrl.setOnClickListener(this)

        enlargerl=view.findViewById(R.id.enlargeicon) as RelativeLayout
        enlargerl.setOnClickListener(this)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChocolatebrownwichFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChocolatebrownwichFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.backicon -> {
                val fragment = ProductsFragmentcnt()
                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.addbtn -> {
                val fragment = CartdetailsFragment()
                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }

            R.id.enlargeiconrl -> {
/*
                chocoimg.buildDrawingCache()
                val bitmap: Bitmap = chocoimg.getDrawingCache()
                val intent = Intent(context, ZoomtestActivity::class.java)
                intent.putExtra("BitmapImage", bitmap)
                context?.startActivity(intent)
*/
                val intent = Intent(context, ZoomtestActivity::class.java)
                intent.putExtra("resId", R.drawable.whishlistimg1)
                startActivity(intent)

            }
        }

    }
}