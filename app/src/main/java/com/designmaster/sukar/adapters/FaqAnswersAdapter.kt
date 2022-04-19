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
import com.designmaster.sukar.R
import com.designmaster.sukar.models.FaqAnswerInfo

import java.util.*

class FaqAnswersAdapter(mActivity: Activity?, mList: ArrayList<FaqAnswerInfo>) : RecyclerView.Adapter<FaqAnswersAdapter.ViewHolder>() {
    private val mDataset: ArrayList<String>? = null
    var mContext: Context? = mActivity
    var mList: ArrayList<FaqAnswerInfo> = mList
    private val arraylist: ArrayList<FaqAnswerInfo> = ArrayList()
    lateinit var clickListener: ClickListener

    var row: Int = -1

    fun setOnClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun OnItemClick(position: Int, v: View?)
        fun onButtonClick(pos: Int)
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var lblName: TextView = itemView.findViewById<View>(R.id.lblName) as TextView
        var lblDesc: TextView = itemView.findViewById<View>(R.id.lblDesc) as TextView

        var plusBtn: ImageView = itemView.findViewById(R.id.plusBtn)

        var pnlDescription: LinearLayout = itemView.findViewById<View>(R.id.pnlDescription) as LinearLayout
        var layout1: RelativeLayout = itemView.findViewById<View>(R.id.layout1) as RelativeLayout

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
//        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.brands_item_layout, parent, false)

        var v: View? = null
        v = LayoutInflater.from(parent.context).inflate(R.layout.faqs_answer_item_layout, parent, false)


        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder1: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element holder1.imgLike.setTag(position);


        holder1.lblName.text = mList[position].questionEn
        holder1.lblDesc.text = mList[position].answerEn


//        holder1.pnlDescription.visibility = View.GONE


        if(row==position){
            if (holder1.pnlDescription.visibility == View.VISIBLE) {
                holder1.pnlDescription.visibility = View.GONE
                holder1.plusBtn.animate().rotation(0f)
                holder1.plusBtn.setBackgroundResource(R.drawable.add_icon)
//                notifyDataSetChanged()
            } else {
                holder1.plusBtn.animate().rotation(180f)
                holder1.pnlDescription.visibility = View.VISIBLE
                holder1.plusBtn.setBackgroundResource(R.drawable.add_icon)
//                notifyDataSetChanged()
            }
        }else{
//            if (holder1.pnlDescription.visibility == View.VISIBLE) {
//                holder1.pnlDescription.visibility = View.GONE
//                holder1.plusBtn.animate().rotation(0f)
//                holder1.plusBtn.setBackgroundResource(R.drawable.plus_icon)
////                notifyDataSetChanged()
//            } else {
                holder1.plusBtn.animate().rotation(0f)
                holder1.pnlDescription.visibility = View.GONE
                holder1.plusBtn.setBackgroundResource(R.drawable.add_icon)
//                notifyDataSetChanged()
//            }
        }


        holder1.layout1.setOnClickListener {
//            if (holder1.pnlDescription.visibility == View.VISIBLE) {
//                holder1.pnlDescription.visibility = View.GONE
//                holder1.plusBtn.animate().rotation(0f)
//                holder1.plusBtn.setBackgroundResource(R.drawable.plus_icon)
////                notifyDataSetChanged()
//            } else {
//                holder1.plusBtn.animate().rotation(180f)
//                holder1.pnlDescription.visibility = View.VISIBLE
//                holder1.plusBtn.setBackgroundResource(R.drawable.minus_icon)
////                notifyDataSetChanged()
//            }


//            row = position
//
//            notifyDataSetChanged()


            clickListener.onButtonClick(position)
        }


    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mList.size
//        return 2
    }





    fun getItemSelected(pos: Int) {
        this.row = pos
        notifyDataSetChanged()
    }
}