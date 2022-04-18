package com.designmaster.sukar.fragments


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.chabbal.slidingdotsplash.SlidingSplashView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.*
import com.designmaster.sukar.models.*
import com.designmaster.sukar.util.*
import com.google.android.material.tabs.TabLayout


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragmentcnt : BaseFragment(), View.OnClickListener, ApiCallListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
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
    lateinit var backicon:ImageView

    var tabLayout: TabLayout? = null


    var shopsByCategoryAdapter: ShopsByCategoryAdapter? = null
    var productsByCategoryAdapter: ProductsByCategoryAdapter? = null

    private var shopsData = ArrayList<ShopsData>()
    private var productsData = ArrayList<ProductsData>()

    var category_Id: String = ""

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

        if (arguments != null) {
            category_Id = requireArguments().getString("CategoryId").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.fragment_homecnt,
            container, false


        )
        findviewbyid(view)


        getShopsByCategory()
//        getProductsByCategory()

        return view
    }

    private fun findviewbyid(view: View) {
        menuicon = view.findViewById(R.id.menuimg) as ImageView
        menuicon.setOnClickListener(this)

        backicon = view.findViewById(R.id.btnback) as ImageView
        backicon.setOnClickListener(this)

        rel1 = view.findViewById(R.id.rl) as RelativeLayout
        rel1.setOnClickListener(this)

        rel11 = view.findViewById(R.id.rl11) as RelativeLayout
        rel11.setOnClickListener(this)

        rel2 = view.findViewById(R.id.rl1) as RelativeLayout
        llpro = view.findViewById(R.id.llpro) as LinearLayout
        shoprl = view.findViewById(R.id.shoprl) as RelativeLayout
        prorl =view.findViewById(R.id.prorl) as RelativeLayout
        lineshop =view.findViewById(R.id.lineshop) as TextView
        linepro =view.findViewById(R.id.linepro) as TextView
        shoptv =view.findViewById(R.id.shoptv) as TextView
        protv =view.findViewById(R.id.producttv) as TextView
        shop1tv = view.findViewById(R.id.shop1) as TextView
        shop2tv = view.findViewById(R.id.shop2) as TextView
        catagwiseprorlv = view.findViewById(R.id.catagwiseinforlview) as RecyclerView
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
        catagwiseprorlv.adapter = catagwiseproadapter

        shoprl.setOnClickListener{
           shoptv.setTextColor(resources.getColor(R.color.black))
            protv.setTextColor(resources.getColor(R.color.sand))
//            rel1.visibility = View.VISIBLE
//            rel11.visibility = View.VISIBLE
//            llpro.visibility = View.GONE
            lineshop.visibility=View.VISIBLE
            linepro.visibility=View.GONE
            menuicon.visibility = View.GONE
//            shop1tv.text="HEALTHY FEAST"
//            shop2tv.text="KLIN BAKERY"

            shopsRecyclerView.visibility=View.VISIBLE
            productsRecyclerView.visibility=View.GONE
        }
        prorl.setOnClickListener{
            shoptv.setTextColor(resources.getColor(R.color.sand))
            protv.setTextColor(resources.getColor(R.color.black))
            menuicon.visibility = View.VISIBLE
//            rel1.visibility = View.VISIBLE
//            rel11.visibility = View.VISIBLE
//            llpro.visibility = View.GONE
            linepro.visibility=View.VISIBLE
            lineshop.visibility=View.GONE
//            shop1tv.text="SAVOURIES & SALTIES"
//            shop2tv.text="PIES & TARTS"

            shopsRecyclerView.visibility=View.GONE
            productsRecyclerView.visibility=View.VISIBLE
        }

        var tabLabels_en = arrayOf("SHOPS", "PRODUCTS")

        tabLayout = view.findViewById(R.id.tabLayout);

        tabLayout!!.addTab(tabLayout!!.newTab());
        tabLayout!!.addTab(tabLayout!!.newTab());

        // loop through all navigation tabs

        for (i in 0 until tabLayout!!.tabCount) {
            // inflate the Parent LinearLayout Container for the tab
            // from the layout nav_tab.xml file that we created 'R.layout.nav_tab
            var tab: LinearLayout
            tab = if (AppPrefs.isLocaleEnglish(activity)) {
                LayoutInflater.from(requireActivity()).inflate(R.layout.tab_layout, null) as LinearLayout
            } else {
                LayoutInflater.from(requireActivity()).inflate(R.layout.tab_layout, null) as LinearLayout
            }


            // get child TextView and ImageView from this layout for the icon and label
            val tab_label = tab.findViewById<View>(R.id.nav_label) as TextView

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            if (AppPrefs.isLocaleEnglish(activity)) {
                tab_label.text = tabLabels_en.get(i)
            } else {
                tab_label.text = tabLabels_en.get(i)
            }
//                tab_label.setTextColor(Color.parseColor("#FFFFFF"))

            // finally publish this custom view to navigation tab
            tabLayout!!.getTabAt(i)!!.customView = tab
        }


        shopsRecyclerView = view.findViewById(R.id.shopsRecyclerView)
        shopsRecyclerView.isNestedScrollingEnabled = false;
        val mLayoutManager1: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        shopsRecyclerView!!.layoutManager = mLayoutManager1
        shopsRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(5), true))
        shopsRecyclerView!!.itemAnimator = DefaultItemAnimator()

        shopsByCategoryAdapter = ShopsByCategoryAdapter(
            requireActivity(),
            shopsData
        )
        shopsRecyclerView!!.adapter = shopsByCategoryAdapter


        shopsByCategoryAdapter!!.setOnClickListener(object : ShopsByCategoryAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {

//                val fragment = ProductsFragmentcnt()
                val fragment = SubCategoryProductsByShopFragment()

                val bundle = Bundle()
//                bundle.putString("ImagePath", "https://batocare.com/demo/contact-us.php?screen=app")
                bundle.putString("ShopId", shopsData[position].id)
                fragment.arguments = bundle

                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()

            }

        })

        productsRecyclerView = view.findViewById(R.id.productsRecyclerView)
        productsRecyclerView.isNestedScrollingEnabled = false;
//        productsRecyclerView.layoutManager = LinearLayoutManager(
//            context,
//            LinearLayoutManager.VERTICAL,
//            false
//        )

        val mLayoutManager2: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        productsRecyclerView!!.layoutManager = mLayoutManager2
        productsRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(5), true))
        productsRecyclerView!!.itemAnimator = DefaultItemAnimator()

        productsByCategoryAdapter = ProductsByCategoryAdapter(
            requireActivity(),
            productsData
        )
        productsRecyclerView!!.adapter = productsByCategoryAdapter


        productsByCategoryAdapter!!.setOnClickListener(object : ProductsByCategoryAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {


//                val fragment = ProductsFragmentcnt()
//                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = ProductDetailsPageFragment()
                val bundle = Bundle()
                bundle.putString("ProductId", productsData[position].id)
                fragment.arguments = bundle
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()

            }

            override fun OnFavoriteClick(position: Int) {

                addToWishlist(productsData[position].shopId,productsData[position].id)

            }

        })

        shopsRecyclerView.visibility=View.VISIBLE
        productsRecyclerView.visibility=View.GONE



        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.position==0) {
                    shopsRecyclerView.visibility=View.VISIBLE
                    productsRecyclerView.visibility=View.GONE
                }else{
                    shopsRecyclerView.visibility=View.GONE
                    productsRecyclerView.visibility=View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }



    companion object {
        @kotlin.jvm.JvmField
        var comingfromhome: Boolean = false
        lateinit var llpro:LinearLayout
        lateinit var rel1:RelativeLayout
        lateinit var rel11:RelativeLayout
        lateinit var rel2:RelativeLayout
        lateinit var shop1tv:TextView
        lateinit var shop2tv:TextView

        lateinit var prorl:RelativeLayout
        lateinit var shoprl:RelativeLayout
        lateinit var lineshop:TextView
        lateinit var linepro:TextView

        lateinit var shoptv:TextView
        lateinit var protv:TextView

        lateinit var shopsRecyclerView: RecyclerView
        lateinit var productsRecyclerView: RecyclerView

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(v: View) {

        when(v.id){
            R.id.menuimg->{
//                val fragment = FilterFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = FilterFragment()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.rl->{
//                val fragment = ProductsFragmentcnt()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = ProductsFragmentcnt()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
            R.id.backicon->{
//                val fragment = HomeFragment()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                (activity as MainActivity?)!!.supportFragmentManager.popBackStackImmediate()
            }
            R.id.rl11->{
//                val fragment = ProductsFragmentcnt()
//                (activity as HomeActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

                val fragment = ProductsFragmentcnt()
                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
                    R.id.fragment_container,
                    fragment
                ).addToBackStack(null).commit()
            }
        }
    }

    private fun getShopsByCategory() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.shopsByCategory(
                category_Id
            ),
            this,
            ApiConstants.REQUEST_CODE.SHOPS_BY_CATEGORY
        )

    }

    private fun getProductsByCategory() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.productsByCategory(
                category_Id,
                ""
            ),
            this,
            ApiConstants.REQUEST_CODE.PRODUCTS_BY_CATEGORY
        )

    }


    private fun addToWishlist(shopId: String, productId: String) {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.addToWishlist(
                AppPrefs.getUserId(requireActivity()).toString(),
                shopId,
                productId
            ),
            this,
            ApiConstants.REQUEST_CODE.ADD_TO_WISHLIST
        )

    }


    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.SHOPS_BY_CATEGORY ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    ShopsByCategoryResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {
                    shopsData.clear()
//                    for (i in apiResponse.output.info.indices) {

                        shopsData.addAll(apiResponse.output.info)
                        shopsByCategoryAdapter!!.notifyDataSetChanged()


//                    }

                }

                getProductsByCategory()
            }

            ApiConstants.REQUEST_CODE.PRODUCTS_BY_CATEGORY ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    ProductsByCategoryResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {
                    productsData.clear()
//                    for (i in 0 until apiResponse.output.info.size) {

                        productsData.addAll(apiResponse.output.info)
                        productsByCategoryAdapter!!.notifyDataSetChanged()


//                    }

                }
            }

            ApiConstants.REQUEST_CODE.ADD_TO_WISHLIST ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    AddToWishlistResponse::class.java,
                    response
                )

                alertDialog(requireActivity(),apiResponse.output.message)

            }
        }
        hideProgress()
    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()

    }


    private fun alertDialog(context: Context, msg: String?) {
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
            getProductsByCategory()
        }
        dialog.show()
    }
}