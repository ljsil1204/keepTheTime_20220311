package com.example.keepthetime_20220311.api

import com.example.keepthetime_20220311.datas.BasicResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface APIList {

//    BASE_URL에 해당하는 서버에서, 어떤 기능들을 사용할건지 명시.

    @FormUrlEncoded // 파라미터 중에, Field (formData) 에 담아야 하는 파라미터가 있다.
    @POST("/user")
    fun postRequestLogin (
        @Field("email") email : String,
        @Field("password") pw : String
    ) : Call<BasicResponse> // 서버가 주는 응답을 (성공시에) BasicResponse 형태로 자동 파싱


    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email : String,
        @Field("password") pw : String,
        @Field("nick_name") nick : String,
    ) : Call<BasicResponse>

    @GET("/user")
    fun getRequestMyInfo() : Call<BasicResponse>


    @GET("/user/check")
    fun getRequestDuplicatedCheck(
        @Query("type") type : String,
        @Query("value") value : String,
    ) : Call<BasicResponse>


    @GET("/user/friend")
    fun getrequestFriendList(
        @Query("type") type : String, // all my, requested  세 문구 외에는 넣지 말자.
    ) : Call<BasicResponse>

    @GET("/search/user")
    fun getRequestSearchUser(
        @Query("nickname") nickname : String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user/friend")
    fun postRequestAddFriend(
        @Field("user_id") userId : Int,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PUT("/user/friend")
    fun putRequestAcceptOrDenyFriendRequest(
        @Field("user_id") userId : Int,
        @Field("type") type : String,
    ) : Call<BasicResponse>


    @FormUrlEncoded
    @POST("/appointment")
    fun postRequestAppointment(
        @Field("title") title : String,
        @Field("datetime") datetime : String,
        @Field("start_place")  startPlaceName : String,
        @Field("start_latitude") startLat : Double,
        @Field("start_longitude") startLog : Double,
        @Field("place") place : String,
        @Field("latitude") lat : Double,
        @Field("longitude") lng : Double,
    ) : Call<BasicResponse>


    @GET("/appointment")
    fun getRequestAppointmentList() : Call<BasicResponse>

    @GET("/user/place")
    fun getRequestPlaceList() : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/user/place")
    fun postRequestAddMyPlace(
        @Field("name") name : String,
        @Field("latitude") lat : Double,
        @Field("longitude") lng : Double,
        @Field("is_primary") isPrimary : Boolean,
    ) : Call<BasicResponse>


    @FormUrlEncoded
    @POST("/user/social")
    fun postRequestSocialLogin (
        @Field("provider") provider : String,
        @Field("uid") uid : String,
        @Field("nick_name") nickname : String,
    ) : Call<BasicResponse>

}