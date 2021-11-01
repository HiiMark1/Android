package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.databinding.FragmentInfoBinding
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment(R.layout.fragment_info){

    private lateinit var binding: FragmentInfoBinding
    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        val heroes = HeroRepository.heroes
        val glide = Glide.with(this)
        for(h in heroes){
            if (h.id == arguments?.getInt("id")) {
                with(binding) {
                    name.text = "name: " + h.name
                    hp.text = "hp: " + h.hp
                    mana.text = "mana: " + h.mana
                    id.text = "id: " + h.id
                    glide.load(h.url)
                        .apply(options)
                        .into(photo)
                }
                break
            }
        }

    }

    private fun showSelectedTitle(title: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            "Title: $title",
            Snackbar.LENGTH_LONG
        ).show()
    }
}