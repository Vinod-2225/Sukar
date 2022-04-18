package com.designmaster.sukar.models

import com.google.gson.annotations.SerializedName

data class ProductsByCategoryResponse (
    @SerializedName("output")
    val output: ProductsByCategory
)

data class ProductsByCategory (
    @SerializedName("success")
    val success: Int,
    @SerializedName("info")
    val info: ArrayList<ProductsData>
)

data class ProductsData (
    @SerializedName("id")
    val id: String,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("sub_category_id")
    val subCategoryId: String,
    @SerializedName("store_category_id")
    val storeCategoryId: String,
    @SerializedName("size_id")
    val sizeId: String,
    @SerializedName("flavour_id")
    val flavourId: String,
    @SerializedName("shop_id")
    val shopId: String,
    @SerializedName("title_en")
    val titleEn: String,
    @SerializedName("title_ar")
    val titleAr: String,
    @SerializedName("content_en")
    val contentEn: String,
    @SerializedName("content_ar")
    val contentAr: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("extra_size")
    val extraSize: String,
    @SerializedName("extra_flavour")
    val extraFlavour: String,
    @SerializedName("extra_charge")
    val extraCharge: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("preparation_time")
    val preparationTime: String,
    @SerializedName("ordering")
    val ordering: String,
    @SerializedName("thumbimg1")
    val thumbimg1: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("created_date")
    val createdDate: String
)