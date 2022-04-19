package com.designmaster.sukar.fragments


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.CartAdapter
import com.designmaster.sukar.adapters.FaqsCateAdapter
import com.designmaster.sukar.models.CartInfo
import com.designmaster.sukar.models.FaqCategoryDataField
import com.designmaster.sukar.models.FaqCategoryResponse
import com.designmaster.sukar.models.OpenStoreResponse
import com.designmaster.sukar.util.*
import java.util.ArrayList



class FAQsFragment : BaseFragment(), ApiCallListener, View.OnClickListener {

    var rootView: View? = null

    var faqsCategoriesRecyclerView: RecyclerView? = null


    private var faqsCateList = ArrayList<FaqCategoryDataField>()

    var faqsAdapter: FaqsCateAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.faq_s),true)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.faq_s),true)
        }

        // Inflate the layout for this fragment
        if (rootView == null) {

            rootView = inflater.inflate(R.layout.faqs_fragment, container, false)


            idMappings()
            setOnClickListeners()
            getFaqCategories()
//            myOrders()

        }
        return rootView
    }

    private fun idMappings() {


        faqsCategoriesRecyclerView = rootView!!.findViewById<View>(R.id.faqsCategoriesRecyclerView) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        faqsCategoriesRecyclerView!!.layoutManager = mLayoutManager
        faqsCategoriesRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(15), true))
        faqsCategoriesRecyclerView!!.itemAnimator = DefaultItemAnimator()

        faqsAdapter = FaqsCateAdapter(activity, faqsCateList)
        faqsCategoriesRecyclerView!!.adapter = faqsAdapter

        faqsAdapter!!.setOnClickListener(object : FaqsCateAdapter.ClickListener {
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

    }


    override fun onClick(v: View) {
        when (v.id) {


        }

    }


    private fun getFaqCategories() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getFaqCategories(

            ),
            this,
            ApiConstants.REQUEST_CODE.FAQS_CATEGORY
        )
    }

    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {

            ApiConstants.REQUEST_CODE.FAQS_CATEGORY -> {
                val apiResponse = RetrofitApiCall.getPayload(FaqCategoryResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    faqsCateList.addAll(apiResponse.output.dataField)
                    faqsAdapter!!.notifyDataSetChanged()
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