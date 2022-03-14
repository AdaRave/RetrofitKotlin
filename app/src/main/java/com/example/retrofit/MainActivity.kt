package com.example.retrofit

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.retrofit.model.delete.DeleteGet
import com.example.retrofit.model.favorites.ImageGet
import com.example.retrofit.model.image.Favorites
import com.example.retrofit.model.image.FavoritesPost
import com.example.retrofit.retrofit.RetrofitInstance
import com.example.retrofit.ui.theme.RetrofitTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/*api:
https://documenter.getpostman.com/view/5578104/RWgqUxxh#ae1b5e8f-ca63-4de8-a715-f4944f4cec07*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetrofitTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Favorites()
                    //GetImage()
                    DelVotes()
                }
            }
        }
    }
}


/*будет работать один раз для каждого изображения и пользователя
т.к нельзя 2 раза добавить в избранное одно изображение
Для работы меняй или первый или второй параметр. Если меняешь первый, убедись, что такое изображение существует*/
fun Favorites() {
    val interfase = RetrofitInstance.api
    interfase.addFavorites(FavoritesPost("9ccXTANkb", "your-user-2"))//передаем значения на сервер
        .enqueue(object : Callback<Favorites> {
            override fun onResponse(call: Call<Favorites>, response: Response<Favorites>) {
                if (response.body() != null) { //проверяем тело на пустоту
                    Log.d(
                        "RETROFIT_TEST",
                        response.body()!!.message
                    ) //для проверки выводим в консоль
                }
            }

            override fun onFailure(call: Call<Favorites>, t: Throwable) {
                Log.d("RETROFIT_TEST", "ERROR $t") //для проверки получаем ошибку
            }

        })
}

fun GetImage() {
    val interfase = RetrofitInstance.api
    interfase.getImage("MjAzMDMwMg", "small").enqueue(object : Callback<ImageGet> {
        override fun onResponse(call: Call<ImageGet>, response: Response<ImageGet>) {
            if (response.body() != null) { //проверяем тело на пустоту
                Log.d(
                    "RETROFIT_TEST",
                    response.body()!!.toString()
                ) //для проверки выводим в консоль
            }
        }

        override fun onFailure(call: Call<ImageGet>, t: Throwable) {
            Log.d("RETROFIT_TEST", "ERROR $t") //для проверки получаем ошибку
        }

    })

}

fun DelVotes() {
    val interfase = RetrofitInstance.api
    interfase.delVote("511719") //значение взято из getVotes
        .enqueue(object : Callback<DeleteGet> {
            override fun onResponse(call: Call<DeleteGet>, response: Response<DeleteGet>) {
                if (response.body() != null) {
                    Log.d("RETROFIT_TEST", response.body()!!.toString())
                }
            }

            override fun onFailure(call: Call<DeleteGet>, t: Throwable) {

            }
        })
}

