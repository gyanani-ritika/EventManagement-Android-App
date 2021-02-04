package com.example.eventmanagement

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login_org.*


class OrganiserForm : AppCompatActivity() {
    var email: EditText? = null
    var password:EditText? = null
    private var userDao: UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_org)

        email = findViewById(R.id.email_OL)
        password = findViewById(R.id.pswrd_OL)

        val db: AppDatabase = AppDatabase.getDatabase(application)
        userDao = db.userDao()

        val signUp : Button = findViewById(R.id.signup_OL)
        signUp.setOnClickListener {
            val i = Intent(this@OrganiserForm, OrgRegsForm::class.java)
            startActivity(i)
        }

        val login : Button = findViewById(R.id.login_OL)
        login.setOnClickListener {

            val email: String = email_OL.text.toString().trim()
            val password: String = pswrd_OL.text.toString().trim()
            val user: UserEntity? = db.userDao().getUser(email, password)

            if (user!= null){
                SharedPrefsForLogin.setUserLoggedIn(this, true)
                val i = Intent(this@OrganiserForm, DashBoardOrg::class.java)
                startActivity(i)
                }
            else{
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setMessage("User Not Registered")
                alertDialogBuilder.setPositiveButton("Register"){ _, _ ->
                    val i = Intent(this@OrganiserForm, OrgRegsForm::class.java)
                    startActivity(i)
                }
                alertDialogBuilder.setNegativeButton("Retry"){ _, _ ->
                    pswrd_OL.text?.clear()


                }
                val alertDialog: AlertDialog = alertDialogBuilder.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
            }
        }
    }

