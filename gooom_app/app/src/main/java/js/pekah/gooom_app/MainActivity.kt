package js.pekah.gooom_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import js.pekah.gooom_app.databinding.ActivityMainBinding
import js.pekah.gooom_app.ui.base.BaseActivity

private const val TAG = "MainActivity_gooom"
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var bottomNavigation: BottomNavigationView
    var fragment: Fragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}