package com.designmaster.sukar.fragments

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.chabbal.slidingdotsplash.SlidingSplashView
import com.designmaster.sukar.R
import com.designmaster.sukar.activities.MainActivity
import com.designmaster.sukar.adapters.*
import com.designmaster.sukar.models.*
import com.designmaster.sukar.util.*
import com.google.android.material.tabs.TabLayout

class SubCategoryProductsByShopFragment: BaseFragment(), View.OnClickListener, ApiCallListener {
    // TODO: Rename and change types of parameters

    var tabLayout: TabLayout? = null
    var vPagerImages: ViewPager? = null
    var productsRecyclerView: RecyclerView? = null

    var productsByCategoryAdapter: ProductsBySubCategoryAdapter? = null

    private var featuredImage = ArrayList<FeaturedImage>()
    private var subCategoriesByShopData = ArrayList<SubCategoriesByShopData>()
    private var productsData = ArrayList<ProductsBySubCategoryData>()

    var shop_Id: String = ""
    var sub_Cat_Id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (arguments != null) {
            shop_Id = requireArguments().getString("ShopId").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(
            R.layout.sub_category_products_by_shop_fragment,
            container, false


        )
        findviewbyid(view)

        getBannersByShopId()

        return view
    }

    private fun findviewbyid(view: View) {

        vPagerImages = view.findViewById(R.id.vPagerImages)

        var tabLabels_en = arrayOf("SHOPS", "PRODUCTS")

        tabLayout = view.findViewById(R.id.tabLayout);

//        tabLayout!!.addTab(tabLayout!!.newTab());
//        tabLayout!!.addTab(tabLayout!!.newTab());

        // loop through all navigation tabs

//        for (i in 0 until tabLayout!!.tabCount) {
//            // inflate the Parent LinearLayout Container for the tab
//            // from the layout nav_tab.xml file that we created 'R.layout.nav_tab
//            var tab: LinearLayout
//            tab = if (AppPrefs.isLocaleEnglish(activity)) {
//                LayoutInflater.from(requireActivity()).inflate(R.layout.tab_layout, null) as LinearLayout
//            } else {
//                LayoutInflater.from(requireActivity()).inflate(R.layout.tab_layout, null) as LinearLayout
//            }
//
//
//            // get child TextView and ImageView from this layout for the icon and label
//            val tab_label = tab.findViewById<View>(R.id.nav_label) as TextView
//
//            // set the label text by getting the actual string value by its id
//            // by getting the actual resource value `getResources().getString(string_id)`
//            if (AppPrefs.isLocaleEnglish(activity)) {
//                tab_label.text = tabLabels_en.get(i)
//            } else {
//                tab_label.text = tabLabels_en.get(i)
//            }
////                tab_label.setTextColor(Color.parseColor("#FFFFFF"))
//
//            // finally publish this custom view to navigation tab
//            tabLayout!!.getTabAt(i)!!.customView = tab
//        }


        productsRecyclerView = view.findViewById(R.id.productsRecyclerView)
        productsRecyclerView!!.isNestedScrollingEnabled = false;


        val mLayoutManager2: RecyclerView.LayoutManager = GridLayoutManager(context, 2)
        productsRecyclerView!!.layoutManager = mLayoutManager2
        productsRecyclerView!!.addItemDecoration(GridSpacingItemDecoration(2, dpToPx(5), true))
        productsRecyclerView!!.itemAnimator = DefaultItemAnimator()

        productsByCategoryAdapter = ProductsBySubCategoryAdapter(
            requireActivity(),
            productsData
        )
        productsRecyclerView!!.adapter = productsByCategoryAdapter


        productsByCategoryAdapter!!.setOnClickListener(object : ProductsBySubCategoryAdapter.ClickListener {
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

        })

//        productsRecyclerView!!.visibility= View.GONE



        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                sub_Cat_Id = subCategoriesByShopData[tab!!.position].id
                getProductsBySubCatId()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }





    override fun onClick(v: View) {

        when(v.id){
//            R.id.menuimg->{
//
//                val fragment = FilterFragment()
//                (activity as MainActivity?)!!.supportFragmentManager.beginTransaction().replace(
//                    R.id.fragment_container,
//                    fragment
//                ).addToBackStack(null).commit()
//            }



        }
    }


    private fun getBannersByShopId() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.bannersByShopId(
                shop_Id
            ),
            this,
            ApiConstants.REQUEST_CODE.BANNERS_BY_SHOP_ID
        )

    }

    private fun getSubCategoriesByShopId() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.subCategoryByShopId(
                shop_Id
            ),
            this,
            ApiConstants.REQUEST_CODE.SUB_CATEGORY_BY_SHOP_ID
        )

    }



    private fun getProductsBySubCatId() {
        showProgress()
        RetrofitApiCall.hitApi(
            ApiClient.apiInterFace.getProductsBySubCatId(
                sub_Cat_Id,
                AppPrefs.getUserId(requireActivity()).toString()
            ),
            this,
            ApiConstants.REQUEST_CODE.PRODUCTS_BY_SUB_CATEGORY_ID
        )

    }

    override fun onSuccess(response: String, requestCode: Int) {
        when (requestCode) {

            ApiConstants.REQUEST_CODE.BANNERS_BY_SHOP_ID ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    BannersByShopResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {
                    featuredImage.clear()
//                    for (i in apiResponse.output.featuredImage.indices) {

                        featuredImage.addAll(apiResponse.output.featuredImage)



                        val mAdapter = CustomImagePagerAdapter(requireActivity(), featuredImage)
                        vPagerImages!!.adapter = mAdapter
//        tabDots!!.setupWithViewPager(vPagerImages, true)
//                    }

                }

                getSubCategoriesByShopId()
            }

            ApiConstants.REQUEST_CODE.SUB_CATEGORY_BY_SHOP_ID ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    SubCategoriesByShopResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {
                    subCategoriesByShopData.clear()
                    for (i in apiResponse.output.info.indices) {

                        subCategoriesByShopData.add(apiResponse.output.info[i])


//                        for (j in subCategoriesByShopData.indices) {
                            tabLayout!!.addTab(tabLayout!!.newTab());
//                        }

                        sub_Cat_Id = subCategoriesByShopData[0].id
                    }

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
                            tab_label.text = subCategoriesByShopData[i].titleEn
                        } else {
                            tab_label.text = subCategoriesByShopData[i].titleAr
                        }
//                tab_label.setTextColor(Color.parseColor("#FFFFFF"))

                        // finally publish this custom view to navigation tab
                        tabLayout!!.getTabAt(i)!!.customView = tab
                    }
                    getProductsBySubCatId()
                }

            }

            ApiConstants.REQUEST_CODE.PRODUCTS_BY_SUB_CATEGORY_ID ->{
                val apiResponse = RetrofitApiCall.getPayload(
                    ProductsBySubCategoryResponse::class.java,
                    response
                )
                if (apiResponse.output.success == 1) {
                    productsData.clear()
//                    for (i in apiResponse.output.info.indices) {

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


    class CustomImagePagerAdapter(
        private val mContext: Context,
        images1: ArrayList<FeaturedImage>
    ) : PagerAdapter() {
        var mLayoutInflater: LayoutInflater = mContext.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        private val images: ArrayList<FeaturedImage> = images1

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
//            val itemView = mLayoutInflater.inflate(R.layout.tutorial_pager_layout, container, false)
            val itemView: View?

//            if (AppPrefs.isLocaleEnglish(mContext!!)) {
            itemView = mLayoutInflater.inflate(R.layout.home_pager_layout, container, false)
//            }else{
//                itemView = mLayoutInflater.inflate(R.layout.tutorial_pager_layout_ar, container, false)
//            }

            val img = itemView.findViewById<View>(R.id.img) as ImageView

//            img.layoutParams.width = AppPrefs.get_device_width(mContext)  - 50;
//            img.layoutParams.height = (AppPrefs.get_device_width(mContext) / 2) - 50;

//            if(position==0){
//                itemView!!.setPadding(0, 0, 150, 0);
//            }else{
//
//            }


            Glide.with(mContext)
                .load(images?.get(position)?.image!!) //                    .placeholder(R.drawable.place_holder)
                .listener(object : RequestListener<Drawable?> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable?>,
                        isFirstResource: Boolean
                    ): Boolean {

//                            progress1.setVisibility(View.GONE);
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any,
                        target: Target<Drawable?>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
//                            progress1.setVisibility(View.GONE);
                        return false
                    }
                })
                .into(img)
            container.addView(itemView)
            img.setOnClickListener {
                //                    showGallery(position);
            }
            return itemView
        }

        override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
            collection.removeView(view as View)
        }

        override fun getCount(): Int {
//        Log.v("mResources.length",""+mResources.length);
            return images.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        fun showGallery(position: Int) {

//            final Dialog dialog = new Dialog(context);
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.setContentView(R.layout.dialog_layout_for_gallery_image);
//            WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
//            layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
//            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//            dialog.getWindow().setAttributes(layoutParams);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            dialog.setCancelable(true);
//            ViewPager pager_post_photo = (ViewPager) dialog.findViewById(R.id.pager_post_photo);
//            Adapter_Gallery adapter_gallery = new Adapter_Gallery(context, mResources);
//            pager_post_photo.setAdapter(adapter_gallery);
//            pager_post_photo.setCurrentItem(position);
//            dialog.show();
        }

    }
}