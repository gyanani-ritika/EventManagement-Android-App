package com.example.eventmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText

class AtndRegForm : AppCompatActivity() {

    private lateinit var firstName: EditText
    private lateinit var usrname: EditText
    private lateinit var phoneNo: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText
    fun isValidEmail(target: CharSequence): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun isValidPassword(password: String): Boolean {

        return !(password.indexOf("@") == -1 && password.indexOf("_") == -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atnd_reg)
        firstName = findViewById(R.id.name_A)
         usrname= findViewById(R.id.usrname_A)
        phoneNo = findViewById(R.id.phnN__A)
        password = findViewById(R.id.pswrd_A)
        email = findViewById(R.id.email_A)
        val register: Button = findViewById(R.id.reg_A)
        val login: Button = findViewById(R.id.login_A)
        login.setOnClickListener(View.OnClickListener {
            val i = Intent(this@AtndRegForm, Attendeeform::class.java)
            startActivity(i)
        })

        /*login.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {

            }
        })*/
        register.setOnClickListener {
            if (validateForm()) {
                //proceed
            }
        }
    }

    private fun validateForm(): Boolean {
        val firstNameStr = firstName.text.toString()
        if (firstNameStr.isEmpty()) {
            firstName.error = "Field cannot be empty"
            return false
        }
        val userNameStr = usrname.text.toString()
        if (userNameStr.isEmpty()) {
            usrname.error = "Field cannot be empty"
            return false
        } else if (userNameStr.length  !in 4..15) {
            usrname.error = "Username too long"
            return false
        }
//         else if (userNameStr.matches) {
//            usrname.setError("White Spaces are not allowed")
//            false

        val emailStr = email.text.toString()
        if (emailStr.isEmpty()) {
            email.error = "Field cannot be empty"
            return false
        } else if (!isValidEmail(emailStr)) {
            email.error = "Invalid email address"
            return false
        }
        val phnNoStr = phoneNo.text.toString()
        if (phnNoStr.isEmpty()) {
            phoneNo.error = "Field cannot be empty"
            return false
        }
        val passStr = password.text.toString()
        if (passStr.isEmpty()) {
            password.error = "Field cannot be empty"
            return false
        }
        else if(!isValidPassword(passStr)){
            password.error = "Must contain @ or _ symbol"
            return false
        }
        else if (passStr.length !in 4..15) {
            password.error = "Invalid Password"
            return false
        }

        usrname.error = null
        password.error = null
        phoneNo.error = null
        email.error = null
        firstName.error = null
        return true
    }
}

