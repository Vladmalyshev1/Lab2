package com.example.lr2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

data class Photo(
    val imageId: Int,
    val name: String,
    val location: String
)



class MainActivity : AppCompatActivity() {
    private var currImage: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState != null) run {
            currImage = savedInstanceState.getInt("currImage")
            updateUI(currImage)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("currImage", currImage)
    }

    private val photos: List<Photo> = listOf(
        Photo(R.drawable.louvre, "Ancient royal castle in paris", "Paris, France, 1793"),
        Photo(R.drawable.pyramid, "Pyramid of Cheops", "Egypt, Cairo, 2560 BC"),
        Photo(R.drawable.notredamedeparis, "Notre Dame de Paris", "Paris, France, 1163"),
        Photo(R.drawable.eifel, "Eifel Tower", "Paris, France, 1887")
    )

    private fun updateUI(currId: Int){
        val imageView = findViewById<ImageView>(R.id.imageView)
        val nameView = findViewById<TextView>(R.id.textView2)
        val locationView = findViewById<TextView>(R.id.textView3)
        imageView.setImageResource(photos[currId].imageId)
        nameView.text = photos[currId].name
        locationView.text = photos[currId].location
    }

    fun changeInfo(view: View){
        var direction = 1
        if (findViewById<Button>(view.id).text == "Previous")
            direction = -1
        println(currImage)
        currImage += direction
        if (currImage == -1)
            currImage = photos.size - 1
        if (currImage == photos.size)
            currImage = 0
        updateUI(currImage)
    }
}