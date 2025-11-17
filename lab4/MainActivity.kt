package com.example.lab4

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // 接收 SecActivity 回傳結果
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { intent ->
                val drink = intent.getStringExtra("drink")
                val sugar = intent.getStringExtra("sugar")
                val ice = intent.getStringExtra("ice")

                binding.tvMeal.text = """
                    飲料：$drink
                    
                    甜度：$sugar
                    
                    冰塊：$ice
                """.trimIndent()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChoice.setOnClickListener {
            startForResult.launch(Intent(this, SecActivity::class.java))
        }
    }
}
