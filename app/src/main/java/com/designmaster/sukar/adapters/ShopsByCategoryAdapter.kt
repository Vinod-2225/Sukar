package com.designmaster.sukar.adapters

import android.app.Activity
import android.content.Context
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
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.fragments.HomeFragmentcnt
import com.designmaster.sukar.models.ShopsData
import com.designmaster.sukar.util.AppPrefs
import java.util.ArrayList

class ShopsByCategoryAdapter (mActivity: Activity?, mList: ArrayList<ShopsData>) :
    RecyclerView.Adapter<ShopsByCategoryAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<ShopsData> = mList
    lateinit var clickListener: ClickListener

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var shopImg: ImageView =
            itemView.findViewById<View>(R.id.shopImg) as ImageView

        var lblShopName: TextView =
            itemView.findViewById<View>(R.id.lblShopName) as TextView

        /* var designertext: TextView =
             itemView.findViewById<View>(R.id.designertxt) as TextView*/


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
            LayoutInflater.from(parent.context).inflate(R.layout.shops_item_layout, parent, false)


        return ViewHolder(v)
    }

    var row_index = -1


    override fun onBindViewHolder(holder1: ViewHolder, position: Int) {
        /*Picasso.with(mContext).load(mList[position].image)
            .into(
                holder1.catagimgs
            );*/
        //holder1.catagimgs.setImageBitmap(BitmapFactory.decodeFile(mList.get(position).image))

        mContext?.let {
            Glide.with(it)
                .load(mList[position].image)
                .transform(CenterCrop(), RoundedCorners(5))
                .into(holder1.shopImg)
        }
        if (AppPrefs.isLocaleEnglish(mContext)){
            holder1.lblShopName.text = mList[position].titleEn
            //holder1.designertext.text = mList[position].designer_prod_recommedation
        }else{
            holder1.lblShopName.text = mList[position].titleAr
            // holder1.designertext.text = mList[position].designer_prod_recommedation_ar
        }
//        holder1.catagimgs.setOnClickListener{
////    val nextFrag = HomeFragmentcnt()
////    (mContext as HomeActivity?)?.supportFragmentManager?.beginTransaction()
////        ?.replace(R.id.fragment_container, nextFrag, "findThisFragment")
////        ?.addToBackStack(null)
////        ?.commit()
//
//            val fragment = HomeFragmentcnt()
//            (mContext as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                R.id.fragment_container,
//                fragment
//            ).addToBackStack(null).commit()
//        }

    }


    override fun getItemCount(): Int {
        return mList.size

    }


}