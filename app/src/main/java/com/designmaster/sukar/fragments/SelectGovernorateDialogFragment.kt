package com.designmaster.sukar.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.designmaster.sukar.R
import com.designmaster.sukar.adapters.SelectGovernorateAdapter
import com.designmaster.sukar.databinding.SelectItemDialogBinding
import com.designmaster.sukar.models.governorates.GovernorateInfo
import com.designmaster.sukar.util.AppConstants
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectGovernorateDialogFragment: BottomSheetDialogFragment() {

    companion object {
        fun getInstance(zoneData: ArrayList<GovernorateInfo>): SelectGovernorateDialogFragment {
            val args = Bundle()
            args.putParcelableArrayList(AppConstants.INTENT_KEY.DATA, zoneData)
            val fragment = SelectGovernorateDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var mContext: Context? = null
    private lateinit var clickListener: ClickListener
    private lateinit var binding: SelectItemDialogBinding
    private lateinit var governorateListAdapter: SelectGovernorateAdapter
    private var itemData = ArrayList<GovernorateInfo>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        if (arguments != null) {
            itemData.addAll(requireArguments().getParcelableArrayList(AppConstants.INTENT_KEY.DATA)!!)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.select_item_dialog, container, false)
        setReasonList()
        return binding.root
    }

    private fun setReasonList() {
        governorateListAdapter = SelectGovernorateAdapter(mContext, itemData) {
            clickListener.onItemClick(it)
            dismiss()
        }
        binding.rvCountry!!.adapter = governorateListAdapter
    }

    fun setOnClick(clickListener: SelectGovernorateDialogFragment.ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(pos: Int)
    }
}