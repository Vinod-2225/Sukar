package com.designmaster.sukar.adapters


import CategoryData
import CouponsData
import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.fragments.HomeFragmentcnt
import com.designmaster.sukar.util.AppPrefs
import com.designmaster.sukar.util.MyApplication
import java.util.*


class CouponslistAdapter(mActivity: Activity?, mList: ArrayList<CouponsData>) :
    RecyclerView.Adapter<CouponslistAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<CouponsData> = mList
    private val arraylist: ArrayList<CouponsData> = ArrayList()
    lateinit var clickListener: ClickListener

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var skr: TextView =
            itemView.findViewById<View>(R.id.skr) as TextView

        var upto: TextView =
            itemView.findViewById<View>(R.id.upto) as TextView

         var until: TextView =
             itemView.findViewById<View>(R.id.until) as TextView


        init {

            itemView.setOnClickListener(this)

        }


        override fun onClick(v: View?) {


            clickListener.OnItemClick(adapterPosition, v)
        }


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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.coupons_listitems, parent, false)


        return ViewHolder(v)
    }

    var row_index = -1

    override fun onBindViewHolder(holder1: ViewHolder, position: Int) {

        if (AppPrefs.isLocaleEnglish(mContext!!)) {
            holder1.skr.text = mList[position].title
            holder1.upto.text = "Up to "+mList[position].couponamount+"KWD off on all purchases"
            holder1.until.text = mList[position].todate
        }else{
            holder1.skr.text = mList[position].title
           holder1.upto.text = "Up to "+mList[position].couponamount+"KWD off on all purchases"
            holder1.until.text = mList[position].todate
        }


    }


    override fun getItemCount(): Int {
        return mList.size

    }


    init {
        arraylist.addAll(mList)
    }
}