package com.designmaster.sukar.fragments


import Flavoursresponse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.chabbal.slidingdotsplash.SlidingSplashView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.HomeActivity
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.ChapterAdapter
import com.designmaster.sukar.adapters.FlavourslistAdapter
import com.designmaster.sukar.adapters.ViewPagerAdapter
import com.designmaster.sukar.util.*
import com.designmaster.sukar.util.BaseFragment


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductsFragmentcnt : BaseFragment(), View.OnClickListener, ApiCallListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var backicon: ImageView
    lateinit var mImageViewPager: ViewPager
    var imagesurl: ArrayList<String> = ArrayList()
    lateinit var lldetailimg: SlidingSplashView
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    var catagwiseproadapter: ChapterAdapter? = null
    lateinit var catagwiseprorlv: RecyclerView
    val chaptersList: java.util.ArrayList<String> = java.util.ArrayList()
    lateinit var menuicon:ImageView
    lateinit var rl:RelativeLayout
    lateinit var rl1:RelativeLayout
    lateinit var flavoursrlv: RecyclerView
    private var flavourslistarray: java.util.ArrayList<Flavoursresponse>? = null
    var flavourslistAdapter: FlavourslistAdapter? = null
    // images array
    var images = intArrayOf(
        R.drawable.homebannerimg, R.drawable.homebannerimg, R.drawable.homebannerimg, R.drawable.homebannerimg,
        R.drawable.homebannerimg, R.drawable.homebannerimg, R.drawable.homebannerimg, R.drawable.homebannerimg
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
            R.layout.fragment_productscnt,
            container, false


        )
        findviewbyid(view)
        return view
    }

    private fun findviewbyid(view: View) {
        flavoursrlv = view.findViewById(R.id.rlvflavours)
        flavoursrlv.setNestedScrollingEnabled(false);
        flavoursrlv.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        )

        backicon=view.findViewById(R.id.backicon) as ImageView
        backicon.setOnClickListener(this)

        menuicon = view.findViewById(R.id.menuimg) as ImageView
        menuicon.setOnClickListener(this)

       /* rel1 = view.findViewById(R.id.rl1) as RelativeLayout
        rel1.setOnClickListener(this)
        rel2 = view.findViewById(R.id.rl12) as RelativeLayout
        rel2.setOnClickListener(this)
        rel3 = view.findViewById(R.id.rl121) as RelativeLayout
        rel3.setOnClickListener(this)
        rel4 = view.findViewById(R.id.rl122) as RelativeLayout
        rel4.setOnClickListener(this)*/


       // llpro = view.findViewById(R.id.llpro) as LinearLayout
        shoprl = view.findViewById(R.id.shoprl) as RelativeLayout
        prorl =view.findViewById(R.id.prorl) as RelativeLayout
        lineshop =view.findViewById(R.id.lineshop) as TextView
        linepro =view.findViewById(R.id.linepro) as TextView
        shoptv =view.findViewById(R.id.shoptv) as TextView
        protv =view.findViewById(R.id.producttv) as TextView
        shop1tv = view.findViewById(R.id.shop1) as TextView
        //shop2tv = view.findViewById(R.id.shop2) as TextView
      /*  catagwiseprorlv = view.findViewById(R.id.catagwiseinforlview) as RecyclerView
        catagwiseprorlv.setLayoutManager(
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        )
        chaptersList.add("SHOPS")
        chaptersList.add("PRODUCTS")
        comingfromhome=true
        catagwiseproadapter = ChapterAdapter(activity as FragmentActivity, chaptersList)
        catagwiseprorlv.adapter = catagwiseproadapter*/

        shoprl.setOnClickListener{
           shoptv.setTextColor(resources.getColor(R.color.black))
            protv.setTextColor(resources.getColor(R.color.sand))
           /* rel1.visibility = View.VISIBLE
            rel2.visibility = View.VISIBLE
            rel3.visibility = View.VISIBLE
            rel4.visibility = View.VISIBLE*/
           // llpro.visibility = View.VISIBLE
            lineshop.visibility=View.VISIBLE
            linepro.visibility=View.GONE
            /*shop1tv.text="HEALTHY FEAST"
            shop2tv.text="KLIN BAKERY"*/
        }
        prorl.setOnClickListener{
            shoptv.setTextColor(resources.getColor(R.color.sand))
            protv.setTextColor(resources.getColor(R.color.black))
            /*rel1.visibility = View.VISIBLE
            rel2.visibility = View.VISIBLE
            rel3.visibility = View.VISIBLE
            rel4.visibility = View.VISIBLE*/
           // llpro.visibility = View.VISIBLE
            linepro.visibility=View.VISIBLE
            lineshop.visibility=View.GONE
            /*shop1tv.text="SAVOURIES & SALTIES"
            shop2tv.text="PIES & TARTS"*/
        }

//        getflavoursapi()
    }

    private fun getflavoursapi() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getflavours(

            ),
            this,
            ApiConstants.REQUEST_CODE.FLAVOURS
        )

    }


    companion object {
        @kotlin.jvm.JvmField
        var comingfromhome: Boolean = false
       // lateinit var llpro:LinearLayout
       // lateinit var rel1:RelativeLayout
        lateinit var rel11:RelativeLayout
       // lateinit var rel2:RelativeLayout
       // lateinit var rel3:RelativeLayout
       // lateinit var rel4:RelativeLayout
        lateinit var shop1tv:TextView
       // lateinit var shop2tv:TextView

        lateinit var prorl:RelativeLayout
        lateinit var shoprl:RelativeLayout
        lateinit var lineshop:TextView
        lateinit var linepro:TextView

        lateinit var shoptv:TextView
        lateinit var protv:TextView

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductsFragmentcnt().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {
           when(v.id){
            R.id.menuimg->{
                val fragment = FilterFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.rl1->{
                val fragment = ChocolatebrownwichFragment()
                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.rl12->{
                val fragment = ChocolatebrownwichFragment()
                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.backicon->{
                val fragment = HomeFragmentcnt()
                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
        }
    }

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.FLAVOURS -> {
                val apiResponse = RetrofitApiCall.getPayload(
                    Flavoursresponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {
                    flavourslistarray = ArrayList();
//                    if (MyApplication.LANGUAGE_SELECTION == "English") {
                        for (i in 0 until apiResponse.output.data.size) {
                            flavourslistAdapter = FlavourslistAdapter(
                                requireActivity(),
                                apiResponse.output.data
                            )
                            flavoursrlv.adapter = flavourslistAdapter

                        }
//                    }

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