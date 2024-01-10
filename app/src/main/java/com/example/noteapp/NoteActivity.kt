package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.noteapp.databinding.ActivityNoteBinding
import com.example.noteapp.db.Note
import com.example.noteapp.viewModel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.Date

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel= ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        binding.buttonSave.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = binding.editTextNoteTitle.text.toString()
        val content = binding.editTextNote.text.toString()

        if(title.isNotEmpty() && content.isNotEmpty()){
            val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
            val currentDateAndTime: String = sdf.format(Date())
            val newNote = Note(title=title,content =content, date = currentDateAndTime )

            viewModel.insert(newNote)
            finish()

        }else{
            Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show()

        }
    }
}