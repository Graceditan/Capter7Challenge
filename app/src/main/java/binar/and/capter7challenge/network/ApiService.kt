package binar.and.capter7challenge.network

import binar.and.capter7challenge.model.GetAllFilmItem
import binar.and.capter7challenge.model.GetAllUserItem
import binar.and.capter7challenge.model.ResponseLogin
import binar.and.capter7challenge.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    @GET("apifilm.php")
    suspend fun getAllFilm(): List<GetAllFilmItem>

    @GET("apiuser.php")
    fun getAllUser(): Call<List<GetAllUserItem>>


    @POST("updateuser.php")
    @FormUrlEncoded
    fun updateUser(
        @Field("id") id: Int,
        @Field("username") username: String,
        @Field("complete_name") completename: String,
        @Field("address") address: String,
        @Field("dateofbirth") dateofbirth: String,
    ): Call<List<GetAllUserItem>>


    @POST("register.php/")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,

        ): Call<ResponseRegister>

    @POST("login.php")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseLogin>
}