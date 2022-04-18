package com.designmaster.sukar.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.designmaster.sukar.R
import com.designmaster.sukar.models.ProductsData
import com.designmaster.sukar.models.WishlistProductsByCategoryInfo
import com.designmaster.sukar.util.AppPrefs
import java.util.*

class WishlistProductsByCategoryAdapter (mActivity: Activity?, mList: ArrayList<WishlistProductsByCategoryInfo>) : RecyclerView.Adapter<WishlistProductsByCategoryAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<WishlistProductsByCategoryInfo> = mList
    private val arraylist: ArrayList<WishlistProductsByCategoryInfo> = ArrayList()
    lateinit var clickListener: ClickListener

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
        fun OnFavoriteClick(position: Int)
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var productImgLayout: RelativeLayout =
            itemView.findViewById<View>(R.id.productImgLayout) as RelativeLayout
        var productImg: ImageView = itemView.findViewById<View>(R.id.productImg) as ImageView
        var lblProductName: TextView = itemView.findViewById<View>(R.id.lblProductName) as TextView
        var lblNewPrice: TextView = itemView.findViewById<View>(R.id.lblNewPrice) as TextView

        var favoriteImg: ImageView = itemView.findViewById<View>(R.id.favoriteImg) as ImageView

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
                .inflate(R.layout.products_item_layout, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.products_item_layout, parent, false)
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
        holder1.productImgLayout.layoutParams.width =
            (AppPrefs.get_device_width(mContext!!) / 2) - 50
        holder1.productImgLayout.layoutParams.height =
            (AppPrefs.get_device_width(mContext!!) / 2) - 50

        holder1.productImg.layoutParams.width = (AppPrefs.get_device_width(mContext!!) / 2) - 50
        holder1.productImg.layoutParams.height = (AppPrefs.get_device_width(mContext!!) / 2) - 50



        mContext?.let {
            Glide.with(it)
                .load(mList[position].image)
                .transform(CenterCrop())
                .into(holder1.productImg)
        }


        holder1.lblProductName.text = mList[position].tilteEn


        holder1.lblNewPrice.text = mList[position].price + ""

        holder1.favoriteImg.setOnClickListener {

            clickListener.OnFavoriteClick(position)

        }


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
                if (wp.tilteEn?.toLowerCase(Locale.getDefault())?.contains(charText)!!) {
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