package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.db.Note
import com.example.noteapp.viewModel.NoteViewModel

class MainActivity : AppCompatActivity(),NoteAdapter.OnItemClickListener  {
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = NoteAdapter(this,this)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager= StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

       viewModel.allNotes.observe(this) { list ->
            list?.let {
                // on below line we are updating our list.
                adapter.updateList(it)
            }
        }

        binding.fab.setOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(note: Note) {
        // Handle item click, for example, open NoteActivity with the selected note
        val intent = Intent(this, NoteActivity::class.java)
        intent.putExtra("NOTE_ID", note.id) // Pass the note ID or any necessary data
        startActivity(intent)
    }
}