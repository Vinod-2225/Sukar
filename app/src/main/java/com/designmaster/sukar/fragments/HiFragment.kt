package com.designmaster.sukar.fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.models.AddAddressResponse
import com.designmaster.sukar.models.areas.AreaInfo
import com.designmaster.sukar.models.governorates.GovernorateInfo
import com.designmaster.sukar.util.*
import com.rcd.bato.models.ourServices.AreaResponse
import com.rcd.bato.models.ourServices.GovernorateResponse
import java.util.ArrayList

class HiFragment : BaseFragment(), ApiCallListener, View.OnClickListener {
    var rootView: View? = null
    var savedInstanceState: Bundle? = null

    var homeAddressTxt: TextView? = null
    var governorateLayout: RelativeLayout? = null
    var governorateTxt: TextView? = null
    var areaLayout: RelativeLayout? = null
    var areaTxt: TextView? = null
    var blockEditTxt: TextView? = null
    var streetEditTxt: TextView? = null
    var buildingNoEditTxt: TextView? = null
    var floorNoEditTxt: TextView? = null
    var descriptionEditTxt: TextView? = null

    private lateinit var btnSubmit: Button

    private var governorateInfo = ArrayList<GovernorateInfo>()
    private var areaInfo = ArrayList<AreaInfo>()

    var gov_Id: String = ""
    var area_Id: String = ""

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
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.add_shipping_address),false)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.add_shipping_address),false)
        }

        if (rootView == null) {
            rootView = if (AppPrefs.isLocaleEnglish(activity)) {
                inflater.inflate(R.layout.add_address_fragment, container, false)
            } else {
                inflater.inflate(R.layout.add_address_fragment, container, false)
            }
            idMappings()
            setOnClickListeners()


            getGovernorates()


        }


        return rootView
    }

    private fun idMappings() {


        homeAddressTxt = rootView!!.findViewById(R.id.homeAddressTxt)
        governorateLayout = rootView!!.findViewById(R.id.governorateLayout)
        governorateTxt = rootView!!.findViewById(R.id.governorateTxt)
        areaLayout = rootView!!.findViewById(R.id.areaLayout)
        areaTxt = rootView!!.findViewById(R.id.areaTxt)
        blockEditTxt = rootView!!.findViewById(R.id.blockEditTxt)
        streetEditTxt = rootView!!.findViewById(R.id.streetEditTxt)
        buildingNoEditTxt = rootView!!.findViewById(R.id.buildingNoEditTxt)
        floorNoEditTxt = rootView!!.findViewById(R.id.floorNoEditTxt)
        descriptionEditTxt = rootView!!.findViewById(R.id.descriptionEditTxt)

        btnSubmit = rootView!!.findViewById(R.id.btnSubmit)


    }

    private fun setOnClickListeners() {

        btnSubmit!!.setOnClickListener(this)
        governorateLayout!!.setOnClickListener(this)
        areaLayout!!.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when (v.id) {


            R.id.btnSubmit -> {

                addAddress()

            }
            R.id.governorateLayout -> {

                val dialog = SelectGovernorateDialogFragment.getInstance(governorateInfo!!)
                dialog.isCancelable = true
                dialog.setOnClick(object : SelectGovernorateDialogFragment.ClickListener {
                    override fun onItemClick(pos: Int) {

                        governorateTxt!!.text = governorateInfo[pos].titleEn
                        gov_Id = governorateInfo[pos].id.toString()

                        areaTxt!!.text = "Area"
                        area_Id = ""

                        getAreas()

                    }
                })
                dialog.show(requireActivity().supportFragmentManager, "governorateDialog")
            }

            R.id.areaLayout -> {

                val dialog = SelectAreaDialogFragment.getInstance(areaInfo!!)
                dialog.isCancelable = true
                dialog.setOnClick(object : SelectAreaDialogFragment.ClickListener {
                    override fun onItemClick(pos: Int) {

                        areaTxt!!.text = areaInfo[pos].titleEn
                        area_Id = areaInfo[pos].id.toString()


                    }
                })
                dialog.show(requireActivity().supportFragmentManager, "governorateDialog")
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



    private fun getGovernorates() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getGovernorates(
            ),
            this,
            ApiConstants.REQUEST_CODE.GOVERNORATES
        )
    }


    private fun getAreas() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getAreas(
                gov_Id
            ),
            this,
            ApiConstants.REQUEST_CODE.AREAS
        )
    }


    private fun addAddress() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.addAddress(
                AppPrefs.getUserId(activity).toString(),
                "",
                "",
                "",
                "",
                area_Id,
                gov_Id,
                buildingNoEditTxt!!.text.toString(),
                blockEditTxt!!.text.toString(),
                streetEditTxt!!.text.toString(),
                floorNoEditTxt!!.text.toString(),
                "",
                ""


            ),
            this,
            ApiConstants.REQUEST_CODE.ADD_ADDRESS
        )
    }





    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {


            ApiConstants.REQUEST_CODE.GOVERNORATES -> {
                val apiResponse = RetrofitApiCall.getPayload(GovernorateResponse::class.java, response)
                if (apiResponse.output!!.success == 1) {

                    governorateInfo.clear()

                    apiResponse?.output?.info.let {
                        if (it != null) {
                            governorateInfo.addAll(it)
                        }
                    }
                }
            }

            ApiConstants.REQUEST_CODE.AREAS -> {
                val apiResponse = RetrofitApiCall.getPayload(AreaResponse::class.java, response)
                if (apiResponse.output!!.success == 1) {

                    areaInfo.clear()

                    apiResponse?.output?.info.let {
                        if (it != null) {
                            areaInfo.addAll(it)
                        }
                    }
                }
            }


            ApiConstants.REQUEST_CODE.ADD_ADDRESS -> {
                val apiResponse = RetrofitApiCall.getPayload(AddAddressResponse::class.java, response)
                if (apiResponse.output!!.success == 1) {

                    alertDialog(requireActivity(),apiResponse.output.message)
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

    private fun alertDialog(context: Context, msg: String?) {
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
            requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        dialog.show()
    }

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
}