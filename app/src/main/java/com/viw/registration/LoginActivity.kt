package com.viw.registration

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val radioGroup : RadioGroup = findViewById(R.id.radioGroup)
        var linearLayoutBtn1 : LinearLayout = findViewById(R.id.btn1)
        var linearLayoutBtn2 : LinearLayout = findViewById(R.id.btn2)

        fun isOptionSelected() : Boolean {
            return radioGroup.checkedRadioButtonId != -1
        }

        linearLayoutBtn1.setOnClickListener(){
            if(isOptionSelected()){
            var intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)

        }
            else{
                Toast.makeText(this,"Please select an option",Toast.LENGTH_SHORT).show()
            }
        }

        linearLayoutBtn2.setOnClickListener() {
            if (isOptionSelected()) {
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this,"Please select an option",Toast.LENGTH_SHORT).show()
            }
        }
    }
}