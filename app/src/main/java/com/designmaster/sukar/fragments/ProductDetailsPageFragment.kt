package com.designmaster.sukar.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.activities.ZoomtestActivity
import com.designmaster.sukar.models.AddToCartResponse
import com.designmaster.sukar.models.ProductDetailsResponse
import com.designmaster.sukar.util.*

class ProductDetailsPageFragment: BaseFragment(), View.OnClickListener, ApiCallListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var backicon: ImageView
//    lateinit var addbtnrl: RelativeLayout
    lateinit var enlargerl: RelativeLayout
    lateinit var enlargeiconrl: RelativeLayout
    lateinit var enlargeimg: ImageView
    lateinit var chocoimg: ImageView

    lateinit var productNameTv: TextView
    lateinit var brandTv: TextView
    lateinit var productPriceTv: TextView
    lateinit var productDescriptionTv: TextView
    lateinit var minusBtn: Button
    lateinit var plusBtn: Button
    lateinit var addBtn: Button

    lateinit var lblQuantity: TextView
    lateinit var optinal: EditText

    var productId: String = ""

    var productStock: String = ""

    var totalPrice: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders("",true)
        } else {
            (activity as MainActivity?)!!.setHeaders("",true)
        }

        if (arguments != null) {
            productId = requireArguments().getString("ProductId").toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.product_details_page_fragment,
            container, false


        )
        findviewbyid(view)

        getProductsDetailsByProId()

        return view
    }

    private fun findviewbyid(view: View) {
        enlargeimg=view.findViewById(R.id.enlarge) as ImageView
        chocoimg=view.findViewById(R.id.chocoimg) as ImageView
        backicon=view.findViewById(R.id.backicon) as ImageView
        backicon.setOnClickListener(this)

        enlargerl=view.findViewById(R.id.enlargeiconrl) as RelativeLayout
        enlargerl.setOnClickListener(this)

//        addbtnrl=view.findViewById(R.id.addbtn) as RelativeLayout
//        addbtnrl.setOnClickListener(this)

        enlargerl=view.findViewById(R.id.enlargeicon) as RelativeLayout
        enlargerl.setOnClickListener(this)

        productNameTv=view.findViewById(R.id.productNameTv) as TextView
        brandTv=view.findViewById(R.id.brandTv) as TextView
        productPriceTv=view.findViewById(R.id.productPriceTv) as TextView
        productDescriptionTv=view.findViewById(R.id.productDescriptionTv) as TextView
        minusBtn=view.findViewById(R.id.minusBtn) as Button
        plusBtn=view.findViewById(R.id.plusBtn) as Button
        addBtn=view.findViewById(R.id.addBtn) as Button
        lblQuantity=view.findViewById(R.id.lblQuantity) as TextView
        optinal=view.findViewById(R.id.optinal) as EditText


        minusBtn.setOnClickListener(this)
        plusBtn.setOnClickListener(this)
        addBtn.setOnClickListener(this)
    }



    override fun onClick(v: View) {
        when(v.id) {
            R.id.backicon -> {
                val fragment = ProductsFragmentcnt()
                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
//            R.id.addbtn -> {
//                val fragment = CartdetailsFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()
//            }

            R.id.enlargeiconrl -> {
/*
                chocoimg.buildDrawingCache()
                val bitmap: Bitmap = chocoimg.getDrawingCache()
                val intent = Intent(context, ZoomtestActivity::class.java)
                intent.putExtra("BitmapImage", bitmap)
                context?.startActivity(intent)
*/
                val intent = Intent(context, ZoomtestActivity::class.java)
                intent.putExtra("resId", R.drawable.whishlistimg1)
                startActivity(intent)

            }

            R.id.minusBtn -> {
                if (lblQuantity.text.toString().toInt() > 1) {
                    val product_quantity_count = lblQuantity!!.text.toString().toInt() - 1
                    lblQuantity.text = "" + product_quantity_count

                }
            }

            R.id.plusBtn -> {

                if(lblQuantity.text.toString().toInt()<productStock.toString().toInt()){
                    val product_quantity_count = lblQuantity.text.toString().toInt() + 1
                    lblQuantity.text = "" + product_quantity_count
                }

            }

            R.id.addBtn -> {
                addToCart()
            }
        }


    }


    private fun getProductsDetailsByProId() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getProductsDetailsByProId(
                productId
            ),
            this,
            ApiConstants.REQUEST_CODE.PRODUCTS_DETAILS_BY_ID
        )

    }


    private fun addToCart() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.addToCart(
                AppPrefs.getUserId(requireActivity()).toString(),
                productId,
                lblQuantity.text.toString(),
                optinal.text.toString(),
                "",totalPrice
            ),
            this,
            ApiConstants.REQUEST_CODE.ADD_TO_CART
        )

    }

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.PRODUCTS_DETAILS_BY_ID -> {
                val apiResponse = RetrofitApiCall.getPayload(
                    ProductDetailsResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {

                    if (AppPrefs.isLocaleEnglish(requireActivity())) {
                        productNameTv.text = apiResponse.output.info[0].titleEn
                        productDescriptionTv.text = AppUtils.parseHtml(apiResponse.output.info[0].contentEn)
                    }else{
                        productNameTv.text = apiResponse.output.info[0].titleAr
                        productDescriptionTv.text = AppUtils.parseHtml(apiResponse.output.info[0].contentAr)
                    }

                    brandTv.text = apiResponse.output.info[0].shopName
                    productPriceTv.text = apiResponse.output.info[0].price+" KWD"

                    totalPrice = apiResponse.output.info[0].price

                    productStock = apiResponse.output.info[0].quantity

                    requireActivity()?.let {
                        Glide.with(it)
                            .load(apiResponse.output.info[0].thumbimg1)
                            .transform(CenterCrop())
                            .into(chocoimg)
                    }

                }
            }

            ApiConstants.REQUEST_CODE.ADD_TO_CART -> {
                val apiResponse = RetrofitApiCall.getPayload(
                    AddToCartResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {

                    if (AppPrefs.isLocaleEnglish(requireActivity())) {
                        successDialog(apiResponse.output.message)
                    }else{
                        successDialog(apiResponse.output.message)
                    }




                }
            }
        }
        hideProgress()

    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()

    }


    fun successDialog(msg: String?) {
        val dialog = Dialog(requireActivity())
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
            okText.text = requireActivity().getString(R.string.ok)
        } else {
            okText.text = requireActivity().getString(R.string.ok_ar)
        }
        okText.setOnClickListener {
            dialog.dismiss()


        }
        dialog.show()
    }
}