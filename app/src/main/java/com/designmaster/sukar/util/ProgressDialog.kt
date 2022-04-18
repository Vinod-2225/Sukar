package com.rawcode.joesfam.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.designmaster.sukar.R


class ProgressDialog : DialogFragment() {

    var rootView: View? = null
    var gifView: ImageView? = null

    companion object {
        fun getInstance(): ProgressDialog {
            val args = Bundle()
            val fragment = ProgressDialog()
            fragment.arguments = args
            return fragment
        }
    }



    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager?.beginTransaction()
            ft?.add(this, tag)
            ft?.commitAllowingStateLoss()
        } catch (ignored: IllegalStateException) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (rootView == null) {
            rootView = inflater.inflate(R.layout.layout_progress_dialog, container, false)

            gifView =  rootView!!.findViewById(R.id.gifView)
            Glide.with(activity!!)
                .asGif()
                .load(R.drawable.loader_new)
                .into(gifView!!);
        }

        return rootView

    }

    override fun onStart() {
        super.onStart()
        var dialog = dialog;
        if (dialog != null) {
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.setCancelable(false)
            //dialog.window?.setWindowAnimations(R.style.DialogAnimation);
        }
    }
}