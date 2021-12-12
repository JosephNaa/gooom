package js.pekah.gooom_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import js.pekah.gooom_app.databinding.ActivityMainBinding
import js.pekah.gooom_app.ui.base.BaseActivity
import js.pekah.gooom_app.ui.main.add.AddFragment
import js.pekah.gooom_app.ui.main.home.HomeFragment
import js.pekah.gooom_app.ui.main.mypage.MyPageFragment
import js.pekah.gooom_app.ui.main.pop.PopFragment
import js.pekah.gooom_app.ui.main.search.SearchFragment

private const val TAG = "MainActivity_gooom"
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private lateinit var bottomNavigation: BottomNavigationView
    var fragment: Fragment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 첫 화면 지정
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout_main, HomeFragment())
            .commit()

        bottomNavigation = findViewById(R.id.tab_layout_bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_page_1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, HomeFragment())
                        .commit()
                    true
                }
                R.id.navigation_page_2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, PopFragment())
                        .commit()
                    true
                }
                R.id.navigation_page_3 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, AddFragment())
                        .commit()
                    true
                }
                R.id.navigation_page_4 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, SearchFragment())
                        .commit()
                    true
                }
                R.id.navigation_page_5 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout_main, MyPageFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}