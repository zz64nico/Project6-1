package com.example.note_kotlin

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.note_kotlin.DateUtil.getCurrentDate
import com.example.note_kotlin.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    var binding: ActivityAddNoteBinding? = null
    var notesDao: NotesDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
                notesDao = MainApplication.instance?.scoreDataBase?.scoreDao()
    }

    fun save(view: View?) {
        val title = binding!!.etTitle.text.toString()
        val description = binding!!.etDescription.text.toString()
        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            Toast.makeText(applicationContext, "please input", Toast.LENGTH_SHORT).show()
            return
        }
        val notes = Notes()
        notes.des = description
        notes.title = title
        notes.time = getCurrentDate(DateUtil.FORMAT)
        notesDao!!.insertScore(notes)
        Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
        finish()
    }
}