package com.designmaster.sukar.util

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.util.ArrayList

class AppUtils {
    companion object {
        fun changeWidth(view: View, oldWidth: Int, newWidth: Int) {
            val slideAnimator = ValueAnimator
                .ofInt(oldWidth, newWidth)
                .setDuration(500)
            slideAnimator.addUpdateListener { animation1: ValueAnimator ->
                val value = animation1.animatedValue as Int
                view.layoutParams.width = value
                view.requestLayout()
            }
            var animationSet = AnimatorSet();
            animationSet.setInterpolator(AccelerateDecelerateInterpolator());
            animationSet.play(slideAnimator);
            animationSet.start()
        }

        var proSortPosition : String = ""
//        var proSortBy: String = ""
//        var proSortByName: String = ""

        var proMinPrice: String = ""
        var proMaxPrice: String = ""

        var colorIdList = ArrayList<String>()
        var colorNameList = ArrayList<String>()

        var sizeIdList = ArrayList<String>()
        var sizeNameList = ArrayList<String>()


//        fun changeHeight(
//            view: View,
//            storeProductAdapter: CartWishListAdapter,
//            oldHeight: Int,
//            newHeight: Int
//        ) {
//            val slideAnimator = ValueAnimator
//                .ofInt(oldHeight, newHeight)
//                .setDuration(500)
//            slideAnimator.addUpdateListener { animation1: ValueAnimator ->
//                val value = animation1.animatedValue as Int
//                view.layoutParams.height = value
//                view.requestLayout()
//                storeProductAdapter.notifyDataSetChanged()
//            }
//            var animationSet = AnimatorSet();
//            animationSet.setInterpolator(AccelerateDecelerateInterpolator());
//            animationSet.play(slideAnimator);
//            animationSet.start()
//        }

        fun changeHeight(
            view: View,
            oldHeight: Int,
            newHeight: Int
        ) {
            val slideAnimator = ValueAnimator
                .ofInt(oldHeight, newHeight)
                .setDuration(500)
            slideAnimator.addUpdateListener { animation1: ValueAnimator ->
                val value = animation1.animatedValue as Int
                view.layoutParams.height = value
                view.requestLayout()
            }
            var animationSet = AnimatorSet();
            animationSet.setInterpolator(AccelerateDecelerateInterpolator());
            animationSet.play(slideAnimator);
            animationSet.start()
        }


        fun setUnderLine(textView: TextView) {
            //textView.paintFlags = textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }

        fun removeUnderLine(textView: TextView) {
            //textView.paintFlags = 0
        }

        fun parseHtml(data: String?): Spanned {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(data, Html.FROM_HTML_MODE_LEGACY)
            } else {
                @Suppress("DEPRECATION")
                return Html.fromHtml(data)
            }
        }

        fun getPriceFromString(priceString: String?): Float {
            var formatedPrice = 0.00f
            try {
                formatedPrice =
                    priceString?.replace("$", "")?.replace("KWD", "")?.replace("KD", "")
                        ?.replace(" ", "")?.replace(",", "")?.toFloat()!!
            } catch (e: Exception) {
            }
            return formatedPrice
        }

        fun getQuantity(qty: String?): Int {
            var quantity = 0
            try {
                quantity =qty!!.toInt()
            } catch (e: Exception) {
            }
            return quantity
        }

        fun getFormattedPrice(priceString: String?): String {
            var formatedPrice = 0.00f
            try {
                formatedPrice =
                    priceString?.replace("$", "")?.replace("KWD", "")?.replace("KD", "")
                        ?.replace(" ", "")?.replace(",", "")?.toFloat()!!
            } catch (e: Exception) {
            }
            return AppPrefs.getCurrency(MyApplication.getContext()) + String.format(
                "%.2f",
                formatedPrice
            )
        }

        fun showLoader(context: Context, loaderView: ImageView, dataView: View) {
            dataView.visibility = View.GONE
            /*Glide.with(context)
                .asGif()
                .load(R.drawable.grey_place_holder)
                .into(loaderView);*/
        }

        fun hideLoader(imageView: ImageView) {
            imageView.visibility = View.GONE
        }

        fun isEmailValid(email: String): Boolean {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

    }

}