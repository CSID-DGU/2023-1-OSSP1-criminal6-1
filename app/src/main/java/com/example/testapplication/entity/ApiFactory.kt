
import com.example.testapplication.BuildConfig
import com.example.testapplication.service.CreateRoomService
import com.example.testapplication.service.RoomlistService
import com.example.testapplication.service.SearchRoomService
import com.example.testapplication.service.SignService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiFactory {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.CRIMINAL_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object CriminalServicePool {
    val signupService = ApiFactory.create<SignService>()
    val loginService = ApiFactory.create<SignService>()
    val createroomService = ApiFactory.create<CreateRoomService>()
    val searchroomService = ApiFactory.create<SearchRoomService>()
    val roomlistService = ApiFactory.create<RoomlistService>()
}


