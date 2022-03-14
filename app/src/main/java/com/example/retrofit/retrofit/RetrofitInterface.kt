package com.example.retrofit.retrofit

import com.example.retrofit.model.delete.DeleteGet
import com.example.retrofit.model.favorites.ImageGet
import com.example.retrofit.model.image.Favorites
import com.example.retrofit.model.image.FavoritesPost
import com.example.retrofit.retrofit.Const.API_KEY
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {
    @Headers("x-api-key: $API_KEY") //в документации указано, что ключ является Header
    @POST("favourites")
    fun addFavorites(@Body post: FavoritesPost): Call<Favorites>

    @Headers("x-api-key: $API_KEY")
    @GET("images/{image_id}")
    fun getImage(
        @Path("image_id") image_id: String,
        @Query("size") size: String
    ): Call<ImageGet>
//в Query в данном случае можно отправить один параметр. Параметры в документации указаны необязательными


    /*
    Перед Delete самостоятельно сделать метод
    Post для Votes. Для получения vote_id можно воспользоваться Get Voes
     */
    @Headers("x-api-key: $API_KEY")
    @DELETE("votes/{vote_id}")
    fun delVote(@Path ("vote_id") vote_id: String) : Call<DeleteGet>

}