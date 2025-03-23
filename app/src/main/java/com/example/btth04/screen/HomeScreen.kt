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
    val preferenceHelper = PreferenceHelper(LocalContext.current)
    val context = LocalContext.current
    // Biến lưu trữ thông tin tài khoản hiện tại trong SharedPreferences
    val account = preferenceHelper.GetAccount()
    var PuserName by remember { mutableStateOf(account.username) }
    var PpassWord by remember { mutableStateOf(account.password) }

    // Biến lưu trữ thông tin tài khoản được nhập vào ( 2 trường nhập EditText)
    var userName by remember { mutableStateOf("") }
    var passWord by remember { mutableStateOf("") }
    // thiết kế giao diện
    // Layout chính dạng cột
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // EditText tương ứng với userName
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
        // EditText tương ứng với passWord
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
        // 1 Row để chứa 3 button SAVE, LOAD, CLEAR
        Row(
            modifier = Modifier
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    // sự kiện click để lưu tài khoản vào SharedPreferences
                    onSaveClick(preferenceHelper, userName, passWord, context)
                    userName = ""
                    passWord = ""
                }
            ) {
                Text(text = "SAVE")
            }
            Button(
                onClick = {
                    // Load lại tài khoản từ SharedPreferences và cập nhật vào biến
                    PuserName = account.username
                    PpassWord = account.password
                }
            ) {
                Text(text = "LOAD")
            }
            Button(
                onClick = {
                    // sự kiện click để xóa tài khoản trong SharedPreferences
                    onClearClick(preferenceHelper)
                    PuserName = ""
                    PpassWord = ""
                }
            ) {
                Text(text = "CLEAR")
            }
        }
        // TextView hiển th thông tin tài khoản khi thực hiện LOAD
        Text(
            text = "User Name: $PuserName", // Hiển thị thông tin tài khoản ,
            // mọi thay đổi của PuserName đều sẽ làm thay đổi TextView này
            fontSize = 18.sp, // Tăng kích thước chữ
            fontWeight = FontWeight.Bold, // Làm đậm
            modifier = Modifier
                .padding(bottom = 8.dp), // Tạo khoảng cách dưới
        )
        Text(
            text = "Password: $PpassWord", // tương tự trên
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

    }
}

fun onSaveClick(preferenceHelper: PreferenceHelper , userName: String, passWord: String , context: Context){
    // kiểm tra nếu 2 trường có thông tin hay ko
    if(!userName.isEmpty() && !passWord.isEmpty()) {
        val account = Account(userName, passWord)
        preferenceHelper.SaveAccount(account) // Ghi thông tin tài khoản vào SharedPreferences ( ghi đè nếu đã có)
        Toast.makeText(context, "Save successfully", Toast.LENGTH_SHORT).show()
    }
    else{
        Toast.makeText(context, "Please fills all blanks", Toast.LENGTH_SHORT).show()
    }
}

fun onClearClick(preferenceHelper: PreferenceHelper){
    // Xóa tài khoản trong SharedPreferences
    preferenceHelper.ClearAccount()
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}