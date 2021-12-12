package js.pekah.gooom_app.util

import android.content.Context
import android.content.SharedPreferences
import js.pekah.gooom_app.model.dto.User

class SharedPreferencesUtil(context: Context) {
    val SHARED_PREFERENCES_NAME = "gooom_preference"

    var prefences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun addUser(user: User) {
        val editor = prefences.edit()
        editor.putString("id", user.id)
        editor.apply()
    }

    fun getUser(): User {
        val id = prefences.getString("id", "")
        if (id != "") {
            return User(id!!, "")
        } else {
            return User()
        }
    }

    fun deleteUser() {
        val editor = prefences.edit()
        editor.clear()
        editor.apply()
    }


}