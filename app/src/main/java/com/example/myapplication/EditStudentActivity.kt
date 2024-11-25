package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private lateinit var student: StudentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog)

        val studentName = intent.getStringExtra("studentName")
        val studentId = intent.getStringExtra("studentId")

        if (studentName != null && studentId != null) {
            student = StudentModel(studentName, studentId)
        }

        val editHoten = findViewById<EditText>(R.id.edit_hoten)
        val editMssv = findViewById<EditText>(R.id.edit_mssv)
        val btnSave = findViewById<Button>(R.id.button_ok)
        val btnCancel = findViewById<Button>(R.id.button_cancel)

        editHoten.setText(student.studentName)
        editMssv.setText(student.studentId)

        btnSave.setOnClickListener {
            student.studentName = editHoten.text.toString()
            student.studentId = editMssv.text.toString()
            val resultIntent = Intent()
            resultIntent.putExtra("studentName", student.studentName)
            resultIntent.putExtra("studentId", student.studentId)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }
}
