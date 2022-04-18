package com.designmaster.sukar.fragments


import Categorylistresponse
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.chabbal.slidingdotsplash.SlidingSplashView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.CategorylistAdapter
import com.designmaster.sukar.adapters.ChapterAdapter
import com.designmaster.sukar.adapters.MyCustomPagerAdapter
import com.designmaster.sukar.models.Bannersresponse
import com.designmaster.sukar.util.*
import com.google.android.material.tabs.TabLayout
import com.designmaster.sukar.util.BaseFragment
import java.util.*
import kotlin.collections.ArrayList
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
class HomeFragment : BaseFragment(), ApiCallListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var mImageViewPager: ViewPager

    var imagesurl: ArrayList<String> = ArrayList()
    lateinit var lldetailimg: SlidingSplashView
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    var catagwiseproadapter: ChapterAdapter? = null
    lateinit var chatimg: AppCompatImageView
    private var singledetailarray: java.util.ArrayList<Bannersresponse>? = null
    private var categorylistarray: java.util.ArrayList<Categorylistresponse>? = null
    val chaptersList: java.util.ArrayList<String> = java.util.ArrayList()
    var cataglistAdapter: CategorylistAdapter? = null
    lateinit var catagrlv: RecyclerView
    // images array
    var images = intArrayOf(
        R.drawable.ban1, R.drawable.ban2, R.drawable.homebannerimg, R.drawable.ban3,
        R.drawable.ban4, R.drawable.ban5, R.drawable.ban1, R.drawable.ban2
    )
    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed
    val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions.
    val NUM_PAGES = 3

    // Creating Object of ViewPagerAdapter
    var mViewPagerAdapter: MyCustomPagerAdapter? = null
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

        (activity as MainActivity?)!!.setHeaders(requireActivity().resources.getString(R.string.account),false)

        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_home,
            container, false


        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        chatimg = view.findViewById(R.id.chatimg) as AppCompatImageView
        catagrlv = view.findViewById(R.id.rlvcataglist)
        catagrlv.isNestedScrollingEnabled = false;
        catagrlv.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        chatimg.setOnClickListener {
//            val fragment = ChartFragment()
//            (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                R.id.fragment_container,
//                fragment
//            ).addToBackStack(null).commit()

            val fragment = ChartFragment()
            (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                fragment
            ).addToBackStack(null).commit()
        }


        mImageViewPager = view.findViewById(R.id.pager) as ViewPager
        val tabLayout = view.findViewById(R.id.tabDots) as TabLayout
        tabLayout.setupWithViewPager(mImageViewPager, true)

        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())


        bannersapi()
        Categoriesapi()

    }

    private fun Categoriesapi() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getcategories(

            ),
            this,
            ApiConstants.REQUEST_CODE.CATEGORIES
        )

    }

    private fun bannersapi() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getbanners(

            ),
            this,
            ApiConstants.REQUEST_CODE.BANNERS
        )

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

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.BANNERS -> {
                val apiResponse = RetrofitApiCall.getPayload(
                    Bannersresponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {

                    singledetailarray = ArrayList();


                        imagesurl.clear()
                        for (i in 0..apiResponse.output.data.size-1) {
                            singledetailarray!!.add(apiResponse)
                            val img1 =
                                singledetailarray!!.get(i).output.data.get(i).image
                            imagesurl.add(img1);


                        }
                        mViewPagerAdapter = context?.let { MyCustomPagerAdapter(it,
                            imagesurl
                        ) }

                        // Adding the Adapter to the ViewPager
                        mImageViewPager.setAdapter(mViewPagerAdapter)

/*After setting the adapter use the timer */
                        val handler = Handler()
                        val Update = Runnable {
                            if (currentPage == NUM_PAGES) {
                                currentPage = 0
                            }
                            mImageViewPager.setCurrentItem(currentPage++, true)
                        }

                        timer = Timer() // This will create a new Thread

                        timer!!.schedule(object : TimerTask() {
                            // task to be scheduled
                            override fun run() {
                                handler.post(Update)
                            }
                        }, DELAY_MS, PERIOD_MS)





                }
            }
            ApiConstants.REQUEST_CODE.CATEGORIES ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    Categorylistresponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {
                    categorylistarray = ArrayList();

                        for (i in 0 until apiResponse.output.data.size) {
                            cataglistAdapter = CategorylistAdapter(
                                requireActivity(),
                                apiResponse.output.data
                            )
                            catagrlv.adapter = cataglistAdapter



                        }


                    }
            }
        }
        hideProgress()

    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()

    }
}