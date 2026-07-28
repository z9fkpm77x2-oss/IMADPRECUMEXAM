package com.example.imadprecumexam

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Mainscreen : AppCompatActivity() {
    private val  TAG = "AddItem"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_mainscreen)

        var total = findViewById<TextView>(R.id.textView2)
        var addItem = findViewById<Button>(R.id.btnAddtem)
        var viewList = findViewById<Button>(R.id.btnSave)
        updateTotal() //  show the correct total

        addItem.setOnClickListener {
            Log.d(TAG,"View List Button")
            val intent = Intent (this, AddItemScreen :: class.java)
            startActivity(intent)
            viewList.setOnClickListener {
                Log.d(TAG,"View List Button Clicked ")
                val intent = Intent (this, DetailViewScreen:: class.java)
                startActivity(intent)
            }
        }
        override fun onResume() {
            super.onResume()
            updateTotal()
        }
        private fun updateTotal()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.etItemName)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}