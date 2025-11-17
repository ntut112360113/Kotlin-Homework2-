package com.example.lab6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab6.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val items = arrayOf("選項 1", "選項 2", "選項 3", "選項 4", "選項 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 處理系統欄位 Padding
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnToast.setOnClickListener { showToast("預設 Toast") }

        binding.btnSnackBar.setOnClickListener {
            Snackbar.make(it, "按鈕式 Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("按鈕") { showToast("已回應") }
                .show()
        }

        binding.btnDialog1.setOnClickListener {
            showButtonAlertDialog()
        }

        binding.btnDialog2.setOnClickListener {
            showListAlertDialog()
        }

        binding.btnDialog3.setOnClickListener {
            showSingleChoiceDialog()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun showButtonAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("按鈕式 AlertDialog")
            .setMessage("AlertDialog 內容")
            .setNeutralButton("左按鈕") { _, _ -> showToast("左按鈕") }
            .setNegativeButton("中按鈕") { _, _ -> showToast("中按鈕") }
            .setPositiveButton("右按鈕") { _, _ -> showToast("右按鈕") }
            .show()
    }

    private fun showListAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("列表式 AlertDialog")
            .setItems(items) { _, i -> showToast("你選的是${items[i]}") }
            .show()
    }

    private fun showSingleChoiceDialog() {
        var selectedIndex = 0
        AlertDialog.Builder(this)
            .setTitle("單選式 AlertDialog")
            .setSingleChoiceItems(items, 0) { _, i -> selectedIndex = i }
            .setPositiveButton("確定") { _, _ -> showToast("你選的是${items[selectedIndex]}") }
            .show()
    }
}
