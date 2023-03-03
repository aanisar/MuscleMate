package com.lh1110642.gymgenie
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lh1110642.gymgenie.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

//        binding.registerbuttonregister.setOnClickListener {
//            val email = binding.emailedittextregister.text.toString()
//            val password = binding.passwordedittextregister.text.toString()
//
//            if (email.isEmpty() || password.isEmpty())
//            {
//                Toast.makeText(baseContext, "Please type your sign up info",
//                    Toast.LENGTH_SHORT).show()
//            }
//            else{
//                createAccount(email, password)
//            }
//
//        }
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
//
//    private fun createAccount(email: String, password: String) {
//        // [START create_user_with_email]
//        auth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success")
//                    val user = auth.currentUser
//                    updateUI(user)
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(baseContext, "Your email and password need to be in the proper format.",
//                        Toast.LENGTH_SHORT).show()
//                    binding.emailedittextregister.text.clear()
//                    binding.passwordedittextregister.text.clear()
//                }
//            }
//        // [END create_user_with_email]
//    }
//
//    private fun updateUI(user: FirebaseUser?) {
//        if (user!= null){
//            startActivity(Intent(this,BrowsingActivity::class.java))
//        }
//        else{
//            Toast.makeText(baseContext, "Your email and password need to be in the proper format.",
//                Toast.LENGTH_SHORT).show()
//            binding.emailedittextregister.text.clear()
//            binding.passwordedittextregister.text.clear()
//        }
//    }
//
//    companion object {
//        private const val TAG = "EmailPassword"
//    }
}