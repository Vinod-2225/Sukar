package com.designmaster.sukar.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.designmaster.sukar.R
import com.designmaster.sukar.models.CartInfo
import com.designmaster.sukar.util.AppPrefs
import java.util.*

class CartAdapter(mActivity: Activity?, mList: ArrayList<CartInfo>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<CartInfo> = mList
    lateinit var clickListener: ClickListener

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
        fun onQuantityClick(pos: Int, qty: String)
        //        fun onImageClick(pos: Int)
        fun onRemoveClick(pos: Int)
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var productImg: ImageView = itemView.findViewById<View>(R.id.productImg) as ImageView
        var lblProductName: TextView = itemView.findViewById<View>(R.id.lblProductName) as TextView
        var lblShopName: TextView = itemView.findViewById<View>(R.id.lblShopName) as TextView
        var lblPrice: TextView = itemView.findViewById<View>(R.id.lblPrice) as TextView
        var lblQuantity: TextView = itemView.findViewById<View>(R.id.lblQuantity) as TextView

        var minusBtn: Button = itemView.findViewById<View>(R.id.minusBtn) as Button
        var plusBtn: Button = itemView.findViewById<View>(R.id.plusBtn) as Button
        var btnRemove: Button = itemView.findViewById<View>(R.id.btnRemove) as Button
        var btnWhislist: Button = itemView.findViewById<View>(R.id.btnWhislist) as Button

        var lblSize: TextView = itemView.findViewById<View>(R.id.lblSize) as TextView

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
            v = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
        }else{
            v = LayoutInflater.from(parent.context).inflate(R.layout.cart_item_layout, parent, false)
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

        mContext?.let {
            Glide.with(it)
                    .load(mList[position].image)
                    .transform(CenterCrop(), RoundedCorners(5))
                    .into(holder1.productImg)
        }
        holder1.lblProductName.text = mList[position].title

//        val f1: Float = mList[position].totalAmount!!.toFloat()
//        val f2: Float = unit_price.toFloat()
//        val f3: Float = f1 * f2
//        val total_price: String = String.format("%.3f", f1)

//        holder1.lblPrice.text = total_price+ " "+mList[position].currencyUnit

        holder1.lblPrice.text = mList[position].price
        holder1.lblQuantity.text = mList[position].quantity.toString()


        if(!mList[position].size.isNullOrEmpty()){
            if (AppPrefs.isLocaleEnglish(mContext)) {
                holder1.lblSize.text = "Size : "+mList[position].size
            }else{
                holder1.lblSize.text = "بحجم : "+mList[position].size
            }

        }else{
            holder1.lblSize.text = ""
        }


        holder1.minusBtn.setOnClickListener {

//            val product_quantity_count = mList[position].getQuantity()!!.toInt() - 1
//            mList[position].setQuantity(product_quantity_count.toString())

                if (holder1.lblQuantity.text.toString().toInt() > 1) {
                    val product_quantity_count = holder1.lblQuantity!!.text.toString().toInt() - 1
                    holder1.lblQuantity.text = "" + product_quantity_count
                    clickListener.onQuantityClick(position,"decrease")
                }


        }

        holder1.plusBtn.setOnClickListener {
//            if (holder1.lblQuantity!!.text.toString().toInt() > 0) {

//            if(mList[position].inStock==true){
                val product_quantity_count = holder1.lblQuantity.text.toString().toInt() + 1
                holder1.lblQuantity.text = "" + product_quantity_count
                clickListener.onQuantityClick(position,"increase")
//            }



        }


        holder1.btnRemove.setOnClickListener {
            clickListener.onRemoveClick(position)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mList.size
//        return 2
    }




}