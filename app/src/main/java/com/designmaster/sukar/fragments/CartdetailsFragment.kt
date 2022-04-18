package com.designmaster.sukar.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.util.BaseFragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CartdetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CartdetailsFragment : BaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
var rlwishlist:RelativeLayout?=null
    lateinit var checkoutll:LinearLayout
    lateinit var date:TextView
    lateinit var time: Spinner
    lateinit var datePicker: DatePickerDialog
    var day = 0
    var month: Int = 0
    var year: Int = 0
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

        (activity as MainActivity?)!!.setHeaders(requireActivity().resources.getString(R.string.cart),false)

        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_cartdetails,
            container, false

        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        val mainIntent = Intent(context, HomeActivity::class.java)
                        startActivity(mainIntent)
                        return true
                    }
                }
                return false
            }
        })
        rlwishlist = view.findViewById(R.id.wishlistrl) as RelativeLayout
        rlwishlist!!.setOnClickListener(this)
        checkoutll = view.findViewById(R.id.checkll) as LinearLayout
        checkoutll.setOnClickListener(this)

        date = view.findViewById(R.id.timeo) as TextView
        date!!.setOnClickListener(this)
        time = view.findViewById(R.id.timet) as Spinner
       // time!!.setOnClickListener(this)
        val calendar: Calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
        datePicker = DatePickerDialog(
            requireActivity(),
            R.style.my_dialog_theme,
            object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

                    val inputFormat = SimpleDateFormat("yyyy-M-d", Locale.ENGLISH)
                    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)


                    try {
                        val date1 = inputFormat.parse("" + year + "-" + (month + 1) + "-" + dayOfMonth)


                        date.text = outputFormat.format(date1)


                    } catch (e: ParseException) {
                        // TODO Auto-generated catch block
                        e.printStackTrace()
                    }

                }
            },
            year,
            month,
            day
        );

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CartdetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CartdetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.wishlistrl -> {
                val fragment = WhishlistFragment()
                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.checkll -> {
                val fragment = SavedAddressFragment()
                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.timeo -> {
                datePicker.show()
            }

        }

    }
}