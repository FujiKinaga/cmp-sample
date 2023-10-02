package cmp.sample.shared.data.core.apiclient.users

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST

interface UsersApi {

  @POST("signin")
  suspend fun postSignIn(@Body body: SignInRequest): SignInResponse

  @GET("users/me")
  suspend fun getUsersMe(): UserResponse

  @DELETE("users")
  suspend fun deleteUsers()
}
