package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lh1110642.gymgenie.databinding.ActivityOtherFiltersBinding

class OtherFiltersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtherFiltersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtherFiltersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swap.setOnClickListener{
            startActivity(Intent(this, BrowsingActivity::class.java))
        }
    }
}