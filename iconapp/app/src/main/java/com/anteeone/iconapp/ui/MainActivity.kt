package com.anteeone.iconapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.anteeone.iconapp.R
import com.anteeone.iconapp.ui.fragments.ItemListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(ItemListFragment(),"itemlistfragment")
    }

    private fun replaceFragment(fragment: Fragment,backStackId: String = "null"){
        var inMemoryFragment = supportFragmentManager.findFragmentById(fragment.id)
        if (inMemoryFragment != null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,inMemoryFragment)
                .addToBackStack(backStackId)
                .commit()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(backStackId)
            .commit()
    }
}