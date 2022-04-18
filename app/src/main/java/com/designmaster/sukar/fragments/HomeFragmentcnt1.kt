package com.designmaster.sukar.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
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
class HomeFragmentcnt1 : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mImageViewPager: ViewPager
    lateinit var rlimg:RelativeLayout
    lateinit var rlimg1:RelativeLayout
    lateinit var rlimg2:RelativeLayout
    var imagesurl: ArrayList<String> = ArrayList()
    lateinit var lldetailimg: SlidingSplashView
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    var catagwiseproadapter: ChapterAdapter? = null

    val chaptersList: java.util.ArrayList<String> = java.util.ArrayList()
    // images array
    var images = intArrayOf(
        R.drawable.homebannerimg, R.drawable.cakes, R.drawable.scoopeddesserts, R.drawable.chocolate,
        R.drawable.cookies, R.drawable.nuts, R.drawable.bites, R.drawable.sweetpastries
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
            R.layout.fragment_homecnt1,
            container, false


        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {


        rlimg = view.findViewById(R.id.rlimg) as RelativeLayout
        rlimg1 = view.findViewById(R.id.rlimg1) as RelativeLayout
        rlimg2 = view.findViewById(R.id.rlimg2) as RelativeLayout

rlimg.setOnClickListener{
    val fragment = HomeFragmentcnt()
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
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}