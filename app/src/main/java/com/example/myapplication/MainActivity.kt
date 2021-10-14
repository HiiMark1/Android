package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit_btn = findViewById<Button>(R.id.EditButton)
        val name = findViewById<TextView>(R.id.NameAndSurname)
        val editName = findViewById<EditText>(R.id.NameAndSurnameEditText)

        edit_btn.setOnClickListener{
            if(edit_btn.text=="Редактировать"){
                name.visibility= View.INVISIBLE
                editName.visibility = View.VISIBLE
                edit_btn.text = "Сохранить"
            } else {
                name.text = editName.text.toString()
                editName.visibility = View.INVISIBLE
                name.visibility = View.VISIBLE
                edit_btn.text = "Редактировать"
            }
        }
    }
}