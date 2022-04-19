package com.designmaster.sukar.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.designmaster.sukar.R
import com.designmaster.sukar.models.FaqCategoryDataField
import com.designmaster.sukar.models.FeedInfo
import com.designmaster.sukar.util.AppPrefs
import java.util.*

class FeedAdapter(mActivity: Activity?, mList: ArrayList<FeedInfo>) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<FeedInfo> = mList
    private val arraylist: ArrayList<FeedInfo> = ArrayList()
    lateinit var clickListener: ClickListener

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
        fun OnCommentsClick(position: Int)
        fun OnLikesClick(position: Int)
        fun OnShareClick(position: Int)
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {


//        var lblPostsCount: TextView = itemView.findViewById<View>(R.id.lblPostsCount) as TextView
//        var lblPhotosCount: TextView = itemView.findViewById<View>(R.id.lblPhotosCount) as TextView
//        var lblActivityCount: TextView = itemView.findViewById<View>(R.id.lblActivityCount) as TextView
        var lblName: TextView = itemView.findViewById<View>(R.id.lblName) as TextView
        var lblDate: TextView = itemView.findViewById<View>(R.id.lblDate) as TextView
        var lblMsg: TextView = itemView.findViewById<View>(R.id.lblMsg) as TextView
        var lblCommentsCount: TextView = itemView.findViewById<View>(R.id.lblCommentsCount) as TextView
        var lblLikesCount: TextView = itemView.findViewById<View>(R.id.lblLikesCount) as TextView
        var lblSharesCount: TextView = itemView.findViewById<View>(R.id.lblSharesCount) as TextView

        var img1: ImageView = itemView.findViewById<View>(R.id.img1) as ImageView
        var img2: ImageView = itemView.findViewById<View>(R.id.img2) as ImageView


        var commentsLayout: LinearLayout = itemView.findViewById(R.id.commentsLayout)
        var likesLayout: LinearLayout = itemView.findViewById(R.id.likesLayout)
        var shareLayout: LinearLayout = itemView.findViewById(R.id.shareLayout)


        init {

            itemView.setOnClickListener(this)

        }


        override fun onClick(v: View?) {
//            TODO("Not yet implemented")

            clickListener.OnItemClick(adapterPosition, v)
        }

//        itemView.setOnClickListener(this)
    }

    fun add(position: Int, item: String) {
        mDataset!!.add(position, item)
        notifyItemInserted(position)
    }

    fun remove(item: String?) {
        val position = mDataset!!.indexOf(item)
        mDataset.removeAt(position)
        notifyItemRemoved(position)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // create a new view
//        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.products_item_layout, parent, false)

        var v: View? = null
        if (AppPrefs.isLocaleEnglish(mContext!!)) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.feed_item_layout, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.feed_item_layout, parent, false)
        }

        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder1: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element holder1.imgLike.setTag(position);


        mContext?.let {
            Glide.with(it)
                .load(mList[position].profileImage)
                .placeholder(R.drawable.account)
                .transform(CenterCrop())
                .into(holder1.img1)
        }


        holder1.lblName.text = mList[position].name
        holder1.lblDate.text = mList[position].date
        holder1.lblMsg.text = mList[position].content
        holder1.lblCommentsCount.text = mList[position].comments
        holder1.lblLikesCount.text = mList[position].likes
        holder1.lblSharesCount.text = mList[position].shares


        mContext?.let {
            Glide.with(it)
                .load(mList[position].image)
                .transform(CenterCrop())
                .into(holder1.img2)
        }


        holder1.commentsLayout.setOnClickListener {
            clickListener.OnCommentsClick(position)
        }

        holder1.likesLayout.setOnClickListener {
            clickListener.OnLikesClick(position)
        }

        holder1.shareLayout.setOnClickListener {
            clickListener.OnShareClick(position)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mList.size
//        return 2
    }



}