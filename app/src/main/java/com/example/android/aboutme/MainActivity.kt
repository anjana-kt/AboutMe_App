package com.example.android.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.android.aboutme.databinding.ActivityMainBinding


    class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding

        private val myName: MyName = MyName("Covid 19")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

            binding.myName = myName

            binding.doneButton.setOnClickListener {
                addNickname(it)
            }
            findViewById<TextView>(R.id.nickname_text).setOnClickListener {
                updateNickname(it)
            }
        }


        /**
     * Click handler for the DONE button.
     * Hides the EditText and the DONE button.
     * Sets the EditText content to the TextView and displays it.
     */
    private fun addNickname(view: View) {
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()

            nicknameText.text = binding.nicknameEdit.text
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }
        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }

    /**
     * Click handler for the nickname TextView.
     * Displays the EditText and the DONE button.
     * Hides the nickname TextView.
     */
    private fun updateNickname(view: View) {
        val editText = findViewById<EditText>(R.id.nickname_edit)
        val doneButton = findViewById<Button>(R.id.done_button)

        editText.visibility = View.VISIBLE
        doneButton.visibility = View.VISIBLE
        view.visibility = View.GONE

        // Set the focus to the edit text.
        editText.requestFocus()

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }
}
