package com.example.noteapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.R
import com.example.noteapp.db.Note
import kotlin.random.Random

class NoteAdapter(val context: Context):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val notes= ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewContent: TextView = itemView.findViewById(R.id.textViewContent)
        val textViewTitle: TextView = itemView.findViewById(R.id.titel)
        val textViewDate: TextView = itemView.findViewById(R.id.textDate)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_card, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        val currentNote = notes[position]
        holder.textViewContent.text = currentNote.content
        holder.textViewTitle.text= currentNote.title
        holder.textViewDate.text= currentNote.date

        holder.cardView.setCardBackgroundColor(holder.itemView.resources.getColor(getRandomColor(),null))
    }


    override fun getItemCount(): Int {
        return notes.size
    }
     fun getRandomColor(): Int {
         val listColor= ArrayList<Int>()
         listColor.add(R.color.note1)
         listColor.add(R.color.note2)
         listColor.add(R.color.note3)
         listColor.add(R.color.note4)
         listColor.add(R.color.note5)
         listColor.add(R.color.note6)
         listColor.add(R.color.note7)
        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(listColor.size)
         return listColor[randomIndex]


    }


    fun updateList(newNote: List<Note>) {
        notes.clear()
        notes.addAll(newNote)

        notifyDataSetChanged()

    }


}