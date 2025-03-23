package com.example.btth04.helper

import android.content.Context
import com.example.btth04.model.Account

class PreferenceHelper(context: Context) {
    val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun SaveAccount(account: Account) {
        val editor = sharedPreferences.edit()
        editor.putString("username", account.username)
        editor.putString("password", account.password)
        editor.apply()
    }
    fun GetAccount(): Account {
        val username = sharedPreferences.getString("username", "")
        val password = sharedPreferences.getString("password", "")
        return Account(username!!,password!!)
    }
    fun ClearAccount() {
        val editor = sharedPreferences.edit()
        editor.remove("username") // Chỉ xoá username
        editor.remove("password") // Chỉ xoá password
        editor.apply()
    }
}
