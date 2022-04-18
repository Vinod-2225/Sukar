package com.designmaster.sukar.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.AddressListAdapter
import com.designmaster.sukar.adapters.CartAdapter
import com.designmaster.sukar.models.*
import com.designmaster.sukar.util.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedAddressFragment : BaseFragment(), ApiCallListener, View.OnClickListener {
    var rootView: View? = null
    var savedInstanceState: Bundle? = null

    var addShippingAddressTxt: TextView? = null
    
    var addressesRecyclerView: RecyclerView? = null

    var addressLayout:RelativeLayout? = null
    var addressEmptyLayout:RelativeLayout? = null
    var btnContinue: Button? = null


    private var addressInfo = ArrayList<AddressInfo>()

    var addressListAdapter: AddressListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState


    }

    companion object {

        var userId: String? = null
        var language: String? = null

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders("",false)
        } else {
            (activity as MainActivity?)!!.setHeaders("",false)
        }

        if (rootView == null) {
            rootView = if (AppPrefs.isLocaleEnglish(activity)) {
                inflater.inflate(R.layout.addresses_fragment, container, false)
            } else {
                inflater.inflate(R.layout.addresses_fragment, container, false)
            }
            idMappings()
            setOnClickListeners()


            getAddressList()


        }


        return rootView
    }

    private fun idMappings() {

        addShippingAddressTxt = rootView!!.findViewById(R.id.addShippingAddressTxt)
        


        addressLayout = rootView!!.findViewById(R.id.addressLayout)
        addressEmptyLayout = rootView!!.findViewById(R.id.addressEmptyLayout)

        addressLayout!!.visibility = View.GONE
        addressEmptyLayout!!.visibility = View.GONE

        btnContinue = rootView!!.findViewById(R.id.btnContinue)

        addressesRecyclerView = rootView!!.findViewById<View>(R.id.addressesRecyclerView) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        addressesRecyclerView!!.layoutManager = mLayoutManager
        addressesRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(5), true))
        addressesRecyclerView!!.itemAnimator = DefaultItemAnimator()


        addressListAdapter = AddressListAdapter(activity, addressInfo)
        addressesRecyclerView!!.adapter = addressListAdapter

        addressListAdapter!!.setOnClickListener(object : AddressListAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {

//                    val fragment1 = SubCategory1Fragment()
//                    val bundle1 = Bundle()
//                    bundle1.putString("CategoryId", bagArrayList.get(position).categoryID)
//                    bundle1.putString("CategoryName", bagArrayList.get(position).categoryName)
//                    fragment1.setArguments(bundle1)
//                    (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment1).addToBackStack(null).commit()
            }


        })


    }

    private fun setOnClickListeners() {

        btnContinue!!.setOnClickListener(this)
        addShippingAddressTxt!!.setOnClickListener(this)

    }
    override fun onClick(v: View) {
        when (v.id) {


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

            R.id.addShippingAddressTxt -> {

                val fragment = AddAddressFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
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

    private fun getAddressList() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getAddressList(
                AppPrefs.getUserId(activity).toString()

            ),
            this,
            ApiConstants.REQUEST_CODE.ADDRESS_LIST
        )
    }





    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {


            ApiConstants.REQUEST_CODE.ADDRESS_LIST -> {
                val apiResponse = RetrofitApiCall.getPayload(AddressListResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    addressInfo.clear()

                    apiResponse?.output?.info.let {
                        if (it != null) {
                            addressInfo.addAll(it)
                        }
                    }
//                    (activity as MainActivity?)!!.setBadge(apiResponse.response?.cartItemCount!!)
//                    addressListAdapter!!.notifyDataSetChanged()

//                    addressListAdapter = AddressListAdapter(activity, addressInfo)
//                    addressesRecyclerView!!.adapter = addressListAdapter
                    addressLayout!!.visibility = View.VISIBLE
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

//    fun alertDialog(context: Context, msg: String?) {
//        val dialog = Dialog(context)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        if (AppPrefs.isLocaleEnglish(activity)) {
//            dialog.setContentView(R.layout.alert_dialog_box)
//        }else{
//            dialog.setContentView(R.layout.alert_dialog_box_ar)
//        }
//        val layoutParams = dialog.window!!.attributes
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
//        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
//        dialog.window!!.attributes = layoutParams
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.setCancelable(true)
//        val alertMessage = dialog.findViewById<View>(R.id.alertMessage) as TextView
//        alertMessage.text = msg
//        val okText = dialog.findViewById<View>(R.id.okText) as TextView
//
//        if (AppPrefs.isLocaleEnglish(activity)) {
//            okText.text = context.getString(R.string.ok)
//        } else {
//            okText.text = context.getString(R.string.ok_ar)
//        }
//        okText.setOnClickListener {
//            dialog.dismiss()
//            viewCart()
//        }
//        dialog.show()
//    }

//    fun DeleteAlertDialog(productId: String) {
//        val dialog = Dialog(requireActivity())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        if (AppPrefs.isLocaleEnglish(activity)) {
//            dialog.setContentView(R.layout.delete_alert_dialog)
//        }else{
//            dialog.setContentView(R.layout.delete_alert_dialog)
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
//            okText.text = requireActivity().getResources().getString(R.string.yes)
//            cancelText.text = requireActivity().getResources().getString(R.string.no)
//        } else {
//            alertMessage.text = "هل أنت متأكد أنك تريد حذفه؟"
//            okText.text = requireActivity().getResources().getString(R.string.yes_ar)
//            cancelText.text = requireActivity().getResources().getString(R.string.no_ar)
//        }
//        okText.setOnClickListener {
//            dialog.dismiss()
//
////                fragment.RemoveFromCartTask(product_Id,product_Price);
//            removeFromCart(productId)
//        }
//        cancelText.setOnClickListener { dialog.dismiss() }
//        dialog.show()
//    }
}