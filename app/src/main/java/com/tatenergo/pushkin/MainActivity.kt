package com.tatenergo.pushkin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    companion object {
        const val COUNT_INDEX = "Index" // const key to save/read value from bundle
        const val TAG = "MainActivity"
    }

    lateinit var sp: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    private lateinit var fragment1: ButtonsFragment
    private lateinit var fragment2: ButtonsFragment
    private var currentFragment: Int = 1
    val stih: List<String> = listOf<String>(
        "1. Ты видел деву на скале",
        "2. В одежде белой над волнами",
        "3. Когда, бушуя в бурной мгле,",
        "4. Играло море с берегами,",
        "5. Когда луч молний озарял",
        "6. Ее всечасно блеском алым",
        "7. И ветер бился и летал",
        "8. С ее летучим покрывалом?",
        "9. Прекрасно море в бурной мгле",
        "10.И небо в блесках без лазури;",
        "11.Но верь мне: дева на скале",
        "12.Прекрасней волн, небес и бури."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = this.getSharedPreferences("PushkinApp", MODE_PRIVATE)
        editor = sp.edit()
        fragment1 = ButtonsFragment.newInstance("frag1", "param fragment 1")
        fragment2 = ButtonsFragment.newInstance("frag2", "param fragment 2")

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getInt("currentFragment")
        }
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentView,
                when (currentFragment) {
                    2 -> fragment2
                    else -> fragment1
                }
            )
            .commit()
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.gotoFragment1 -> {
                    currentFragment = 1
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentView, fragment1)
                        .commit()
                    true
                }
                R.id.gotoFragment2 -> {
                    currentFragment = 2
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentView, fragment2)
                        .commit()
                    true
                }
                else -> false
            }
        }

        toLog("onCreate  ")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt("currentFragment", currentFragment)
    }

    override fun onResume() {
        super.onResume()
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentView,
                when (currentFragment) {
                    2 -> fragment2
                    else -> fragment1
                }
            )
            .commit()
        toLog("onResume  ")
    }

    override fun onStart() {
        super.onStart()
        toLog("onStart   ")
    }

    override fun onPause() {
        super.onPause()
        toLog("onPause   ")
    }

    override fun onStop() {
        super.onStop()
        toLog("onStop    ")
    }

    override fun onDestroy() {
        super.onDestroy()
        toLog("onDestroy ")
    }

    private fun toLog(event: String) {
        var index = getSavedIndex()
        if (index >= stih.count())
            index = 0
        Log.d(TAG, event + stih[index])
        index++
        saveIndex(index)
    }

    private fun saveIndex(value: Int) {
        editor?.putInt(COUNT_INDEX, value)
        editor?.commit()
    }

    private fun getSavedIndex(): Int {
        return sp?.getInt(COUNT_INDEX, 0) ?: 0
    }
}