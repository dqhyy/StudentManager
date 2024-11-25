package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
  private val students: List<StudentModel>,
  private val onItemLongClick: (Int) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item, parent, false)
    return StudentViewHolder(view)
  }

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]
    holder.bind(student)
    holder.itemView.setOnLongClickListener {
      onItemLongClick(position)
      true
    }
  }

  override fun getItemCount(): Int = students.size

  class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.text_student_name)
    private val mssvTextView: TextView = itemView.findViewById(R.id.text_student_id)

    fun bind(student: StudentModel) {
      nameTextView.text = student.studentName
      mssvTextView.text = student.studentId
    }
  }
}
