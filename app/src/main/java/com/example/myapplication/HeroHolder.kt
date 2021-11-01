package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.databinding.ItemHeroBinding

class HeroHolder (
    private val binding: ItemHeroBinding,
    private val glide: RequestManager,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var hero: Hero? = null

    init {
        // use only one [setOnClickListener]
        itemView.setOnClickListener {
            hero?.id?.also(action)
        }
    }

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: Hero) {
        this.hero = item
        with(binding) {
            name.text = item.name
            hp.text = item.hp.toString()
            mana.text = item.mana.toString()

            glide.load(item.url)
                .apply(options)
                .into(photo)
        }


        // use only one [setOnClickListener]
        itemView.setOnClickListener {
            action(item.id)
        }
    }

    companion object {
        fun create (
            parent: ViewGroup,
            glide: RequestManager,
            action : (Int) -> Unit
        ) = HeroHolder (
            ItemHeroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), glide, action
        )
    }
}
