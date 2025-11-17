package com.example.lab4

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4.databinding.ActivitySecBinding

class SecActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener { sendResult() }
    }

    private fun sendResult() {
        val drink = binding.edDrink.text.toString().trim()

        if (drink.isEmpty()) {
            Toast.makeText(this, "請輸入飲料名稱", Toast.LENGTH_SHORT).show()
            return
        }

        val sugar = binding.rgSugar.getCheckedText()
        val ice = binding.rgIce.getCheckedText()

        val intent = Intent().apply {
            putExtra("drink", drink)
            putExtra("sugar", sugar)
            putExtra("ice", ice)
        }

        setResult(RESULT_OK, intent)
        finish()
    }

    // 🔧 Extension：讓 RadioGroup 取得已選項文字更簡潔
    private fun androidx.appcompat.widget.LinearLayoutCompat.getCheckedText(): String {
        val id = (this as android.widget.RadioGroup).checkedRadioButtonId
        return findViewById<RadioButton>(id).text.toString()
    }
}
