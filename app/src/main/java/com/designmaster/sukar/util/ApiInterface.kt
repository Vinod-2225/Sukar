package com.designmaster.sukar.util

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    //1
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.USER_REGISTRATION)
    fun userRegistration(

        @Field("firstname") firstname: String,
        @Field("lastname") lastname: String,
        @Field("email") email: String,
        @Field("countrycode") countrycode: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("confpassword") confpassword: String,
        @Field("device_token") device_token: String,
        @Field("device_type") device_type: String

    ): Call<ResponseBody>


    //2
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.USER_LOGIN)
    fun userLogin(

        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String,
        @Field("device_type") device_type: String

    ): Call<ResponseBody>


    //3
    @GET(ApiConstants.API_END_POINT.ABOUTUS)
    fun getaboutus(

    ): Call<ResponseBody>


    //4
    @GET(ApiConstants.API_END_POINT.BANNERS)
    fun getbanners(

    ): Call<ResponseBody>


    //5
    @GET(ApiConstants.API_END_POINT.CATEGORIES)
    fun getcategories(

    ): Call<ResponseBody>

    //6
    @GET(ApiConstants.API_END_POINT.COUPONS)
    fun getcoupons(

    ): Call<ResponseBody>


    //7
    @GET(ApiConstants.API_END_POINT.FLAVOURS)
    fun getflavours(

    ): Call<ResponseBody>



    //8
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.MY_PROFILE)
    fun myProfile(

        @Field("user_id") userId: String

    ): Call<ResponseBody>


    //9
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.UPDATE_PROFILE)
    fun updateProfile(

        @Field("user_id") user_id: String,
        @Field("fname") fname: String,
        @Field("lname") lname: String,
        @Field("email") email: String,
        @Field("phone") phone: String,


        ): Call<ResponseBody>


    //10
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.SHOPS_BY_CATEGORY)
    fun shopsByCategory(

        @Field("cat_id") catId: String

    ): Call<ResponseBody>


    //11
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.PRODUCTS_BY_CATEGORY)
    fun productsByCategory(

        @Field("cat_id") catId: String,
        @Field("sub_cat_id") subCatId: String

    ): Call<ResponseBody>


    //12
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.BANNERS_BY_SHOP_ID)
    fun bannersByShopId(

        @Field("shop_id") shopId: String

    ): Call<ResponseBody>


    //13
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.SUB_CATEGORY_BY_SHOP_ID)
    fun subCategoryByShopId(

        @Field("shop_id") shopId: String

    ): Call<ResponseBody>


    //14
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.PRODUCTS_BY_SUB_CATEGORY_ID)
    fun getProductsBySubCatId(

        @Field("store_cat_id") subCatId: String,
        @Field("user_id") user_id: String

    ): Call<ResponseBody>


    //15
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.PRODUCTS_DETAILS_BY_ID)
    fun getProductsDetailsByProId(

        @Field("id") productId: String

    ): Call<ResponseBody>

    //16
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.ADD_TO_CART)
    fun addToCart(

        @Field("user_id") userId: String,
        @Field("product_id") productId: String,
        @Field("quantity") quantity: String,
        @Field("comments") comments: String,
        @Field("flavour_id") flavour_id: String,
        @Field("total_price") total_price: String

    ): Call<ResponseBody>

    //17
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.CART_LIST)
    fun getCartList(

        @Field("user_id") userId: String

    ): Call<ResponseBody>

    //18
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.REMOVE_FROM_CART_LIST)
    fun removeFromCartList(

        @Field("user_id") userId: String,
        @Field("product_id") productId: String,

        ): Call<ResponseBody>

    //19
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.UPDATE_QUANTITY_CART_LIST)
    fun updateQuantityCartList(

        @Field("user_id") userId: String,
        @Field("product_id") productId: String,
        @Field("type") type: String

        ): Call<ResponseBody>


    //20
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.ADDRESS_LIST)
    fun getAddressList(

        @Field("user_id") userId: String

    ): Call<ResponseBody>

    //21
    @GET(ApiConstants.API_END_POINT.GOVERNORATES)
    fun getGovernorates(

    ): Call<ResponseBody>

    //22
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.AREAS)
    fun getAreas(

        @Field("governates_id") governates_id: String

    ): Call<ResponseBody>

    //23
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.ADD_ADDRESS)
    fun addAddress(


        @Field("user_id") user_id: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("countrycode") countrycode: String,
        @Field("mobile_no") mobile_no: String,
        @Field("area") area: String,
        @Field("governate") governate: String,
        @Field("building_no") building_no: String,
        @Field("block") block: String,
        @Field("street") street: String,
        @Field("floor_no") floor_no: String,
        @Field("lat") lat: String,
        @Field("lan") lan: String

    ): Call<ResponseBody>


    //23
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.ADD_TO_WISHLIST)
    fun addToWishlist(

        @Field("user_id") user_id: String,
        @Field("shop_id") name: String,
        @Field("product_id") email: String

    ): Call<ResponseBody>


    //24
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.WISHLIST_SHOPS_BY_CATEGORY)
    fun wishlistShopsByCategory(

        @Field("user_id") userId: String

    ): Call<ResponseBody>


    //25
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.WISHLIST_PRODUCTS_BY_CATEGORY)
    fun wishlistProductsByCategory(

        @Field("user_id") userId: String

    ): Call<ResponseBody>

    //26
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.ORDER_NOW)
    fun orderNow(

        @Field("user_id") user_id: String,
        @Field("shipping_address") shipping_address: String,
        @Field("deliverycharge") delivery_charge: String,
        @Field("paymentMethod") payment_method: String,
        @Field("grandtotal") grand_total: String,
        @Field("payvat") pay_vat: String

    ): Call<ResponseBody>

    //27
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.MY_ORDERS)
    fun myOrders(

        @Field("buyer") buyer: String

    ): Call<ResponseBody>

    //28
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.MY_ORDER_DETAILS)
    fun myOrderDetails(

        @Field("OrderID") orderID: String

    ): Call<ResponseBody>

//    company_name:design master global it ovt ltd
//    email:designmaste@gmail.com
//    mobile:888888888
//    contact_name:anji
//    year_establishment:2008
//    special_food_type:all
//    physical_store:yes, kuwait
//    links:example.com
//    additional_information:nothing
//    about_us:friends
//    countrycode:+965

    //29
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.OPEN_A_STORE)
    fun openAStore(

        @Field("company_name") company_name: String,
        @Field("email") email: String,
        @Field("mobile") mobile: String,
        @Field("contact_name") contact_name: String,
        @Field("year_establishment") year_establishment: String,
        @Field("special_food_type") special_food_type: String,
        @Field("physical_store") physical_store: String,
        @Field("links") links: String,
        @Field("additional_information") additional_information: String,
        @Field("about_us") about_us: String,
        @Field("countrycode") countrycode: String

    ): Call<ResponseBody>

    //30
    @FormUrlEncoded
    @POST(ApiConstants.API_END_POINT.CONTACT_US)
    fun contactUs(

        @Field("fullname") full_name: String,
        @Field("email") email: String,
        @Field("countrycode") country_code: String,
        @Field("phone") phone: String,
        @Field("comments") comments: String,
        @Field("date") date: String

    ): Call<ResponseBody>


    //31
    @GET(ApiConstants.API_END_POINT.FAQS_CATEGORY)
    fun getFaqCategories(

    ): Call<ResponseBody>

}