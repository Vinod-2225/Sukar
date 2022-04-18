package com.designmaster.sukar.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.util.BaseFragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FeedFragment : BaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var backimg: ImageView? = null
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
            R.layout.fragment_feed,
            container, false

        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        backimg = view.findViewById(R.id.backimg)
        backimg!!.setOnClickListener(this)

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FeedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backimg -> {
                OpenastroreFragment.comingfromopenstore = false
                val mainIntent = Intent(context, HomeActivity::class.java)
                startActivity(mainIntent)
            }
        }

    }
}