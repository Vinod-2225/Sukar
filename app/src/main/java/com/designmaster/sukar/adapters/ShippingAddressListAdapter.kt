package com.designmaster.sukar.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.models.AddressInfo
import com.designmaster.sukar.util.AppPrefs
import java.util.ArrayList

class ShippingAddressListAdapter(mActivity: Activity?, mList: ArrayList<AddressInfo>) : RecyclerView.Adapter<ShippingAddressListAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<AddressInfo> = mList
    lateinit var clickListener: ClickListener

    var row: Int = -1

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
        fun OnSelected(position: Int, address: String)
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var lblAddressType: TextView = itemView.findViewById<View>(R.id.lblAddressType) as TextView
        var lblAddress: TextView = itemView.findViewById<View>(R.id.lblAddress) as TextView
        var selectImg: ImageView = itemView.findViewById<View>(R.id.selectImg) as ImageView

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
//        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.bag_item_layout, parent, false)

        val v: View?

        if (AppPrefs.isLocaleEnglish(mContext!!)) {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.shipping_address_item, parent, false)
        } else {
            v = LayoutInflater.from(parent.context)
                .inflate(R.layout.shipping_address_item, parent, false)
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


//        holder1.lblAddressType.text = mList[position].name

        if(row == position){
            holder1.selectImg.setBackgroundResource(R.drawable.bg_2)
        }else{
            holder1.selectImg.setBackgroundResource(R.drawable.bg_1)
        }

        (mList[position].governatesname + " ,"+
                mList[position].area + " , " +
                "Block:" + mList[position].block + " , " +
                "Street: " + mList[position].street + ", " +
                "Floor: " + mList[position].floorNo + " , " +
                "Mobile No." + mList[position].mobileNo).also { holder1.lblAddress.text = it }


        holder1.selectImg.setOnClickListener {

            clickListener.OnSelected(position,holder1.lblAddress.text.toString())

            row = position

            notifyDataSetChanged()

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mList.size
//        return 2
    }
}