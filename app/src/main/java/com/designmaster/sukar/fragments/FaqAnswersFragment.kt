package com.designmaster.sukar.fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.FaqAnswersAdapter
import com.designmaster.sukar.adapters.FaqsCateAdapter
import com.designmaster.sukar.models.FaqAnswerInfo
import com.designmaster.sukar.models.FaqAnswerResponse
import com.designmaster.sukar.models.FaqCategoryDataField
import com.designmaster.sukar.models.FaqCategoryResponse
import com.designmaster.sukar.util.*
import java.util.ArrayList

class FaqAnswersFragment: BaseFragment(), ApiCallListener, View.OnClickListener {

    var rootView: View? = null

    var faqsCategoriesRecyclerView: RecyclerView? = null


    private var faqsCateList = ArrayList<FaqAnswerInfo>()

    var faqsAdapter: FaqAnswersAdapter? = null

    var faqId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (arguments != null) {
            faqId = requireArguments().getString("FaqId").toString()
        }

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.faq_s), true)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.faq_s), true)
        }

        // Inflate the layout for this fragment
        if (rootView == null) {

            rootView = inflater.inflate(R.layout.faqs_fragment, container, false)


            idMappings()
            setOnClickListeners()
            getFaqAnswers()
//            myOrders()

        }
        return rootView
    }

    private fun idMappings() {


        faqsCategoriesRecyclerView =
            rootView!!.findViewById<View>(R.id.faqsCategoriesRecyclerView) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        faqsCategoriesRecyclerView!!.layoutManager = mLayoutManager
        faqsCategoriesRecyclerView!!.addItemDecoration(
            GridSpacingItemDecoration(
                1,
                dpToPx(15),
                true
            )
        )
        faqsCategoriesRecyclerView!!.itemAnimator = DefaultItemAnimator()

        faqsAdapter = FaqAnswersAdapter(activity, faqsCateList)
        faqsCategoriesRecyclerView!!.adapter = faqsAdapter

        faqsAdapter!!.setOnClickListener(object : FaqAnswersAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {

//                    val fragment1 = SubCategory1Fragment()
//                    val bundle1 = Bundle()
//                    bundle1.putString("CategoryId", bagArrayList.get(position).categoryID)
//                    bundle1.putString("CategoryName", bagArrayList.get(position).categoryName)
//                    fragment1.setArguments(bundle1)
//                    (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment1).addToBackStack(null).commit()
            }

            override fun onButtonClick(pos: Int) {
                faqsAdapter!!.getItemSelected(pos)
            }


        })

    }

    private fun setOnClickListeners() {

    }


    override fun onClick(v: View) {
        when (v.id) {


        }

    }


    private fun getFaqAnswers() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.faqAnswers(
                faqId
            ),
            this,
            ApiConstants.REQUEST_CODE.FAQS_ANSWERS
        )
    }

    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {

            ApiConstants.REQUEST_CODE.FAQS_ANSWERS -> {
                val apiResponse =
                    RetrofitApiCall.getPayload(FaqAnswerResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    faqsCateList.addAll(apiResponse.output.info)
                    faqsAdapter!!.notifyDataSetChanged()
                } else if (apiResponse.output.success == 0) {

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
        } else {
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
            requireActivity().supportFragmentManager.popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
        dialog.show()
    }


    private fun errorDialog(context: Context, msg: String?) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (AppPrefs.isLocaleEnglish(activity)) {
            dialog.setContentView(R.layout.alert_dialog_box)
        } else {
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