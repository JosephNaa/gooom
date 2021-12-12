package js.pekah.gooom_app.api

import js.pekah.gooom_app.model.dto.User
import js.pekah.gooom_app.model.response.ApiResponse
import js.pekah.gooom_app.model.response.JwtAuthenticationResponse
import js.pekah.gooom_app.model.response.UserIdentityAvailability
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @POST("/api/auth/signup")
    suspend fun join(@Body body: User): Response<ApiResponse>

    @POST("/api/auth/signin")
    suspend fun login(@Body body: User): Response<JwtAuthenticationResponse>

    @GET("/api/users/checkIdAvailability")
    suspend fun isAvailable(@Query("id") id: String): Response<UserIdentityAvailability>

}