package com.example.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.admin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ubutton.setOnClickListener {
            val intent=Intent(this@MainActivity,UploadActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.upbutton.setOnClickListener {
            var intent=Intent(this@MainActivity,UpdateActivity2::class.java)
            startActivity(intent)
            finish()
        }
        binding.dbutton.setOnClickListener {
            var intent=Intent(this@MainActivity,DeleteActivity2::class.java)
            startActivity(intent)
            finish()
        }
    }
}