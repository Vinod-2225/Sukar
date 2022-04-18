package com.designmaster.sukar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
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
 * Use the [FilterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FilterFragment : BaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var backicon: ImageView
    lateinit var resetrl: RelativeLayout
    lateinit var applyrl: RelativeLayout
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
            R.layout.filter,
            container, false


        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        backicon=view.findViewById(R.id.backicon) as ImageView
        backicon.setOnClickListener(this)
        resetrl = view.findViewById(R.id.resetrl) as RelativeLayout
        resetrl.setOnClickListener(this)
        applyrl = view.findViewById(R.id.applyrl) as RelativeLayout
        applyrl.setOnClickListener(this)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FilterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
when(v.id){
    R.id.resetrl->{
//        val fragment = HomeFragmentcnt()
//        (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//            R.id.fragment_container,
//            fragment
//        ).addToBackStack(null).commit()

        (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()
    }
    R.id.applyrl->{
//        val fragment = HomeFragmentcnt()
//        (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//            R.id.fragment_container,
//            fragment
//        ).addToBackStack(null).commit()

        (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()
    }
    R.id.backicon->{
//        (activity as HomeActivity?)!!.getSupportFragmentManager().popBackStackImmediate()
       /* val fragment = HomeFragmentcnt()
        (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            fragment
        ).addToBackStack(null).commit()*/

        (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()
    }

}

    }
}