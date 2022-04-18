import com.google.gson.annotations.SerializedName




data class Flavoursresponse (

	@SerializedName("output") val output : FlavoursOutput
)
data class FlavoursOutput (

	@SerializedName("success") val success : Int,
	@SerializedName("info") val data : ArrayList<FlavoursData>
)
data class FlavoursData (

	@SerializedName("id") val id : Int,
	@SerializedName("title_en") val title_en : String,
	@SerializedName("title_ar") val title_ar : String,
	@SerializedName("extra_charge") val extra_charge : Double,
	@SerializedName("extra_size") val extra_size : String,
	@SerializedName("ordering") val ordering : Int,
	@SerializedName("status") val status : Int,
	@SerializedName("created_date") val created_date : String

)