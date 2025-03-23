package com.example.btth04.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btth04.helper.PreferenceHelper
import com.example.btth04.model.Account

@Composable
fun HomeScreen(){
    val preferenceHelper : PreferenceHelper = PreferenceHelper(LocalContext.current)

    val context = LocalContext.current
    val account = preferenceHelper.GetAccount()
    var PuserName by remember { mutableStateOf(account.username) }
    var PpassWord by remember { mutableStateOf(account.password) }

    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = {userName = it},
            label = { Text(text = "User Name") },
            placeholder = {
                Text("Enter your user name")
            },
            modifier = Modifier
                .padding(top =  30.dp)
        )
        OutlinedTextField(
            value = passWord,
            onValueChange = {passWord = it},
            label = { Text(text = "Password") },
            placeholder = {
                Text("Enter your password")
            },
            modifier = Modifier
                .padding(top =  30.dp)
        )

        Row(
            modifier = Modifier
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    onSaveClick(preferenceHelper, userName, passWord, context)
                    userName = ""
                    passWord = ""
                }
            ) {
                Text(text = "SAVE")
            }
            Button(
                onClick = {
                    PuserName = account.username
                    PpassWord = account.password
                }
            ) {
                Text(text = "LOAD")
            }
            Button(
                onClick = {
                    onClearClick(preferenceHelper)
                    PuserName = ""
                    PpassWord = ""
                }
            ) {
                Text(text = "CLEAR")
            }
        }

        Text(
            text = "User Name: $PuserName",
            fontSize = 18.sp, // Tăng kích thước chữ
            fontWeight = FontWeight.Bold, // Làm đậm
            modifier = Modifier
                .padding(bottom = 8.dp), // Tạo khoảng cách dưới
        )
        Text(
            text = "Password: $PpassWord",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

    }
}

fun onSaveClick(preferenceHelper: PreferenceHelper , userName: String, passWord: String , context: Context){

    if(!userName.isEmpty() && !passWord.isEmpty()) {
        val account = Account(userName, passWord)
        preferenceHelper.SaveAccount(account)
        Toast.makeText(context, "Save successfully", Toast.LENGTH_SHORT).show()
    }
    else{
        Toast.makeText(context, "Please fills all blanks", Toast.LENGTH_SHORT).show()
    }
}

fun onClearClick(preferenceHelper: PreferenceHelper){
    preferenceHelper.ClearAccount()
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}