package com.designmaster.sukar.fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.models.*
import com.designmaster.sukar.util.*

class OpenAStoreFragment : BaseFragment(), ApiCallListener, View.OnClickListener {

    var rootView: View? = null

    lateinit var companyNameEdit: EditText;
    lateinit var contactNameEdit: EditText;
    lateinit var mobileNoEdit: EditText;
    lateinit var emailEdit: EditText;
    lateinit var yearOfEstablishmentEdit: EditText;
    lateinit var specialityFoodTypeEdit: EditText;
    lateinit var websiteEdit: EditText;
    lateinit var socialMediaAccountsEdit: EditText;
    lateinit var btnSubmit: Button;
//    lateinit var companyNameEdit: EditText;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.open_a_store),true)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.open_a_store),true)
        }

        // Inflate the layout for this fragment
        if (rootView == null) {

            rootView = inflater.inflate(R.layout.open_a_strore_fragment, container, false)


            idMappings()
            setOnClickListeners()
//            myOrders()

        }
        return rootView
    }

    private fun idMappings() {


        companyNameEdit = rootView!!.findViewById(R.id.companyNameEdit)
        contactNameEdit = rootView!!.findViewById(R.id.contactNameEdit)
        mobileNoEdit = rootView!!.findViewById(R.id.mobileNoEdit)
        emailEdit = rootView!!.findViewById(R.id.emailEdit)
        yearOfEstablishmentEdit = rootView!!.findViewById(R.id.yearOfEstablishmentEdit)
        specialityFoodTypeEdit = rootView!!.findViewById(R.id.specialityFoodTypeEdit)
        websiteEdit = rootView!!.findViewById(R.id.websiteEdit)
        socialMediaAccountsEdit = rootView!!.findViewById(R.id.socialMediaAccountsEdit)
        btnSubmit = rootView!!.findViewById(R.id.btnSubmit)
//        companyNameEdit = rootView!!.findViewById(R.id.companyNameEdit)



    }

    private fun setOnClickListeners() {
        btnSubmit.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {

            R.id.btnSubmit ->{
                openAStore()
            }
        }

    }
    //    company_name:design master global it ovt ltd
//    email:designmaste@gmail.com
//    mobile:888888888
//    contact_name:anji
//    year_establishment:2008
//    special_food_type:all
//    physical_store:yes, kuwait
//    links:example.com
    private fun openAStore() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.openAStore(
                companyNameEdit.text.toString(),
                emailEdit.text.toString(),
                mobileNoEdit.text.toString(),
                contactNameEdit.text.toString(),
                yearOfEstablishmentEdit.text.toString(),
                specialityFoodTypeEdit.text.toString(),
                "Yes",
                websiteEdit.text.toString(),
                "nothing",
                "",
                "+965"
            ),
            this,
            ApiConstants.REQUEST_CODE.MY_ORDERS
        )
    }

    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {

            ApiConstants.REQUEST_CODE.OPEN_A_STORE -> {
                val apiResponse = RetrofitApiCall.getPayload(OpenStoreResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    alertDialog(requireActivity(),"Submitted Successfully")

                }else if (apiResponse.output.success == 0) {

//hhhhh
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
}