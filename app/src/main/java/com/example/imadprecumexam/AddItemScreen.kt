package com.example.imadprecumexam

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddItemScreen : AppCompatActivity() {
    private val TAG = "AddItemActivity"


    private lateinit var etName: EditText

    private lateinit var etCategory: EditText

    private lateinit var etQuantity: EditText

    private lateinit var etComment: EditText

    private lateinit var btnSave: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_item_screen)

        etName = findViewById(R.id.etItemName)

        etCategory = findViewById(R.id.etCategory)

        etQuantity = findViewById(R.id.etQuantity)

        etComment = findViewById(R.id.etComment)

        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {

            saveItem()

            // This function is my ERROR HANDLING for the app, as required by the brief.

               // It checks the user's input BEFORE trying to save it, and shows a

          // helpful Toast message if something is wrong, instead of crashing.

            private fun saveItem() {

            // .trim() removes any accidental spaces the user typed before/after their text

                val name = etName.text.toString().trim()

                val category = etCategory.text.toString().trim()

                val quantityText = etQuantity.text.toString().trim()

                val comment = etComment.text.toString().trim()


             // Check 1: make sure the important fields are not empty

                if (name.isEmpty() || category.isEmpty() || quantityText.isEmpty()) {

                    Toast.makeText(this, "Please fill in Item Name, Category and Quantity", Toast.LENGTH_LONG).show()

                    Log.w(TAG, "Save blocked - required field was empty")

                    return // stop the function here - do not save anything

                }


                 // Check 2: make sure Quantity is actually a whole number.

                  // toIntOrNull() tries to convert the text to an Int - if the user

                  // typed letters instead of numbers, it safely returns null instead

                 // of crashing the whole app.

                val quantity = quantityText.toIntOrNull()

                if (quantity == null || quantity <= 0) {

                    Toast.makeText(this, "Quantity must be a whole number greater than 0", Toast.LENGTH_LONG).show()

                    Log.w(TAG, "Save blocked - quantity '$quantityText' is not a valid positive number")

                    return

                }


                 // If both checks pass, the comment is allowed to be empty - I just

                  // give it a default value so the Detailed View screen never shows a blank line.

                val finalComment = if (comment.isEmpty()) "No comment added" else comment


               // Add the new item into the parallel arrays in my singleton object

                GroceryData.addItem(name, category, quantity, finalComment)

                Log.i(TAG, "New item saved successfully: $name")


                Toast.makeText(this, "$name added to your list!", Toast.LENGTH_SHORT).show()


                finish() // close this screen and go back to MainActivity automatically

            }

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.etItemName)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}