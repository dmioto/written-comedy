package br.com.comedy.writtencomedy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import br.com.church.gnf.device_check.InternetCheck
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_content.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        when {
            isLoggedIn() -> openMainActivity()
            else -> setLoginButton()
        }
    }

    private fun isLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }


    private fun setLoginButton() {
        btn_login.setOnClickListener {
            if (canLogIn()) {
                loginUser(et_username.text.toString() + "@gmail.com", et_password.text.toString())
            }
        }
    }


    private fun loginUser(user: String, password: String) {
        mAuth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this) { task ->
            if (task.isComplete) handleAuthResponse(task)
        }
    }


    private fun canLogIn(): Boolean {
        return if (InternetCheck().isOnline(this)) {
            fieldAreValid(et_username) && fieldAreValid(et_password)
        } else {
            et_username.error = getString(R.string.no_connection_error)
            false
        }

    }


    private fun fieldAreValid(editText: EditText?): Boolean {
        editText?.text.run {
            return if (this != null && this.isNotEmpty()) {
                true
            } else {
                editText?.error = getString(R.string.fill_this_field_error)
                false
            }
        }
    }


    private fun handleAuthResponse(task: Task<AuthResult>) {
        when {
            task.isSuccessful -> { openMainActivity() }
            else -> { et_password.error = getString(R.string.username_or_password_invalid_error) }
        }
    }


    private fun openMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
