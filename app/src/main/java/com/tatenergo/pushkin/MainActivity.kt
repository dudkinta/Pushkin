package com.tatenergo.pushkin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    companion object {
        const val COUNT_INDEX = "Index" // const key to save/read value from bundle
        const val TAG = "MainActivity"
    }

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
    var index: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState!=null)
            index = savedInstanceState!!.getInt(COUNT_INDEX)
        Log.d(TAG, "onCreate:  " +stih[if (index == stih.count()) 0 else index])
        index++
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(COUNT_INDEX, index)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        index = savedInstanceState.getInt(COUNT_INDEX)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume:  " +stih[if (index == stih.count()) 0 else index])
        index++
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart:   " +stih[if (index == stih.count()) 0 else index])
        index++
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause:   " +stih[if (index == stih.count()) 0 else index])
        index++
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop:    " +stih[if (index == stih.count()) 0 else index])
        index++
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: " +stih[if (index == stih.count()) 0 else index])
        index++
    }
}