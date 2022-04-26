package com.designmaster.sukar.activities

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.adapters.CommentsListAdapter
import com.designmaster.sukar.models.CommentListInfo
import com.designmaster.sukar.models.CommentListResponse
import com.designmaster.sukar.models.FeedCommentResponse
import com.designmaster.sukar.util.*

class CommentsListActivity : BaseActivity(), ApiCallListener, View.OnClickListener {

    var commentsRecyclerView: RecyclerView? = null
    var commentsEdit: EditText? = null
    private var btnPost: Button? = null
    var btnBack: Button? = null

    private var commentListInfo = ArrayList<CommentListInfo>()

    var commentsListAdapter: CommentsListAdapter? = null

    var feedId: String = ""
    var userId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comments_list_activity)

        val bundle = intent.extras
        if (bundle != null) {
            feedId = bundle.getString("FeedId").toString()
            userId = bundle.getString("UserId").toString()
        };
//        selected_Date = bundle.getString("SelectedDate");
        idMappings()
        setOnClickListeners()
        getCommentsList()

    }

    private fun idMappings() {
        
        commentsEdit = findViewById(R.id.commentsEdit)
        btnPost = findViewById(R.id.btnPost)
        btnBack = findViewById(R.id.btnBack)

        commentsRecyclerView = findViewById<View>(R.id.commentsRecyclerView) as RecyclerView
        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 1)
        commentsRecyclerView!!.layoutManager = mLayoutManager
        commentsRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(5), true))
        commentsRecyclerView!!.itemAnimator = DefaultItemAnimator()


        commentsListAdapter = CommentsListAdapter(this, commentListInfo)
        commentsRecyclerView!!.adapter = commentsListAdapter

        commentsListAdapter!!.setOnClickListener(object : CommentsListAdapter.ClickListener {
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

        btnPost!!.setOnClickListener(this)
        btnBack!!.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnBack -> {
                finish()
            }

            R.id.btnPost -> {
                if(commentsEdit!!.text.toString() == ""){
                    errorDialog(this@CommentsListActivity,"Please enter your comments")
                }else{
                    feedComment()
                }


            }


        }
    }


    private fun getCommentsList() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.commentsList(
                feedId

            ),
            this,
            ApiConstants.REQUEST_CODE.COMMENTS_LIST
        )
    }


    private fun feedComment() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.feedComment(
                AppPrefs.getUserId(this@CommentsListActivity).toString(),
                feedId,
                commentsEdit!!.text.toString()

            ),
            this,
            ApiConstants.REQUEST_CODE.FEED_COMMENT
        )
    }


    override fun onSuccess(response: String, requestCode: Int) {

        when (requestCode) {


            ApiConstants.REQUEST_CODE.COMMENTS_LIST -> {
                val apiResponse = RetrofitApiCall.getPayload(CommentListResponse::class.java, response)
                if (apiResponse.output.success == 1) {

                    commentListInfo.clear()

                    apiResponse.output?.info.let {
                        if (it != null) {
                            commentListInfo.addAll(it)
                        }
                    }

                    commentsListAdapter!!.notifyDataSetChanged()
                }
            }

            ApiConstants.REQUEST_CODE.FEED_COMMENT -> {
                val apiResponse = RetrofitApiCall.getPayload(FeedCommentResponse::class.java, response)
                if (apiResponse.output.success == 1) {
                    commentsEdit!!.setText("")
                    getCommentsList()
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


    private fun errorDialog(context: Context, msg: String?) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (AppPrefs.isLocaleEnglish(this@CommentsListActivity)) {
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

        if (AppPrefs.isLocaleEnglish(this@CommentsListActivity)) {
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