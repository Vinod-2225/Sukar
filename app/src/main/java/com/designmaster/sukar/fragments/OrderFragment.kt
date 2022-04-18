package com.designmaster.sukar.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.CartAdapter
import com.designmaster.sukar.adapters.MyOrdersAdapter
import com.designmaster.sukar.models.*
import com.designmaster.sukar.util.*
import java.util.ArrayList


class OrderFragment : BaseFragment(), ApiCallListener, View.OnClickListener {

    var rootView: View? = null

    var myOrdersRecyclerView: RecyclerView? = null
    var myOrdersAdapter: MyOrdersAdapter? = null

    private var myOrdersInfo = ArrayList<MyOrdersInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.orders),true)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.orders),true)
        }

        // Inflate the layout for this fragment
        if (rootView == null) {

            rootView = inflater.inflate(R.layout.my_orders_fragment, container, false)


            idMappings()
            setOnClickListeners()
            myOrders()

        }
        return rootView
    }

    private fun idMappings() {

        myOrdersRecyclerView = rootView!!.findViewById<View>(R.id.myOrdersRecyclerView) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        myOrdersRecyclerView!!.layoutManager = mLayoutManager
        myOrdersRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(5), true))
        myOrdersRecyclerView!!.itemAnimator = DefaultItemAnimator()

        myOrdersAdapter = MyOrdersAdapter(activity, myOrdersInfo)
        myOrdersRecyclerView!!.adapter = myOrdersAdapter

        myOrdersAdapter!!.setOnClickListener(object : MyOrdersAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {

//                    val fragment1 = SubCategory1Fragment()
//                    val bundle1 = Bundle()
//                    bundle1.putString("CategoryId", bagArrayList.get(position).categoryID)
//                    bundle1.putString("CategoryName", bagArrayList.get(position).categoryName)
//                    fragment1.setArguments(bundle1)
//                    (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment1).addToBackStack(null).commit()
            }

            override fun onDetailsClick(pos: Int) {
                val fragment = OrderDetailsFragment()
                val bundle = Bundle()
                bundle.putString("OrderId", myOrdersInfo[pos].OrderID)
                fragment.arguments = bundle
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }

//            override fun onQuantityClick(pos: Int, type: String) {
//
//            }
//
//
//            override fun onRemoveClick(pos: Int) {
////                TODO("Not yet implemented")
//
////                DeleteAlertDialog(cartList[pos].productId)
//            }
        })

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
            ApiClient.apiInterFace.myOrders(
                AppPrefs.getUserId(activity).toString()

            ),
            this,
            ApiConstants.REQUEST_CODE.MY_ORDERS
        )
    }

    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {


            ApiConstants.REQUEST_CODE.MY_ORDERS -> {
                val apiResponse = RetrofitApiCall.getPayload(MyOrdersResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    myOrdersInfo.clear()

                    apiResponse?.output?.info.let {
                        if (it != null) {
                            myOrdersInfo.addAll(it)
                        }
                    }
//                    (activity as MainActivity?)!!.setBadge(apiResponse.response?.cartItemCount!!)
                    myOrdersAdapter!!.notifyDataSetChanged()





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