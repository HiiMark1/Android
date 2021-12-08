package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment(R.layout.fragment_main) {

    private var songAdapter: SongAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songAdapter = SongAdapter(SongRepository.songsList) {
            showSelectedHero(it)
        }
        view.findViewById<RecyclerView>(R.id.rv_songs).run {
            adapter = songAdapter
        }
    }

    private fun showSelectedHero(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        val infoFragment = InfoFragment()
        infoFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, infoFragment)
            ?.addToBackStack("fragment")
            ?.commit()
    }
}