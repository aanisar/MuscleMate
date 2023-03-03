package com.lh1110642.gymgenie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.firebase.ui.auth.AuthUI
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                startActivity(Intent(this,LoginActivity::class.java))
            }
        return true

        return super.onOptionsItemSelected(item)
    }
}