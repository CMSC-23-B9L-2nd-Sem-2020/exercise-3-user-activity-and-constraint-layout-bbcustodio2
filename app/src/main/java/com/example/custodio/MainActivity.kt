package com.example.custodio

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var nickname_input : TextView
    lateinit var nickname : TextView
    lateinit var change_button : Button

    val boxArray: List<Int> = listOf(
        1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nickname_input = findViewById(R.id.nickname_input)
        nickname = findViewById(R.id.nickname)
        change_button = findViewById(R.id.change_nickname)

        nickname.setOnClickListener {
            clickHandlerFunction(it)
        }

        change_button.setOnClickListener {
            clickTextFunction(it)
        }

        setListeners()
    }

    private fun clickHandlerFunction(view: View) {
        nickname_input.visibility = View.VISIBLE
        change_button.visibility = View.VISIBLE
        nickname.visibility = View.GONE

        nickname_input.requestFocus()
    }

    private fun clickTextFunction(view: View){
        nickname_input.visibility = View.GONE
        change_button.visibility = View.GONE
        nickname.visibility = View.VISIBLE

        val inputMethodManager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

        nickname.text = nickname_input.text
    }

    private fun getId(): List<List<Int>> {
        val idList: List<List<Int>> = listOf(
            listOf(
                R.id.box_1,
                R.id.box_2,
                R.id.box_3,
                R.id.box_4,
                R.id.box_5
            ),
            listOf(
                R.id.box_6,
                R.id.box_7,
                R.id.box_8,
                R.id.box_9,
                R.id.box_10
            ),
            listOf(
                R.id.box_11,
                R.id.box_12,
                R.id.box_13,
                R.id.box_14,
                R.id.box_15
            ),
            listOf(
                R.id.box_16,
                R.id.box_17,
                R.id.box_18,
                R.id.box_19,
                R.id.box_20
            ),
            listOf(
                R.id.box_21,
                R.id.box_22,
                R.id.box_23,
                R.id.box_24,
                R.id.box_25
            )

        )


        return idList;

    }

    private fun setListeners(){



        for(list: List<Int> in getId()){
            for(item: Int in (0..4)){
                findViewById<TextView>(list[item]).setOnClickListener {
                    invokeColors(it)
                }
            }
        }

    }

    private fun invokeColors(view: View){
      var count = 0
        for(list: List<Int> in getId()){
            for(item: Int in (0..4)) {
                if (view.id == list[item]) {
                    changeColors(count)
                }
                count++
            }
        }
    }

    private fun changeColors(count: Int){
        when(boxArray[count]){


        }
    }

    private fun changeAdjacent(int: Int){

    }

}


