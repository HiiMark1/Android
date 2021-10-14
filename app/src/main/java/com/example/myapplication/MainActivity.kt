package com.example.myapplication

import android.app.Instrumentation
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        with(binding) {
            button.setOnClickListener {
                val webpage: Uri =
                    Uri.parse("https://www.google.com/search?q=" + searchRequest.text.toString())
                val intent = Intent(Intent.ACTION_VIEW, webpage)

                try {
                    startActivityForResult(intent, 1)
                } catch (e: ActivityNotFoundException) {
                    Snackbar.make(binding.root, "error", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var textView4: TextView = findViewById(R.id.textView4)
        var num = textView4.text.toString().toInt()
        num++
        textView4.text = num.toString()
    }
}