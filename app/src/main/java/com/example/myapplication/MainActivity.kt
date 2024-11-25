package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

  private val students = mutableListOf(
    StudentModel("Le Thi A", "SV001"),
    StudentModel("Nguyen Van B", "SV002"),
    StudentModel("Le Van C", "SV003"),
    StudentModel("Ngo Ba D", "SV004"),
    StudentModel("Duong Minh T", "SV005"),
    StudentModel("Tran Xuan H", "SV006"),
  )

  private lateinit var studentAdapter: StudentAdapter
  private var selectedStudentPosition: Int = -1

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    studentAdapter = StudentAdapter(students) { position ->
      selectedStudentPosition = position
      openContextMenu(findViewById<RecyclerView>(R.id.recycler_view_students))
    }

    findViewById<RecyclerView>(R.id.recycler_view_students).run {
      adapter = studentAdapter
      layoutManager = LinearLayoutManager(this@MainActivity)
      registerForContextMenu(this)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.option_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_add_new -> {
        val intent = Intent(this, AddStudentActivity::class.java)
        startActivityForResult(intent, ADD_STUDENT_REQUEST_CODE)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
    super.onCreateContextMenu(menu, v, menuInfo)
    menuInflater.inflate(R.menu.context_menu, menu)
  }

  override fun onContextItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.context_edit -> {
        val intent = Intent(this, EditStudentActivity::class.java)
        intent.putExtra("studentName", students[selectedStudentPosition].studentName)
        intent.putExtra("studentId", students[selectedStudentPosition].studentId)
        startActivityForResult(intent, EDIT_STUDENT_REQUEST_CODE)
        true
      }
      R.id.context_delete -> {
        students.removeAt(selectedStudentPosition)
        studentAdapter.notifyDataSetChanged()
        true
      }
      else -> super.onContextItemSelected(item)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == EDIT_STUDENT_REQUEST_CODE && resultCode == RESULT_OK) {
      val studentName = data?.getStringExtra("studentName");
      val studentId = data?.getStringExtra("studentId")
      if (studentName != null && studentId != null && selectedStudentPosition != -1) {
        students[selectedStudentPosition] = StudentModel(studentName, studentId)
        studentAdapter.notifyDataSetChanged()
      }
    } else if (requestCode == ADD_STUDENT_REQUEST_CODE && resultCode == RESULT_OK) {
      val studentName = data?.getStringExtra("studentName");
      val studentId = data?.getStringExtra("studentId")
      if (studentName != null && studentId != null) {
        students.add(StudentModel(studentName, studentId))
        studentAdapter.notifyDataSetChanged()
      }
    }
  }

  companion object {
    const val ADD_STUDENT_REQUEST_CODE = 1
    const val EDIT_STUDENT_REQUEST_CODE = 2
  }
}
