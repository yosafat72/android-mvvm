package labs.yohesu.moviedb.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import dagger.hilt.android.AndroidEntryPoint
import labs.yohesu.moviedb.R
import labs.yohesu.moviedb.databinding.ActivityMainBinding
import labs.yohesu.moviedb.view.fragment.CategoryFragment
import labs.yohesu.moviedb.view.fragment.HomeFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var menu : Menu = binding.bottomNav.menu
        selectedMenu(menu.getItem(0))

        binding.bottomNav.setOnItemSelectedListener {
            selectedMenu(it)
            false
        }

    }

    private fun selectedMenu(item: MenuItem){
        item.isChecked = true
        when(item.itemId){
            R.id.menuDashboard  -> selectFragment(HomeFragment())
            R.id.menuCategory   -> selectFragment(CategoryFragment())
        }
    }

    private fun selectFragment(fragment: Fragment){
        val transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameContainer, fragment)
        transaction.commit()
    }


}