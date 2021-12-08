package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemSongBinding

class SongHolder (
    private val binding: ItemSongBinding,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var song: Song? = null

    init {
        // use only one [setOnClickListener]
        itemView.setOnClickListener {
            song?.id?.also(action)
        }
    }

    fun bind(item: Song) {
        this.song = item
        with(binding) {
            id.text = item.id.toString()
            name.text = item.name
            groupName.text = item.groupName
            description.text = "Описание: " + item.description
            photo.setImageResource(item.photo)
        }


        // use only one [setOnClickListener]
        itemView.setOnClickListener {
            action(item.id)
        }
    }

    companion object {
        fun create (
            parent: ViewGroup,
            action : (Int) -> Unit
        ) = SongHolder (
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action
        )
    }
}