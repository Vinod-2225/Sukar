package com.designmaster.sukar.fragments

import android.app.Dialog
import android.content.Context
import android.content.Intent
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
import com.designmaster.sukar.activities.CommentsListActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.FeedAdapter
import com.designmaster.sukar.models.*
import com.designmaster.sukar.util.*
import kotlinx.android.synthetic.main.fragment_account.*


class FeedFragment : BaseFragment(), ApiCallListener, View.OnClickListener {

    var rootView: View? = null

    var feedRecyclerView: RecyclerView? = null
    var lblPostsCount:TextView? = null
    var lblPhotosCount:TextView? = null
    var lblActivityCount:TextView? = null


    private var feedInfo = ArrayList<FeedInfo>()

    var feedAdapter: FeedAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.faq_s),false)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.feed),false)
        }

        // Inflate the layout for this fragment
        if (rootView == null) {

            rootView = inflater.inflate(R.layout.feed_fragment, container, false)


            idMappings()
            setOnClickListeners()
            getFeedCount()

//            myOrders()

        }
        return rootView
    }

    private fun idMappings() {



        lblPostsCount = rootView!!.findViewById(R.id.lblPostsCount)
        lblPhotosCount = rootView!!.findViewById(R.id.lblPhotosCount)
        lblActivityCount = rootView!!.findViewById(R.id.lblActivityCount)


        feedRecyclerView = rootView!!.findViewById<View>(R.id.feedRecyclerView) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        feedRecyclerView!!.layoutManager = mLayoutManager
        feedRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(5), true))
        feedRecyclerView!!.itemAnimator = DefaultItemAnimator()

        feedAdapter = FeedAdapter(activity, feedInfo)
        feedRecyclerView!!.adapter = feedAdapter

        feedAdapter!!.setOnClickListener(object : FeedAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {

//                val fragment1 = FaqAnswersFragment()
//                val bundle1 = Bundle()
//                bundle1.putString("FaqId", feedInfo.get(position).id)
//                fragment1.arguments = bundle1
//                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment1).addToBackStack(null).commit()
            }

            override fun OnCommentsClick(position: Int) {

                val mainIntent = Intent(requireActivity(), CommentsListActivity::class.java)
                val bundle1 = Bundle()
                bundle1.putString("FeedId", feedInfo[position].id)
                bundle1.putString("UserId", feedInfo[position].userId)
                mainIntent.putExtras(bundle1)
                startActivity(mainIntent)

            }

            override fun OnLikesClick(position: Int) {
                feedLike(feedInfo[position].userId,feedInfo[position].id)
            }

            override fun OnShareClick(position: Int) {
                feedShare(feedInfo[position].userId,feedInfo[position].id)
            }


        })

    }

    private fun setOnClickListeners() {

    }


    override fun onClick(v: View) {
        when (v.id) {


        }

    }

    private fun getFeedCount() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getFeedTotalCount(

            ),
            this,
            ApiConstants.REQUEST_CODE.FEED_COUNT
        )
    }


    private fun getFeed() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getFeed(

            ),
            this,
            ApiConstants.REQUEST_CODE.FEED_LIST
        )
    }

    private fun feedLike(userId: String,feedId: String) {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.feedLike(
                userId,
                feedId,
                "like"
            ),
            this,
            ApiConstants.REQUEST_CODE.FEED_LIKE
        )
    }

    private fun feedShare(userId: String,feedId: String) {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.feedShare(
                userId,
                feedId,
                "share"
            ),
            this,
            ApiConstants.REQUEST_CODE.FEED_SHARE
        )
    }

    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {

            ApiConstants.REQUEST_CODE.FEED_COUNT -> {
                val apiResponse = RetrofitApiCall.getPayload(FeedBackTotalCountResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    lblPostsCount!!.text = apiResponse.output.dataField[0].totalPosts
                    lblPhotosCount!!.text = apiResponse.output.dataField[0].totalPhotos
                    lblActivityCount!!.text = "0"


                }else if (apiResponse.output.success == 0) {

                }

                getFeed()
            }

            ApiConstants.REQUEST_CODE.FEED_LIST -> {
                val apiResponse = RetrofitApiCall.getPayload(FeedResponse::class.java, response)
                if (apiResponse.output.success == 1) {
                    feedInfo.clear()
                    feedInfo.addAll(apiResponse.output.info)

                    feedAdapter!!.notifyDataSetChanged()

                }else if (apiResponse.output.success == 0) {

                }
            }

            ApiConstants.REQUEST_CODE.FEED_LIKE -> {
                val apiResponse = RetrofitApiCall.getPayload(FeedLikeResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    getFeed()

                }else if (apiResponse.output.success == 0) {

                }
            }

            ApiConstants.REQUEST_CODE.FEED_SHARE -> {
                val apiResponse = RetrofitApiCall.getPayload(FeedShareResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    getFeed()

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

    override fun onResume() {
        super.onResume()
        // put your code here...

        getFeed()
    }


}