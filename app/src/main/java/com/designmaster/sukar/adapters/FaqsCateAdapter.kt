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
import com.designmaster.sukar.models.ProductsData
import com.designmaster.sukar.util.AppPrefs
import java.util.*

class FaqsCateAdapter(mActivity: Activity?, mList: ArrayList<FaqCategoryDataField>) : RecyclerView.Adapter<FaqsCateAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<FaqCategoryDataField> = mList
    private val arraylist: ArrayList<FaqCategoryDataField> = ArrayList()
    lateinit var clickListener: ClickListener

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var cateImgLayout: LinearLayout =
            itemView.findViewById<View>(R.id.cateImgLayout) as LinearLayout
        var cateImg: ImageView = itemView.findViewById<View>(R.id.cateImg) as ImageView
        var lblCateName: TextView = itemView.findViewById<View>(R.id.lblCateName) as TextView

        var itemLayout: RelativeLayout = itemView.findViewById(R.id.itemLayout) as RelativeLayout

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
                .inflate(R.layout.faqs_categories_item, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.faqs_categories_item, parent, false)
        }

        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder1: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element holder1.imgLike.setTag(position);
//        val radius = 15
//        val margin = 5
//        val transformation: Transformation = RoundedCornersTransformation(radius, margin)


        // - get element from your dataset at this position
        // - replace the contents of the view with that element holder1.imgLike.setTag(position);
        holder1.cateImgLayout.layoutParams.width = (AppPrefs.get_device_width(mContext!!) / 2) - 50
        holder1.cateImgLayout.layoutParams.height = (AppPrefs.get_device_width(mContext!!) / 3)

//        holder1.cateImgLayout.layoutParams.width = (AppPrefs.get_device_width(mContext!!) / 2) - 50
//        holder1.cateImgLayout.layoutParams.height = (AppPrefs.get_device_width(mContext!!) / 2)

        holder1.cateImg.layoutParams.width = (AppPrefs.get_device_width(mContext!!) / 3) - 100
        holder1.cateImg.layoutParams.height = (AppPrefs.get_device_width(mContext!!) / 3) - 100



        mContext?.let {
            Glide.with(it)
                .load(mList[position].image)
                .transform(CenterCrop())
                .into(holder1.cateImg)
        }


        holder1.lblCateName.text = mList[position].titleEn


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mList.size
//        return 2
    }

    // Filter Class
    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        mList.clear()
        if (charText.isEmpty()) {
            mList.addAll(arraylist)
        } else {
            for (wp in arraylist) {
                if (wp.titleEn?.toLowerCase(Locale.getDefault())?.contains(charText)!!) {
                    mList.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    init {
        arraylist.addAll(mList)
    }

}