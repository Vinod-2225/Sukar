package com.designmaster.sukar.fragments

import android.R.attr
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.CartAdapter
import com.designmaster.sukar.models.*
import com.designmaster.sukar.util.*
import java.util.ArrayList
import android.R.attr.data




class CartFragmentNew : BaseFragment(), ApiCallListener, View.OnClickListener {
    var rootView: View? = null
    var savedInstanceState: Bundle? = null
    
    var lblSubTotal: TextView? = null
    var lblDeliveryCharge: TextView? = null
    var lblOrderTotal: TextView? = null
    var btnCheckout: Button? = null
    var cartRecyclerView: RecyclerView? = null

    var cart_layout:LinearLayout? = null
    var cart_empty_layout:RelativeLayout? = null
    var btnContinue: Button? = null
    var addAddressBtn: Button? = null
    var lblAddress: TextView? = null

    var knetSelectImg: ImageView? = null
    var visaSelectImg: ImageView? = null
    var codSelectImg: ImageView? = null

    private var cartList = ArrayList<CartInfo>()

    var bagAdapter: CartAdapter? = null

    var grandTotal: String = ""
    var deliveryCharge: String = "1.500"
    var paymentMethod: String = ""
    var addressId: String = ""
    var address: String = ""

    var EXPRESS_CHECKOUT_REQUEST = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        val str = requireActivity().getSharedPreferences("JoesFam", 0)
        userId = str.getString("UserId", "0")
        language = str.getString("Language", "English")
    }

    companion object {

        var bagDetails: CartInfo? = null
        var bagArrayList = ArrayList<CartInfo>()

        var userId: String? = null
        var language: String? = null


    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.cart),false)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.cart),false)
        }

        if (rootView == null) {
            rootView = if (AppPrefs.isLocaleEnglish(activity)) {
                inflater.inflate(R.layout.cart_fragment, container, false)
            } else {
                inflater.inflate(R.layout.cart_fragment, container, false)
            }
            idMappings()
            setOnClickListeners()


            viewCart()


        }


        return rootView
    }

    private fun idMappings() {

        lblSubTotal = rootView!!.findViewById(R.id.lblSubTotal)
        lblDeliveryCharge = rootView!!.findViewById(R.id.lblDeliveryCharge)
        lblOrderTotal = rootView!!.findViewById(R.id.lblOrderTotal)

        btnCheckout = rootView!!.findViewById(R.id.btnCheckout)
        addAddressBtn = rootView!!.findViewById(R.id.addAddressBtn)
        lblAddress = rootView!!.findViewById(R.id.lblAddress)


        knetSelectImg = rootView!!.findViewById(R.id.knetSelectImg)
        visaSelectImg = rootView!!.findViewById(R.id.visaSelectImg)
        codSelectImg = rootView!!.findViewById(R.id.codSelectImg)


        cart_layout = rootView!!.findViewById(R.id.cart_layout)
        cart_empty_layout = rootView!!.findViewById(R.id.cart_empty_layout)

        cart_layout!!.visibility = View.GONE
        cart_empty_layout!!.visibility = View.GONE

        btnContinue = rootView!!.findViewById(R.id.btnContinue)

        cartRecyclerView = rootView!!.findViewById<View>(R.id.cartRecyclerView) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        cartRecyclerView!!.layoutManager = mLayoutManager
        cartRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(5), true))
        cartRecyclerView!!.itemAnimator = DefaultItemAnimator()


        bagAdapter = CartAdapter(activity, cartList)
        cartRecyclerView!!.adapter = bagAdapter

        bagAdapter!!.setOnClickListener(object : CartAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {

//                    val fragment1 = SubCategory1Fragment()
//                    val bundle1 = Bundle()
//                    bundle1.putString("CategoryId", bagArrayList.get(position).categoryID)
//                    bundle1.putString("CategoryName", bagArrayList.get(position).categoryName)
//                    fragment1.setArguments(bundle1)
//                    (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment1).addToBackStack(null).commit()
            }

            override fun onQuantityClick(pos: Int, type: String) {
//                TODO("Not yet implemented")
                updateQuantityCartList(cartList[pos].productId.toString(),type)
            }
//
//            override fun onImageClick(pos: Int) {
//                TODO("Not yet implemented")
//            }

            override fun onRemoveClick(pos: Int) {
//                TODO("Not yet implemented")

                DeleteAlertDialog(cartList[pos].productId)
            }
        })

        
    }

    private fun setOnClickListeners() {

        btnCheckout!!.setOnClickListener(this)
        btnContinue!!.setOnClickListener(this)
        addAddressBtn!!.setOnClickListener(this)
        knetSelectImg!!.setOnClickListener(this)
        visaSelectImg!!.setOnClickListener(this)
        codSelectImg!!.setOnClickListener(this)

    }
    override fun onClick(v: View) {
        when (v.id) {

            R.id.knetSelectImg -> {

                knetSelectImg!!.setBackgroundResource(R.drawable.bg_2)
                visaSelectImg!!.setBackgroundResource(R.drawable.bg_1)
                codSelectImg!!.setBackgroundResource(R.drawable.bg_1)

                paymentMethod = "1"

            }
            R.id.visaSelectImg -> {

                knetSelectImg!!.setBackgroundResource(R.drawable.bg_1)
                visaSelectImg!!.setBackgroundResource(R.drawable.bg_2)
                codSelectImg!!.setBackgroundResource(R.drawable.bg_1)

                paymentMethod = "2"

            }

            R.id.codSelectImg -> {

                knetSelectImg!!.setBackgroundResource(R.drawable.bg_1)
                visaSelectImg!!.setBackgroundResource(R.drawable.bg_1)
                codSelectImg!!.setBackgroundResource(R.drawable.bg_2)

                paymentMethod = "3"

            }
            
            R.id.btnContinue -> {

                (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()

                startActivity(
                        Intent(
                                activity,
                                MainActivity::class.java
                        )
                )
                activity?.finishAffinity()
            }

            R.id.btnCheckout -> {

                if(addressId == ""){
                    errorDialog(requireActivity(),"Please select the address")
                }else if(paymentMethod == ""){
                    errorDialog(requireActivity(),"Please select the payment method")
                }else{
                    orderNow()
                }


            }

            R.id.addAddressBtn -> {
                val fragment = ShippingAddressFragment()
                fragment.setTargetFragment(
                    this@CartFragmentNew,
                    EXPRESS_CHECKOUT_REQUEST
                )
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()


//                val fragment1 = ExpressCheckoutFragment()
//                fragment1.setTargetFragment(
//                    this@LoginFragmentFromCheckOut,
//                    EXPRESS_CHECKOUT_REQUEST
//                )
//                val fragmentManager: FragmentManager = activity!!.supportFragmentManager
//                fragmentManager.beginTransaction() //                            .remove(fragment2)
//                    //                            .add(R.id.fragment_container, fragment1, "RegisterFragment")
//                    .replace(R.id.fragment_container, fragment1, "ExpressCheckoutFragment")
//                    .addToBackStack(null) // .setCustomAnimations(R.anim.right_to_left, R.anim.fadeout_2,0, R.anim.left_to_right)
//                    .commitAllowingStateLoss()
            }

        }
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }

    }

//    /**
//     * Converting dp to pixel
//     */
//    private override fun dpToPx(dp: Int): Int {
//        val r = activity!!.resources
//        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
//    }

    private fun viewCart() {
        showProgress()
        RetrofitApiCall.hitApi(
                ApiClient.apiInterFace.getCartList(
                        AppPrefs.getUserId(activity).toString()

                ),
                this,
                ApiConstants.REQUEST_CODE.CART_LIST
        )
    }

    private fun removeFromCart(productId: String) {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.removeFromCartList(
                AppPrefs.getUserId(requireActivity()).toString(),
                productId
            ),
            this,
            ApiConstants.REQUEST_CODE.REMOVE_FROM_CART_LIST
        )

    }

    private fun updateQuantityCartList(productId: String,type: String) {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.updateQuantityCartList(
                AppPrefs.getUserId(requireActivity()).toString(),
                productId,
                type
            ),
            this,
            ApiConstants.REQUEST_CODE.UPDATE_QUANTITY_CART_LIST
        )

    }


    private fun orderNow() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.orderNow(
                AppPrefs.getUserId(requireActivity()).toString(),
                addressId,
                deliveryCharge,
                paymentMethod,
                grandTotal,
                "0"
            ),
            this,
            ApiConstants.REQUEST_CODE.ORDER_NOW
        )

    }



    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {


            ApiConstants.REQUEST_CODE.CART_LIST -> {
                val apiResponse = RetrofitApiCall.getPayload(CartResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    cartList.clear()

                    apiResponse?.output?.info.let {
                        if (it != null) {
                            cartList.addAll(it)
                        }
                    }
//                    (activity as MainActivity?)!!.setBadge(apiResponse.response?.cartItemCount!!)
                    bagAdapter!!.notifyDataSetChanged()


                    val f1: Float = apiResponse.output.subTotal.toString().toFloat()
                    val f2: Float = deliveryCharge.toFloat()
                    val f3: Float = f1 + f2
//                    val total_price: String = String.format("%.3f", f1)
//                    val sub_total_price: String = String.format("%.3f", f2)


                    lblSubTotal!!.text = String.format("%.3f", f1)+ " KWD"
                    lblDeliveryCharge!!.text = String.format("%.3f", f2)+ " KWD"
                    lblOrderTotal!!.text = String.format("%.3f", f3)+ " KWD"

                    grandTotal = f3.toString()
                    deliveryCharge = f2.toString()

                    cart_layout!!.visibility = View.VISIBLE
                    cart_empty_layout!!.visibility = View.GONE

                }else if (apiResponse.output.success == 0) {
                    cartList.clear()

                    bagAdapter!!.notifyDataSetChanged()

                    lblSubTotal!!.text = "0.000 "+ " KWD"
                    lblDeliveryCharge!!.text = "0.000 "+ " KWD"
                    lblOrderTotal!!.text = "0.000 "+ " KWD"


                    cart_layout!!.visibility = View.GONE
                    cart_empty_layout!!.visibility = View.VISIBLE

                    cartList.clear()


                }
            }

            ApiConstants.REQUEST_CODE.REMOVE_FROM_CART_LIST -> {

                viewCart()

            }

            ApiConstants.REQUEST_CODE.UPDATE_QUANTITY_CART_LIST -> {

                viewCart()

            }
//
            ApiConstants.REQUEST_CODE.ORDER_NOW -> {

                if(paymentMethod == "1"){
                    val apiResponse = RetrofitApiCall.getPayload(KnetOrderResponse::class.java, response)

                    if (apiResponse.IsSuccess) {
                        val fragment = PaymentFragment()
                        val bundle = Bundle()
                        bundle.putString("Url", apiResponse.Data.PaymentURL)
                        fragment.arguments = bundle
                        (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit()
                    }

                }else if(paymentMethod == "3"){
                    val apiResponse = RetrofitApiCall.getPayload(CashOrderResponse::class.java, response)

                    if (apiResponse.IsSuccess == 2) {

                    }


                }


            }
//
//            ApiConstants.REQUEST_CODE.APPLY_COUPON -> {
//                val apiResponse = RetrofitApiCall.getPayload(ApiResponse::class.java, response)
//
//                if (apiResponse.status == ApiConstants.RESPONSE_CODE.API_STATUS_1){
//                    alertDialog(activity!!, apiResponse.msg)
//                }else if (apiResponse.status == ApiConstants.RESPONSE_CODE.API_STATUS_0){
//                    alertDialog(activity!!, apiResponse.msg)
//                }
//
//            }

        }

        hideProgress()
//        showNoData()
    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()
    }


//    fun DeleteAlertDialog(productId: String,sizeId: String,colorId: String, quantity: String) {
//        val dialog = Dialog(activity!!)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        if (AppPrefs.isLocaleEnglish(activity)) {
//            dialog.setContentView(R.layout.delete_alert_dialog)
//        }else{
//            dialog.setContentView(R.layout.delete_alert_dialog_ar)
//        }
//
//        val layoutParams = dialog.window!!.attributes
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
//        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
//        dialog.window!!.attributes = layoutParams
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.setCancelable(false)
//        dialog.setCanceledOnTouchOutside(false)
//        val alertMessage = dialog.findViewById<View>(R.id.alertMessage) as TextView
//        val okText = dialog.findViewById<View>(R.id.okText) as TextView
//        val cancelText = dialog.findViewById<View>(R.id.cancelText) as TextView
//        if (AppPrefs.isLocaleEnglish(activity)) {
//            alertMessage.text = "Are you sure you want to delete?"
//            okText.text = activity!!.getResources().getString(R.string.yes)
//            cancelText.text = activity!!.getResources().getString(R.string.no)
//        } else {
//            alertMessage.text = "هل أنت متأكد أنك تريد حذفه؟"
//            okText.text = activity!!.getResources().getString(R.string.yes_ar)
//            cancelText.text = activity!!.getResources().getString(R.string.no_ar)
//        }
//        okText.setOnClickListener {
//            dialog.dismiss()
//
////                fragment.RemoveFromCartTask(product_Id,product_Price);
//            updateCart(productId,sizeId,colorId,quantity)
//        }
//        cancelText.setOnClickListener { dialog.dismiss() }
//        dialog.show()
//    }


    


    fun errorDialog(context: Context, msg: String?) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (AppPrefs.isLocaleEnglish(activity)) {
            dialog.setContentView(R.layout.alert_dialog_box)
        }else{
            dialog.setContentView(R.layout.alert_dialog_box_ar)
        }
        val layoutParams = dialog.window!!.attributes
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = layoutParams
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val alertMessage = dialog.findViewById<View>(R.id.alertMessage) as TextView
        alertMessage.text = msg
        val okText = dialog.findViewById<View>(R.id.okText) as TextView

        if (AppPrefs.isLocaleEnglish(activity)) {
            okText.text = context.getString(R.string.ok)
        } else {
            okText.text = context.getString(R.string.ok_ar)
        }
        okText.setOnClickListener {
            dialog.dismiss()

        }
        dialog.show()
    }

    fun alertDialog(context: Context, msg: String?) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (AppPrefs.isLocaleEnglish(activity)) {
            dialog.setContentView(R.layout.alert_dialog_box)
        }else{
            dialog.setContentView(R.layout.alert_dialog_box_ar)
        }
        val layoutParams = dialog.window!!.attributes
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = layoutParams
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(true)
        val alertMessage = dialog.findViewById<View>(R.id.alertMessage) as TextView
        alertMessage.text = msg
        val okText = dialog.findViewById<View>(R.id.okText) as TextView

        if (AppPrefs.isLocaleEnglish(activity)) {
            okText.text = context.getString(R.string.ok)
        } else {
            okText.text = context.getString(R.string.ok_ar)
        }
        okText.setOnClickListener {
            dialog.dismiss()
            viewCart()
        }
        dialog.show()
    }

    fun DeleteAlertDialog(productId: String) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (AppPrefs.isLocaleEnglish(activity)) {
            dialog.setContentView(R.layout.delete_alert_dialog)
        }else{
            dialog.setContentView(R.layout.delete_alert_dialog)
        }

        val layoutParams = dialog.window!!.attributes
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        dialog.window!!.attributes = layoutParams
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        val alertMessage = dialog.findViewById<View>(R.id.alertMessage) as TextView
        val okText = dialog.findViewById<View>(R.id.okText) as TextView
        val cancelText = dialog.findViewById<View>(R.id.cancelText) as TextView
        if (AppPrefs.isLocaleEnglish(activity)) {
            alertMessage.text = "Are you sure you want to delete?"
            okText.text = requireActivity().getResources().getString(R.string.yes)
            cancelText.text = requireActivity().getResources().getString(R.string.no)
        } else {
            alertMessage.text = "هل أنت متأكد أنك تريد حذفه؟"
            okText.text = requireActivity().getResources().getString(R.string.yes_ar)
            cancelText.text = requireActivity().getResources().getString(R.string.no_ar)
        }
        okText.setOnClickListener {
            dialog.dismiss()

//                fragment.RemoveFromCartTask(product_Id,product_Price);
            removeFromCart(productId)
        }
        cancelText.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == EXPRESS_CHECKOUT_REQUEST) {
                addressId = data!!.getStringExtra("AddressId").toString()
                val address: String = data!!.getStringExtra("Address").toString()

                lblAddress!!.text = address

            }
        }
    }
}