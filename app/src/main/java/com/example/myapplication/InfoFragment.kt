package com.example.myapplication

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentInfoBinding

class InfoFragment : Fragment(R.layout.fragment_info){

    private lateinit var binding: FragmentInfoBinding

    private var binder: MusicService.LocaleBinder? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            binder = service as? MusicService.LocaleBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        activity?.bindService(
            Intent(this.context, MusicService::class.java),
            connection,
            BIND_AUTO_CREATE
        )

        var song: Song? = null
        val songs = SongRepository.songsList
        for (s in songs) {
            if (s.id == arguments?.getInt("id")) {
                song = s
                break
            }
        }
        if(song!=null){
            with(binding) {
                name.text = song.name
                groupName.text = song.groupName
                photo.setImageResource(song.photo)
                id.text=song.id.toString()
                description.text = song.description

                if (binder?.getSong() != song) {
                    binder?.play(song.music)
                    binder?.setCurrentSong(song)
                } else if (binder?.isPlaying() == false) {
                    playBtn.visibility = View.VISIBLE
                    pauseBtn.visibility = View.INVISIBLE
                }

                playBtn.setOnClickListener {
                    if (song == binder?.getSong())
                        binder?.unpause()
                    else
                        binder?.setCurrentSong(song)
                    binder?.play(song.music)
                    playBtn.visibility = View.INVISIBLE
                    pauseBtn.visibility = View.VISIBLE
                }
                pauseBtn.setOnClickListener {
                    binder?.pause()
                    playBtn.visibility = View.VISIBLE
                    pauseBtn.visibility = View.INVISIBLE
                }
                stopBtn.setOnClickListener {
                    binder?.stop()
                    binder?.setCurrentSong(null)
                    playBtn.visibility = View.VISIBLE
                    pauseBtn.visibility = View.INVISIBLE
                }
                prevBtn.setOnClickListener {
                    val bundle = Bundle()
                    val id: Int = if(song.id==1){
                        7
                    } else {
                        song.id - 1
                    }
                    bundle.putInt("id", id)
                    val infoFragment = InfoFragment()
                    infoFragment.arguments = bundle
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.container, infoFragment)
                        ?.commit()
                }
                nextBtn.setOnClickListener {
                    val bundle = Bundle()
                    val id: Int = if(song.id==7){
                        1
                    } else {
                        song.id + 1
                    }
                    bundle.putInt("id", id)
                    val infoFragment = InfoFragment()
                    infoFragment.arguments = bundle
                    activity?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.container, infoFragment)
                        ?.commit()
                }
            }
        }
    }
}