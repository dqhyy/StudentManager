package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog)

        val editHoten = findViewById<EditText>(R.id.edit_hoten)
        val editMssv = findViewById<EditText>(R.id.edit_mssv)
        val btnAddStudent = findViewById<Button>(R.id.button_ok)
        val btnCancel = findViewById<Button>(R.id.button_cancel)

        btnAddStudent.setOnClickListener {
            val hoten = editHoten.text.toString()
            val mssv = editMssv.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("studentName", hoten)
            resultIntent.putExtra("studentId", mssv)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}
