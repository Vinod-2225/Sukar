package com.designmaster.sukar.fragments


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.util.BaseFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
            R.layout.notification,
            container, false


        )
//        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        HomeActivity.botttomappbar.visibility=View.VISIBLE
                        val fragment = AccountFragment()
                        (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                            R.id.fragment_container,
                            fragment
                        ).addToBackStack(null).commit()
                        return true
                    }
                }
                return false
            }
        })


    }



    companion object {
        @kotlin.jvm.JvmField
        var comingfromhome: Boolean = false



        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}