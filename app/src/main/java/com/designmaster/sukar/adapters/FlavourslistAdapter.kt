package com.designmaster.sukar.adapters


import CategoryData
import CouponsData
import FlavoursData
import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.fragments.ChocolatebrownwichFragment
import com.designmaster.sukar.fragments.HomeFragmentcnt
import com.designmaster.sukar.util.AppPrefs
import com.designmaster.sukar.util.MyApplication
import java.util.*


class FlavourslistAdapter(mActivity: Activity?, mList: ArrayList<FlavoursData>) :
    RecyclerView.Adapter<FlavourslistAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<FlavoursData> = mList
    private val arraylist: ArrayList<FlavoursData> = ArrayList()
    lateinit var clickListener: ClickListener

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var title: TextView =
            itemView.findViewById<View>(R.id.title) as TextView
        var rel: RelativeLayout =
            itemView.findViewById<View>(R.id.rl1) as RelativeLayout

        var charge: TextView =
            itemView.findViewById<View>(R.id.charge) as TextView




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
            LayoutInflater.from(parent.context).inflate(R.layout.flavourslistitem, parent, false)


        return ViewHolder(v)
    }

    var row_index = -1

    override fun onBindViewHolder(holder1: ViewHolder, position: Int) {

        if (AppPrefs.isLocaleEnglish(mContext)){
            holder1.title.text = mList[position].title_en
            if (mList[position].extra_charge.toString().isNotEmpty()){
                holder1.charge.text = mList[position].extra_charge.toString()
            }


        }else{
            holder1.title.text = mList[position].title_ar
            if (mList[position].extra_charge.toString().isNotEmpty()){
                holder1.charge.text = mList[position].extra_charge.toString()
            }

        }
holder1.rel.setOnClickListener{
//    val nextFrag = ChocolatebrownwichFragment()
//    (mContext as HomeActivity?)?.supportFragmentManager?.beginTransaction()
//        ?.replace(R.id.fragment_container, nextFrag, "findThisFragment")
//        ?.addToBackStack(null)
//        ?.commit()

    val nextFrag = ChocolatebrownwichFragment()
    (mContext as MainActivity?)?.supportFragmentManager?.beginTransaction()
        ?.replace(R.id.fragment_container, nextFrag, "findThisFragment")
        ?.addToBackStack(null)
        ?.commit()
}

    }


    override fun getItemCount(): Int {
        return mList.size

    }


    init {
        arraylist.addAll(mList)
    }
}