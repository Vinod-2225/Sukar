package com.designmaster.sukar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.R
import com.designmaster.sukar.databinding.SelectItemViewBinding
import com.designmaster.sukar.models.areas.AreaInfo
import com.designmaster.sukar.models.governorates.GovernorateInfo
import com.designmaster.sukar.util.AppPrefs

class SelectAreaAdapter(
    val context: Context?,
    val itemData: ArrayList<AreaInfo>,
    val clickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<SelectAreaAdapter.Item>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Item {

        var view: SelectItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.select_item_view,
            p0,
            false
        )

        return Item(view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        if (AppPrefs.isLocaleEnglish(context)) {
            holder.binding.tvTitle.text = itemData[position].titleEn
            holder.binding.tvTitle.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        }else{
            holder.binding.tvTitle.text = itemData[position].titleAr
            holder.binding.tvTitle.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        }




        holder.binding.tvTitle.setOnClickListener {
            clickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return itemData.size
    }

    class Item(itemView: SelectItemViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        var binding = itemView
    }
}