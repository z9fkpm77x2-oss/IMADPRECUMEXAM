package com.example.imadprecumexam

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat

class DetailViewScreen : AppCompatActivity() {

    // This screen shows every grocery item that has been added, with its

// Name, Category, Quantity and Comments, using a ListView.

    class DetailedViewActivity(val setOnClickListener: Button.(() -> Unit?) -> Unit) : AppCompatActivity() {


        private val TAG = "DetailedViewActivity"


        private lateinit var listView: ListView

        private lateinit var btnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_view_screen)

        listView = findViewById(R.id.lvItems)

        btnBack = findViewById(R.id.btnBack)


     // Build my custom adapter (see ItemAdapter.kt) and attach it to the

    // ListView. The ListView then automatically loops through every

    // item and calls getView() in the adapter for each one.

        val adapter = ItemAdapter(this)

        listView.adapter = adapter


     // "Back" button navigates cleanly back to the Main Screen, exactly

    // as required in the brief, instead of relying only on the phone's


        btnBack.setOnClickListener {

            Log.d(TAG, "Back button clicked - returning to Main Screen")

            finish() // closes this screen and reveals MainActivity underneath it

        }

    }

    }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}