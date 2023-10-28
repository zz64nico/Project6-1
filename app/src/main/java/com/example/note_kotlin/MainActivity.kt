package com.example.note_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,INoteListener{
    var binding: ActivityMainBinding? = null
    var  nodaAdapter : NoteAdapter? = null;
    var notesDao: NotesDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        notesDao = MainApplication.instance?.scoreDataBase?.scoreDao()
        nodaAdapter = NoteAdapter(this);
        binding?.rlNote?.layoutManager = LinearLayoutManager(this);
        binding?.rlNote?.addItemDecoration(MyItemDecoration(0)) ;
        binding?.rlNote?.adapter = nodaAdapter;
        var list:List<Notes>? = notesDao?.queryScoreList();
        nodaAdapter!!.setNewData(list as MutableList<Notes>?)
        nodaAdapter!!.notifyDataSetChanged()

    }
    fun addNotes(view: View?) {
        startActivity(Intent(this@MainActivity, AddNoteActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        var list:List<Notes>? = notesDao?.queryScoreList();
        nodaAdapter!!.setNewData(list as MutableList<Notes>?)
        nodaAdapter!!.notifyDataSetChanged()
    }
    override fun onNoteClick(notes: Notes?) {
        AlertDialog.Builder(this).apply {
            setTitle("hint")
            setMessage("delete?")
            setPositiveButton("ok") { dialog, _ ->
                //点击了确认按钮
                notesDao?.deleteWords(notes!!)
                var list:List<Notes>? = notesDao?.queryScoreList();
                nodaAdapter!!.setNewData(list as MutableList<Notes>?)
                nodaAdapter!!.notifyDataSetChanged()
                dialog.dismiss()
            }
            setNegativeButton("cancel") { dialog, _ ->
                //点击了取消按钮
                dialog.dismiss()
            }
            create()
            show()
        }
    }
}