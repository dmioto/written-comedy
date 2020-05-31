package br.com.comedy.writtencomedy.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import br.com.comedy.writtencomedy.MainActivity
import br.com.comedy.writtencomedy.R
import br.com.comedy.writtencomedy.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animateConfirmation()
    }

    private fun openLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun animateConfirmation(){

        AnimationUtils.loadAnimation(this, R.anim.bounce_animation).run {
            findViewById<ImageView>(R.id.image_to_anim).startAnimation(this)

            this.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {

                }
                override fun onAnimationEnd(animation: Animation) {
                    openLogin()
                }
                override fun onAnimationRepeat(animation: Animation) {

                }
            })
        }
    }

}
