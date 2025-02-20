package com.viw.registration

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.viw.registration.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private  lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun isOptionSelected() : Boolean {
            return binding.radioGroup.checkedRadioButtonId != -1
        }

        binding.btn1.setOnClickListener(){
            if(isOptionSelected()){
            var intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)

        }
            else{
                Toast.makeText(this,"Please select an option",Toast.LENGTH_SHORT).show()
            }
        }

        binding.btn2.setOnClickListener() {
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