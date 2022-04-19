package com.designmaster.sukar.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
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

class WishListFragment : BaseFragment(), View.OnClickListener, ApiCallListener {
    // TODO: Rename and change types of parameters

    var tabLayout: TabLayout? = null

    var shopsRecyclerView: RecyclerView? = null
    var productsRecyclerView: RecyclerView? = null

    var shopsByCategoryAdapter: WishlistShopsByCategoryAdapter? = null
    var productsByCategoryAdapter: WishlistProductsByCategoryAdapter? = null

    private var shopsData = ArrayList<ShopsData>()
    private var productsData = ArrayList<WishlistProductsByCategoryInfo>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.wishlist_fragment,
            container, false


        )

        if (AppPrefs.isLocaleEnglish(activity)) {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.wishlist),false)
        } else {
            (activity as MainActivity?)!!.setHeaders(resources.getString(R.string.wishlist),false)
        }

        findviewbyid(view)


        getWishlistShopsByCategory()
//        getProductsByCategory()

        return view
    }

    private fun findviewbyid(view: View) {


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
        shopsRecyclerView!!.isNestedScrollingEnabled = false;
        val mLayoutManager1: RecyclerView.LayoutManager = GridLayoutManager(context, 1)
        shopsRecyclerView!!.layoutManager = mLayoutManager1
        shopsRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(1, dpToPx(5), true))
        shopsRecyclerView!!.itemAnimator = DefaultItemAnimator()

        shopsByCategoryAdapter = WishlistShopsByCategoryAdapter(
            requireActivity(),
            shopsData
        )
        shopsRecyclerView!!.adapter = shopsByCategoryAdapter


        shopsByCategoryAdapter!!.setOnClickListener(object : WishlistShopsByCategoryAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {

//                val fragment = ProductsFragmentcnt()
//                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

            }

        })

        productsRecyclerView = view.findViewById(R.id.productsRecyclerView)
        productsRecyclerView!!.isNestedScrollingEnabled = false;
//        productsRecyclerView.layoutManager = LinearLayoutManager(
//            context,
//            LinearLayoutManager.VERTICAL,
//            false
//        )

        val mLayoutManager2: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        productsRecyclerView!!.layoutManager = mLayoutManager2
        productsRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(5), true))
        productsRecyclerView!!.itemAnimator = DefaultItemAnimator()

        productsByCategoryAdapter = WishlistProductsByCategoryAdapter(
            requireActivity(),
            productsData
        )
        productsRecyclerView!!.adapter = productsByCategoryAdapter


        productsByCategoryAdapter!!.setOnClickListener(object : WishlistProductsByCategoryAdapter.ClickListener {
            override fun OnItemClick(position: Int, v: View?) {


//                val fragment = ProductsFragmentcnt()
//                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()

            }

            override fun OnFavoriteClick(position: Int) {

            }

        })

        shopsRecyclerView!!.visibility= View.VISIBLE
        productsRecyclerView!!.visibility= View.GONE



        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if(tab!!.position==0) {
                    shopsRecyclerView!!.visibility= View.VISIBLE
                    productsRecyclerView!!.visibility= View.GONE
                }else{
                    shopsRecyclerView!!.visibility= View.GONE
                    productsRecyclerView!!.visibility= View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }





    override fun onClick(v: View) {

        when(v.id){


        }
    }

    private fun getWishlistShopsByCategory() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.wishlistShopsByCategory(
                AppPrefs.getUserId(requireActivity()).toString()
            ),
            this,
            ApiConstants.REQUEST_CODE.WISHLIST_SHOPS_BY_CATEGORY
        )

    }

    private fun getWishlistProductsByCategory() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.wishlistProductsByCategory(
                AppPrefs.getUserId(requireActivity()).toString()
            ),
            this,
            ApiConstants.REQUEST_CODE.WISHLIST_PRODUCTS_BY_CATEGORY
        )

    }

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {
            ApiConstants.REQUEST_CODE.WISHLIST_SHOPS_BY_CATEGORY ->{
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

                getWishlistProductsByCategory()
            }

            ApiConstants.REQUEST_CODE.WISHLIST_PRODUCTS_BY_CATEGORY ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    WishlistProductsByCategoryResponse::class.java,
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
        }
        hideProgress()
    }

    override fun onError(response: String, requestCode: Int) {
        showToast(response)
        hideProgress()

    }


}