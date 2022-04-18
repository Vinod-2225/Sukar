import com.google.gson.annotations.SerializedName

data class CouponsResponse (

	@SerializedName("output") val output : CouponsOutput
)

data class CouponsOutput (

	@SerializedName("success") val success : Int,
	@SerializedName("info") val info : ArrayList<CouponsData>
)

data class CouponsData (
	@SerializedName("id")
	val id: String,
	@SerializedName("title")
	val title: String,
	@SerializedName("coupontype")
	val coupontype: String,
	@SerializedName("couponamount")
	val couponamount: String,
	@SerializedName("minamount")
	val minamount: String,
	@SerializedName("fromdate")
	val fromdate: String,
	@SerializedName("todate")
	val todate: String,
	@SerializedName("status")
	val status: String,
	@SerializedName("created_date")
	val createdDate: String
)