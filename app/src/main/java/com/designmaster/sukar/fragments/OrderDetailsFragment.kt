package com.designmaster.sukar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.MyOrdersAdapter
import com.designmaster.sukar.models.MyOrderDetailsResponse
import com.designmaster.sukar.models.MyOrdersInfo
import com.designmaster.sukar.models.MyOrdersResponse
import com.designmaster.sukar.util.*
import java.util.ArrayList

class OrderDetailsFragment : BaseFragment(), ApiCallListener, View.OnClickListener {

    var rootView: View? = null

    var lblTotal: TextView? = null
    var lblShop: TextView? = null
    var lblItem: TextView? = null
    var lblQuantity: TextView? = null
    var lblPrice: TextView? = null
    var lblTotalPrice: TextView? = null
    var lblAddress: TextView? = null


    private var myOrdersInfo = ArrayList<MyOrdersInfo>()

    var orderId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.orders),true)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.orders),true)
        }

        if (arguments != null) {
            orderId = requireArguments().getString("OrderId").toString()
        }

        // Inflate the layout for this fragment
        if (rootView == null) {

            rootView = inflater.inflate(R.layout.my_orders_details_fragment, container, false)


            idMappings()
            setOnClickListeners()
            myOrders()

        }
        return rootView
    }

    private fun idMappings() {

        lblTotal = rootView!!.findViewById(R.id.lblTotal)
        lblShop = rootView!!.findViewById(R.id.lblShop)
        lblItem = rootView!!.findViewById(R.id.lblItem)
        lblQuantity = rootView!!.findViewById(R.id.lblQuantity)
        lblPrice = rootView!!.findViewById(R.id.lblPrice)
        lblTotalPrice = rootView!!.findViewById(R.id.lblTotalPrice)

        lblAddress = rootView!!.findViewById(R.id.lblAddress)

    }

    private fun setOnClickListeners() {
//        knowMoreBtn!!.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {

        }

    }

    private fun myOrders() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.myOrderDetails(
                orderId

            ),
            this,
            ApiConstants.REQUEST_CODE.MY_ORDER_DETAILS
        )
    }

    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {


            ApiConstants.REQUEST_CODE.MY_ORDER_DETAILS -> {
                val apiResponse = RetrofitApiCall.getPayload(MyOrderDetailsResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    lblTotal!!.text = apiResponse.output.info[0].total
                    lblShop!!.text = apiResponse.output.info[0].shopName
                    lblItem!!.text = apiResponse.output.info[0].productName
                    lblQuantity!!.text = apiResponse.output.info[0].quantity
                    lblPrice!!.text = apiResponse.output.info[0].productPrice
                    lblTotalPrice!!.text = apiResponse.output.info[0].total

                    lblAddress!!.text = "Area : "+apiResponse.output.address[0].areaName + " ,"+
                            "Block : "+apiResponse.output.address[0].block + " ,"+
                            "Street : "+apiResponse.output.address[0].street + " ,"+
                            "Building : "+apiResponse.output.address[0].buildingNo + " ,"+
                            "Floor : "+apiResponse.output.address[0].floorNo


                }else if (apiResponse.output.success == 0) {



                }
            }





        }

        hideProgress()
//        showNoData()
    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()
    }
}