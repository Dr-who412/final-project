package com.example.umum

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity(),TextWatcher {
    lateinit var email:EditText
    lateinit var pass:EditText
    lateinit var btn:Button
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initall()

        checkStart()

        login()

    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(pass.text.isNotEmpty()&&email.text.isNotEmpty())btn.isEnabled=true
    }

    override fun afterTextChanged(s: Editable?) {

    }
    fun initall (){
        email=findViewById(R.id.editTextTextEmailAddress2)
        pass=findViewById(R.id.editTextNumberPassword)
        btn=findViewById(R.id.textView3)
        sharedPreferences=getSharedPreferences("name",0)

    }
    fun login(){
        email.addTextChangedListener(this)
        pass.addTextChangedListener(this)
        btn.setOnClickListener {
            if(email.text.toString()=="appssquare@gmail.org"&&pass.text.toString()=="123456789"&& Patterns.EMAIL_ADDRESS.matcher(
                    email.text.toString()).matches()) {
                var edit : SharedPreferences.Editor = sharedPreferences.edit()
                edit.putBoolean("bool",true)
                edit.commit()
                var intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else
                Toast.makeText(this,"Email or password wrong!", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkStart(){
        var startWithRestaurantList = sharedPreferences.getBoolean("bool",false)
        if (startWithRestaurantList){
            var moveToRestaurantList = Intent(this,MainActivity2::class.java)
            startActivity(moveToRestaurantList)
            finish()
        }
    }
}

