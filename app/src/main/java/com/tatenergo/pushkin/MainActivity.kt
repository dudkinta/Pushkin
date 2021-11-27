package com.tatenergo.pushkin

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    companion object {
        const val COUNT_INDEX = "Index" // const key to save/read value from bundle
        const val TAG = "MainActivity"
    }

    lateinit var sp: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

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

        toLog("onCreate  ")
    }

    override fun onResume() {
        super.onResume()
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

    fun toLog(event: String) {
        var index = getSavedIndex()
        if (index>=stih.count())
            index=0
        Log.d(TAG, event + stih[index])
        index++
        saveIndex(index)
    }

    fun saveIndex(value: Int) {
        editor?.putInt(COUNT_INDEX, value)
        editor?.commit()
    }

    fun getSavedIndex(): Int {
        return sp?.getInt(COUNT_INDEX, 0) ?: 0
    }
}