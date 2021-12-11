package js.pekah.gooom_app.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import js.pekah.gooom_app.MainActivity
import js.pekah.gooom_app.R
import js.pekah.gooom_app.databinding.ActivityLoginBinding
import js.pekah.gooom_app.ui.base.BaseActivity
import js.pekah.gooom_app.ui.login.fragment.JoinFragment
import js.pekah.gooom_app.ui.login.fragment.LoginFragment

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 가장 첫 화면은 홈 화면의 Fragment로 지정
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_login, LoginFragment())
            .commit()
    }

    fun openFragment(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent)
            }
            2 -> transaction.replace(R.id.frame_layout_login, JoinFragment())
                .addToBackStack(null)

            3 -> transaction.replace(R.id.frame_layout_login, LoginFragment())
                .addToBackStack(null)
        }
        transaction.commit()
    }

}