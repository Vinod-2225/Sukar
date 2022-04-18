package com.designmaster.sukar.util

import android.content.Context
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.designmaster.sukar.BuildConfig
import com.rawcode.joesfam.util.ProgressDialog
import java.io.*
import java.util.*
import java.util.regex.Pattern

open class BaseFragment : Fragment() {

    private var pd: ProgressDialog? = null

    val VIDEO_SUFFIX = ".mp4"
    val IMAGE_SUFFIX = ".jpg"
    val FOLDER_NAME = "/Sukar/"

    val isFragmentAdded: Boolean
        get() = activity != null && !requireActivity().isFinishing && isAdded

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun showToast(message: String) {
        try {
            if (activity != null && isFragmentAdded) {
                Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
        }
    }
    protected fun isValidPassword(pass: String?): Boolean {
        return if (pass != null && pass.length > 0) {
            true
        } else false
    }
    protected fun isValidPasswordlegnthmin(pass: String?): Boolean {
        return if (pass!!.isNotEmpty() && pass.length > 5) {
            true
        } else false
    }
    /* protected fun isValidPasswordlegnthmax(pass: String?): Boolean {
         return if (pass!!.isNotEmpty() && pass.length < 14) {
             true
         } else false
     }*/
    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidMobile1(phone: String): Boolean {
        var check = false
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
            if (phone.length < 8 || phone.length > 8) {
                if(phone.length!= 8) {

                    check = false
                }
                //txtPhone.setError("Not Valid Number")
            } else {
                check = true
            }
        } else {
            check = false
        }
        return check
    }

    protected fun showProgress() {
        hideKeyBoard()
        hideProgress()
        pd = ProgressDialog.getInstance()
        pd?.show(childFragmentManager, "showProgressDialog")
    }

    protected fun hideProgress() {
        if (pd != null) {
            pd?.dismiss()
        }
    }

    protected fun hideKeyBoard() {
        try {
            val inputManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                activity?.currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        } catch (e: Exception) {
        }
    }

/*
    protected fun showErrorDialog(msg: String) {
        var dialog = OneButtonDialog(activity, "", msg, getString(R.string.ok), "")
        dialog.setDialogClickListener(object : OneButtonDialog.DialogClickListener {
            override fun onPositiveClick() {

            }
        })
        dialog.showDialog()
    }*/


//    fun showMessageDialog(msg: String) {
//        var dialog = OneButtonDialog(activity, msg, getString(R.string.ok))
//        dialog.setDialogClickListener(object : OneButtonDialog.DialogClickListener {
//            override fun onPositiveClick() {
//            }
//        })
//        dialog.showDialog()
//    }


    fun getURI(context: Context?, file: File?): Uri {
        return FileProvider.getUriForFile(
            requireContext(),
            BuildConfig.APPLICATION_ID + ".provider",
            file!!
        )
    }

    fun createNewFile(prefix: String?, extension: String): File {
        var prefix = prefix
        if (prefix == null || prefix.isEmpty()) {
            prefix = "PIC_"
        }
        val newDirectory =
            File(Environment.getExternalStorageDirectory().toString() + FOLDER_NAME)
        if (!newDirectory.exists()) {
            if (newDirectory.mkdir()) {
                Log.d("Util", newDirectory.absolutePath + " directory created")
            }
        }
        return File(
            newDirectory,
            prefix + "" + Calendar.getInstance().timeInMillis + extension
        )
    }

    fun createNewFile(prefix: String?): File {
        var prefix = prefix
        if (prefix == null || prefix.isEmpty()) {
            prefix = "PIC_"
        }
        val newDirectory =
            File(Environment.getExternalStorageDirectory().toString() + FOLDER_NAME)
        if (!newDirectory.exists()) {
            if (newDirectory.mkdir()) {
                Log.d("Util", newDirectory.absolutePath + " directory created")
            }
        }
        return File(
            newDirectory,
            prefix + "" + Calendar.getInstance().timeInMillis + IMAGE_SUFFIX
        )
    }

    fun getPath(context: Context, uri: Uri?): File? {
        val projection =
            arrayOf(MediaStore.Images.Media.DATA)
        val cursor =
            context.contentResolver.query(uri!!, projection, null, null, null)
                ?: return null
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val path = cursor.getString(column_index)
        cursor.close()
        return if (path == null) {
            null
        } else {
            copy(
                File(path),
                createNewFile("CROPPED_")
            )
        }
    }


    fun copy(src: File?, dst: File?): File? {
        var `in`: InputStream? = null
        try {
            `in` = FileInputStream(src)
            val out: OutputStream = FileOutputStream(dst)
            // Transfer bytes from in to out
            val buf = ByteArray(1024)
            var len: Int
            while (`in`.read(buf).also { len = it } > 0) {
                out.write(buf, 0, len)
            }
            `in`.close()
            out.close()
            return dst
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    inner class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }

    }

    /**
     * Converting dp to pixel
     */
    open fun dpToPx(dp: Int): Int {
        val r = requireActivity().resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }
}
