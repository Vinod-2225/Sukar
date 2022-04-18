package com.designmaster.sukar.fragments


import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.chabbal.slidingdotsplash.SlidingSplashView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.adapters.ChapterAdapter
import com.designmaster.sukar.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.designmaster.sukar.util.BaseFragment
import kotlin.math.max
import kotlin.math.min


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragmentopenstore : BaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mImageViewPager: ViewPager
    lateinit var rlimg: RelativeLayout
    lateinit var rlimg1: RelativeLayout
    lateinit var rlimg2: RelativeLayout
    var imagesurl: ArrayList<String> = ArrayList()
    lateinit var lldetailimg: SlidingSplashView
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    var catagwiseproadapter: ChapterAdapter? = null
    lateinit var chatimg: AppCompatImageView
    lateinit var infoimg:ImageView

    val chaptersList: java.util.ArrayList<String> = java.util.ArrayList()

    // images array
    var images = intArrayOf(
        R.drawable.ban1, R.drawable.ban2, R.drawable.homebannerimg, R.drawable.ban3,
        R.drawable.ban4, R.drawable.ban5, R.drawable.ban1, R.drawable.ban2
    )

    // Creating Object of ViewPagerAdapter
    var mViewPagerAdapter: ViewPagerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_home_openstore,
            container, false


        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        chatimg = view.findViewById(R.id.chatimg) as AppCompatImageView
        infoimg = view.findViewById(R.id.info) as ImageView
        infoimg.setOnClickListener(this)

        rlimg = view.findViewById(R.id.rlimg) as RelativeLayout
        rlimg1 = view.findViewById(R.id.rlimg1) as RelativeLayout
        rlimg2 = view.findViewById(R.id.rlimg2) as RelativeLayout

        chatimg.setOnClickListener {
            val fragment = ChartFragment()
            (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment
            ).addToBackStack(null).commit()
        }
        rlimg.setOnClickListener {
            val fragment = HomeFragmentcnt1()
            (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment
            ).addToBackStack(null).commit()
        }

        mImageViewPager = view.findViewById(R.id.pager) as ViewPager
        val tabLayout = view.findViewById(R.id.tabDots) as TabLayout
        tabLayout.setupWithViewPager(mImageViewPager, true)

        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = context?.let { ViewPagerAdapter(it, images) }

        // Adding the Adapter to the ViewPager
        mImageViewPager.setAdapter(mViewPagerAdapter)


    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))
            lldetailimg.scaleX = scaleFactor
            lldetailimg.scaleY = scaleFactor
            return true
        }
    }

    companion object {
        @kotlin.jvm.JvmField
        var comingfromhome: Boolean = false


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragmentopenstore().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.info->{
                val inflater =
                    context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                /*  val popupView: View = if(DesignMoveinApp.instance.language.equals("English")||DesignMoveinApp.instance.language.equals("")) {
  */
                val popupView: View=  inflater.inflate(R.layout.joosie, null)


                /* }else{
                     inflater.inflate(R.layout.activity_forgot_password_ar, null)
                 }
 */
                val width = ConstraintLayout.LayoutParams.WRAP_CONTENT
                val height = ConstraintLayout.LayoutParams.WRAP_CONTENT
                val focusable = true // lets taps outside the popup also dismiss it

                val popupWindow = PopupWindow(popupView, width, height, focusable)
                popupWindow.setAnimationStyle(R.style.Animation);

                popupWindow.showAtLocation(v, Gravity.CENTER, 10, 10)
               val exitrl = popupView.findViewById(R.id.exitrl) as RelativeLayout

             //   val cancel = popupView.findViewById(R.id.cancel) as Button
              //  val submit = popupView.findViewById(R.id.reset_pwd) as Button
                exitrl.setOnClickListener {
                    popupWindow.setAnimationStyle(R.style.Animation);
                    popupWindow.dismiss()

                }
                /*submit.setOnClickListener {
                    LoginActivity.fwdstr = email.text.toString()
                    if (!isValidPassword(LoginActivity.fwdstr)) {
                        Toast.makeText(context, "Enter your Email", Toast.LENGTH_LONG).show()
                        email.setError("Enter your Email")

                    } else {
                        // forgot()
                        popupWindow.setAnimationStyle(R.style.Animation);
                        popupWindow.dismiss()
//Toast.makeText(context,"password sent successfully to your mail",Toast.LENGTH_SHORT).show()
                    }
                }*/
            }
        }

    }
}