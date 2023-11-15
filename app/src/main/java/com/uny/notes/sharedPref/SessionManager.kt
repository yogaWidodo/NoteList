import android.content.Context

class SessionManager(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("my-preferences", Context.MODE_PRIVATE)

    fun saveUser(username: String) {
        val editor = sharedPreferences.edit()
        editor.putString("USERNAME_KEY", username)
        editor.apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString("USERNAME_KEY", null)
    }

    fun logout() {
        val editor = sharedPreferences.edit()
        editor.remove("USERNAME_KEY")
        editor.apply()
    }
}