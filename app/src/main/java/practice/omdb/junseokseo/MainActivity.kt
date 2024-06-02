package practice.omdb.junseokseo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import practice.omdb.junseokseo.databinding.ActivityMainBinding
import practice.omdb.junseokseo.fragments.HomeFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())
    }

    fun replaceFragment(
        fragment: Fragment,
        backStack: Boolean = false,
    ) {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            if (backStack) {
                addToBackStack(null)
            }
            setCustomAnimations(
                R.anim.slide_enter_from_right,
                R.anim.slide_exit_to_left,
                R.anim.slide_enter_from_left,
                R.anim.slide_exit_to_right
            )
            replace(R.id.nav_host_fragment_content_main, fragment, fragment::class.java.name)
        }
    }
}