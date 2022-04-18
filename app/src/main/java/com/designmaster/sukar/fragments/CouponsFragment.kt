package com.designmaster.sukar.fragments

import CouponsData
import CouponsResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.CouponslistAdapter
import com.designmaster.sukar.util.*
import com.designmaster.sukar.util.BaseFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CouponsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CouponsFragment : BaseFragment(), View.OnClickListener, ApiCallListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var couponsrlv: RecyclerView? = null
    private var couponslistarray: java.util.ArrayList<CouponsResponse>? = null
    var couponslistAdapter: CouponslistAdapter? = null

    private var couponsData = ArrayList<CouponsData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders("Coupons",true)
        } else {
            (activity as MainActivity?)!!.setHeaders("Coupons",true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_coupons,
            container, false

        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        couponsrlv = view.findViewById(R.id.rlvcouponslist)
        couponsrlv!!.isNestedScrollingEnabled = false;
        couponsrlv!!.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        getcouponsapi()


        couponslistAdapter = CouponslistAdapter(
            requireActivity(),
            couponsData
        )
        couponsrlv!!.adapter = couponslistAdapter


        couponslistAdapter!!.setOnClickListener(object : CouponslistAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {


            }

        })


    }

    private fun getcouponsapi() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getcoupons(

            ),
            this,
            ApiConstants.REQUEST_CODE.COUPONS
        )

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CouponsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CouponsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnback -> {
//                (activity as HomeActivity?)!!.supportFragmentManager.popBackStackImmediate()
                (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()
            }
        }

    }

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.COUPONS ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    CouponsResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {
                    couponslistarray = ArrayList();
//                    if (MyApplication.LANGUAGE_SELECTION == "English") {
                        couponsData.clear()
//                        for (i in 0 until apiResponse.output.info.size) {
//                            couponslistAdapter = CouponslistAdapter(
//                                requireActivity(),
//                                apiResponse.output.data
//                            )
//                            couponsrlv!!.adapter = couponslistAdapter


                            couponsData.addAll(apiResponse.output.info)
                            couponslistAdapter!!.notifyDataSetChanged()


//                        }
//                    }

                }
            }
        }
        hideProgress()
    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()

    }
}