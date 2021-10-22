package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.fragments.GalleryFragment
import com.example.myapplication.fragments.HomeFragment
import com.example.myapplication.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val fragmentContainer = R.id.fragment_container

        supportFragmentManager.beginTransaction()
            .add(fragmentContainer, HomeFragment())
            .commit()

        with(binding) {
            home.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                    .replace(fragmentContainer, HomeFragment())
                    .addToBackStack("homeFragment")
                    .commit()
            }

            gallery.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                    .replace(fragmentContainer, GalleryFragment())
                    .addToBackStack("galleryFragment")
                    .commit()
            }

            settings.setOnClickListener {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                    .replace(fragmentContainer, SettingsFragment())
                    .addToBackStack("settingsFragment")
                    .commit()
            }
        }
    }
}