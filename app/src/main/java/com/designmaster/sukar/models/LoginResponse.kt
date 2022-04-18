import com.google.gson.annotations.SerializedName




data class LoginResponse (

	@SerializedName("output") val output : LoginOutput
)
data class LoginOutput (

	@SerializedName("success") val success : Int,
	@SerializedName("data") val data : List<Data>
)
data class Data (

	@SerializedName("id") val id : Int,
	@SerializedName("fname") val fname : String,
	@SerializedName("lname") val lname : String,
	@SerializedName("phone") val phone : Int,
	@SerializedName("email") val email : String,
	@SerializedName("username") val username : String,
	@SerializedName("secretcode") val secretcode : Int,
	@SerializedName("password") val password : String,
	@SerializedName("gender") val gender : String,
	@SerializedName("countrycode") val countrycode : Int,
	@SerializedName("language") val language : String,
	@SerializedName("date") val date : String,
	@SerializedName("status") val status : Int
)