package com.designmaster.sukar.fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.designmaster.sukar.R
import com.designmaster.sukar.util.AppPrefs

class Hi private fun alertDialog(context: Context, msg: String?) {
    val dialog = Dialog(context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    if (AppPrefs.isLocaleEnglish(activity)) {
        dialog.setContentView(R.layout.alert_dialog_box)
    }else{
        dialog.setContentView(R.layout.alert_dialog_box_ar)
    }
    val layoutParams = dialog.window!!.attributes
    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
    layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
    dialog.window!!.attributes = layoutParams
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(true)
    val alertMessage = dialog.findViewById<View>(R.id.alertMessage) as TextView
    alertMessage.text = msg
    val okText = dialog.findViewById<View>(R.id.okText) as TextView

    if (AppPrefs.isLocaleEnglish(activity)) {
        okText.text = context.getString(R.string.ok)
    } else {
        okText.text = context.getString(R.string.ok_ar)
    }
    okText.setOnClickListener {
        dialog.dismiss()
        requireActivity().supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    dialog.show()
}