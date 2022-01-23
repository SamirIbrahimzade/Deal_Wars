package com.gencool.droid.screens.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.gencool.droid.R
import com.gencool.droid.databinding.ActivityMainBinding
import com.gencool.droid.screens.find.FindFragment
import com.gencool.droid.screens.mydeals.MyDealsFragment
import com.gencool.droid.screens.offer.OfferFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val myDealsFragment = MyDealsFragment()
    private val findFragment = FindFragment()

    companion object {
        var myDeals: MutableList<String> = mutableListOf()
    }

    private var currentSelectedFragment: Fragment = MyDealsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureBottomNav()
        configureHeader()
        configureStatusbar(true)
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        if (fragment is MyDealsFragment) {
            transaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_from_right)
        } else if (fragment is FindFragment) {
            transaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_from_left)
        }

        runOnUiThread {
            if (fragment is OfferFragment) {
                binding.bottomNav.visibility = View.GONE
            } else {
                binding.bottomNav.visibility = View.VISIBLE
            }
        }
        transaction
            .replace(R.id.fl_main, fragment)
            .commit()
    }

    fun makeMyDealsSelected() {


    }

    fun makeFindSelected() {

    }

    private fun configureBottomNav() {
        replaceFragment(currentSelectedFragment)
        binding.apply {
            bottomNav.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_item_my_deals -> {
                        if (currentSelectedFragment::class.java != MyDealsFragment::class.java) {
                            currentSelectedFragment = myDealsFragment
                            replaceFragment(currentSelectedFragment)
                        }
                    }
                    R.id.menu_item_find -> {
                        if (currentSelectedFragment::class.java != FindFragment::class.java) {
                            currentSelectedFragment = findFragment
                            replaceFragment(currentSelectedFragment)
                        }
                    }
                }
                true
            }
        }
    }


    private fun configureHeader() {
        supportActionBar?.hide()
    }

    private fun configureStatusbar(makeDark: Boolean) {
        window.statusBarColor = resources.getColor(R.color.gray2, theme)

        val decor: View = window.decorView
        if (makeDark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        } else {
            decor.setSystemUiVisibility(0)
        }
    }


}