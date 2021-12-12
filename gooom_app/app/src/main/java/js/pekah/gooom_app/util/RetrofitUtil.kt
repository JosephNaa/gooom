package js.pekah.gooom_app.util

import js.pekah.gooom_app.api.UserApi
import js.pekah.gooom_app.config.ApplicationClass

class RetrofitUtil {
    companion object {
        val userService = ApplicationClass.retrofit.create(UserApi::class.java)
    }
}