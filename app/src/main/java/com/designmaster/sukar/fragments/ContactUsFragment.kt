package com.designmaster.sukar.fragments


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.models.OpenStoreResponse
import com.designmaster.sukar.util.*


class ContactusFragment : BaseFragment(), ApiCallListener, View.OnClickListener {

    var rootView: View? = null

    lateinit var nameEdit: EditText;
    lateinit var emailEdit: EditText;
    lateinit var mobileNoEdit: EditText;
    lateinit var commentsEdit: EditText;

    lateinit var btnSubmit: Button;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.contact_us),true)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.contact_us),true)
        }

        // Inflate the layout for this fragment
        if (rootView == null) {

            rootView = inflater.inflate(R.layout.contact_us_fragment, container, false)


            idMappings()
            setOnClickListeners()
//            myOrders()

        }
        return rootView
    }

    private fun idMappings() {


        nameEdit = rootView!!.findViewById(R.id.nameEdit)
        emailEdit = rootView!!.findViewById(R.id.emailEdit)
        mobileNoEdit = rootView!!.findViewById(R.id.mobileNoEdit)
        commentsEdit = rootView!!.findViewById(R.id.commentsEdit)

        btnSubmit = rootView!!.findViewById(R.id.btnSubmit)



    }

    private fun setOnClickListeners() {
        btnSubmit.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {

            R.id.btnSubmit ->{
                if(nameEdit.text.toString() == ""){
                    errorDialog(requireActivity(),"Please enter your name")
                }else if(emailEdit.text.toString() == ""){
                    errorDialog(requireActivity(),"Please enter your email address")
                }else if(mobileNoEdit.text.toString() == ""){
                    errorDialog(requireActivity(),"Please enter your mobile number")
                }else if(commentsEdit.text.toString() == ""){
                    errorDialog(requireActivity(),"Please enter your comments")
                }else{
                    contactUs()
                }

            }
        }

    }


    private fun contactUs() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.contactUs(
                nameEdit.text.toString(),
                emailEdit.text.toString(),
                "+965",
                mobileNoEdit.text.toString(),
                commentsEdit.text.toString(),
                "10-10-2021"
            ),
            this,
            ApiConstants.REQUEST_CODE.CONTACT_US
        )
    }

    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {

            ApiConstants.REQUEST_CODE.CONTACT_US -> {
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


    private fun errorDialog(context: Context, msg: String?) {
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
}