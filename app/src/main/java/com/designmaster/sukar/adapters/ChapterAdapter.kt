package com.designmaster.sukar.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.fragments.HomeFragment.Companion.comingfromhome
import com.designmaster.sukar.fragments.HomeFragmentcnt
import com.designmaster.sukar.fragments.HomeFragmentcnt.Companion.llpro
import com.designmaster.sukar.fragments.HomeFragmentcnt.Companion.productsRecyclerView

import com.designmaster.sukar.fragments.HomeFragmentcnt.Companion.rel1
import com.designmaster.sukar.fragments.HomeFragmentcnt.Companion.rel11
import com.designmaster.sukar.fragments.HomeFragmentcnt.Companion.rel2
import com.designmaster.sukar.fragments.HomeFragmentcnt.Companion.shopsRecyclerView
import com.designmaster.sukar.fragments.WhishlistFragment.Companion.comingfromwishcopy
import com.designmaster.sukar.fragments.WhishlistFragment.Companion.relativerl
import com.designmaster.sukar.fragments.WhishlistFragment.Companion.relativerl1


class ChapterAdapter(
    private val context: FragmentActivity,
    private val chaptersList: ArrayList<String>
) :
    RecyclerView.Adapter<ChapterAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.influencerrestaurenttabs, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return chaptersList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            holder.lblProductName.setTextColor(Color.parseColor("#000000"));
            // getProducts()
        }


        holder.lblProductName.text = chaptersList.get(position)
        holder.itemView.setOnClickListener {
            //Toast.makeText(context, chaptersList.get(position), Toast.LENGTH_LONG).show()
            if (position == 0) {
                if (comingfromhome==true){
                    rel1.visibility = View.VISIBLE
                    rel11.visibility = View.VISIBLE
                    rel2.visibility = View.GONE
                    llpro.visibility = View.GONE
                   // comingfromhome=false
                }else if (comingfromwishcopy==true){
                    relativerl.visibility = View.VISIBLE
                    relativerl1.visibility = View.GONE

                  //  comingfromwishcopy=false
                }
               // holder.lblProductName.setBackgroundResource(R.drawable.btn_bg_1);
                holder.lblProductName.setTextColor(Color.parseColor("#000000"));
                holder.line.visibility = View.VISIBLE

                shopsRecyclerView.visibility=View.VISIBLE
                productsRecyclerView.visibility=View.GONE

                //  getProducts()
            } else {
//                if (comingfromhome==true){
//                    rel1.visibility = View.GONE
//                    rel11.visibility = View.GONE
//                    llpro.visibility = View.VISIBLE
//
//                   // comingfromhome=false
//                }else if (comingfromwishcopy==true){
//                    relativerl.visibility = View.GONE
//                    relativerl1.visibility = View.VISIBLE
//
//                    //comingfromwishcopy=false
//                }
               // holder.lblProductName.setBackgroundResource(R.drawable.btn_bg_1);
                holder.lblProductName.setTextColor(Color.parseColor("#000000"));
                holder.line.visibility = View.VISIBLE

                shopsRecyclerView.visibility=View.GONE
                productsRecyclerView.visibility=View.VISIBLE

                //getRestaurants()

            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        /* val chapterName = view.tvChapterName*/
        var lblProductName: TextView =
            itemView.findViewById<View>(R.id.catagwisestoreinfo1) as TextView
        var line: TextView =
            itemView.findViewById<View>(R.id.line) as TextView


    }


}