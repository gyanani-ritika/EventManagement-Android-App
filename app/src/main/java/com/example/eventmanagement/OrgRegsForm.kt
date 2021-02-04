
package com.example.eventmanagement

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class OrgRegsForm : AppCompatActivity() {
    private lateinit var firstName: EditText
    private lateinit var compname: EditText
    private lateinit var phoneNo: EditText
    private lateinit var password: EditText
    private lateinit var email: EditText

    private fun isValidEmail(target: CharSequence): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    private fun isValidPassword(password: String): Boolean {

        return !(password.indexOf("@") == -1 && password.indexOf("_") == -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_org_regs_form)

        firstName = findViewById(R.id.name)
        compname = findViewById(R.id.compname)
        phoneNo = findViewById(R.id.phnNo)
        password = findViewById(R.id.pswrd)
        email = findViewById(R.id.email)
        val register: Button = findViewById(R.id.reg)
        val login: Button = findViewById(R.id.login)
        login.setOnClickListener {
            val i = Intent(this@OrgRegsForm, OrganiserForm::class.java)
            startActivity(i)
        }

        register.setOnClickListener {
            if (validateForm()) {
                //proceed
                val user = UserEntity(firstName.text.toString(),
                        compname.text.toString(),
                        phoneNo.text.toString(),
                        email.text.toString(),
                        password.text.toString())
                val db = AppDatabase.getDatabase(application)
                val dao = db.userDao()
                dao.insert(user)
                Toast.makeText(this@OrgRegsForm,
                        "You Are Registered Successfully", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateForm(): Boolean {
        val firstNameStr = firstName.text.toString()
        if (firstNameStr.isEmpty()) {
            firstName.error = "Field cannot be empty"
            return false
        }
        val userNameStr = compname.text.toString()
        if (userNameStr.isEmpty()) {
            compname.error = "Field cannot be empty"
            return false
        } else if (userNameStr.length >30) {
            compname.error = "Username too long"
            return false
        }
            else if (userNameStr.length <4) {
            compname.error = "Username too short"
            return false
        }

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

        compname.error = null
        password.error = null
        phoneNo.error = null
        email.error = null
        firstName.error = null
        return true
    }





}