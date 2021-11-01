package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainFragment : Fragment(R.layout.fragment_main) {

    private var heroAdapter: HeroAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        heroAdapter = HeroAdapter(HeroRepository.heroes, Glide.with(this)) {
            showSelectedHero(it)
        }
        view.findViewById<RecyclerView>(R.id.rv_books).run {
            adapter = heroAdapter
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