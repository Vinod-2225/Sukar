import com.google.gson.annotations.SerializedName




data class Categorylistresponse (

	@SerializedName("output") val output : CategoryOutput
)

data class CategoryOutput (

	@SerializedName("success") val success : Int,
	@SerializedName("data") val data : ArrayList<CategoryData>
)

data class CategoryData (

	@SerializedName("id") val id : Int,
	@SerializedName("shop_id") val shop_id : Int,
	@SerializedName("title_en") val title_en : String,
	@SerializedName("title_ar") val title_ar : String,
	@SerializedName("ordering") val ordering : Int,
	@SerializedName("image") val image : String,
	@SerializedName("status") val status : Int,
	@SerializedName("created_date") val created_date : String
)